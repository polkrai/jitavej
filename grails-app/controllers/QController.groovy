import grails.converters.deep.JSON
import org.springframework.web.context.request.RequestContextHolder
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class QController {
	
	def scaffold = Q
	
	def test1 = {
	
		def p = Patient.get(43870)
		def q = Q.get(932945)
		
		render q.toStation
	}
	
	def scan = {
		
	    	def c = Q.createCriteria()
	    	def list = c.list {
				isNotNull('an')
			} 
	    	list.each{q->
	    		def c2 = Order.createCriteria()
	        	def orders = c2.list {
	 	    	   eq("visit", q.visit)
	 	    	   eq("type", 'drug')
	 	    	   eq("mode", 'continuation')
	 	    	   eq("stop", false)
	 	    	   eq("deleted", false)
	     	       order('id', 'asc')
	    		}
	    		
	    		if(orders.size() > 1){
	    			println q.visit.id  +' > '+ q.an +' > '+ orders.size()
	    		}
	    		
	    	}
			
	    render 'compltete!'
	}

	def financetolab = {
		
    	Q queue = Q.get(params.queue_id)
    	
    	////////////////////////////////////////////////////
    	
    	QLog qlog = new QLog()
    	qlog.an = queue.visit.an
    	qlog.vn = queue.visit.vn
		qlog.fromComponent = queue.fromComponent
		qlog.toComponent = session.component
		qlog.sendComponent = Component.get(13)
    	qlog.toStation = Station.get(params.station_id)	
		qlog.user1 = null
		qlog.user2 = null
		qlog.addDate = new Date()
		qlog.sendTime = new Date()
		qlog.receiveTime = new Date()
		qlog.visit = queue.visit
		qlog.action = queue.action
       	qlog.action3 = Action.get(6)   		
    	qlog.save()
    	
    	////////////////////////////////////////////////////
    	
    	queue.toComponent = qlog.sendComponent
    	queue.fromComponent = session.component
    	queue.reComponent = Component.get(11)
    	queue.action3 = Action.get(6)
    	
    	if(params.station_id != null){
    		//queue.toStation = qlog.toStation
    	}
    	else{
    		//queue.toStation = null;
    	}
		    	
    	queue.receiveTime = null
		queue.action = qlog.action3
		queue.actiontext = queue.action.name
		queue.actiontext3 = queue.action3.name
		queue.com1 = queue.fromComponent.name
		queue.com2 = queue.toComponent.name 
		queue.sendTime = new Date()
     	queue.save()

    	render queue.encodeAsJSON()  
    }	
	
	def changeComponent = {		
		
		println 'params.user_id = ' + params.user_id
	
    	Q queue = Q.get(params.queue_id)
    	
    	QLog qlog = new QLog()
    	qlog.an = queue.visit.an
    	qlog.vn = queue.visit.vn
		
		qlog.fromComponent = queue.fromComponent
		qlog.toComponent = session.component
		qlog.sendComponent = Component.get(params.component_id)
		
		if(session.station != null){
			qlog.toStation = session.station
		}
    	
		qlog.user1 = null
		
		if(params.user_id != null){
			
			qlog.user1 = User.get(params.user_id)
		}
		
		qlog.user2 = null
		qlog.addDate = new Date()
		qlog.sendTime = new Date()
		qlog.receiveTime = new Date()
		qlog.visit = queue.visit
		qlog.action = queue.action
       	qlog.action3 = Action.get(params.action_id)   		
    	qlog.save()
    	
    	queue.toComponent = qlog.sendComponent
    	queue.fromComponent = session.component
		
    	println 'session.component >' + session.component.id
		
    	queue.reComponent = null
    	queue.action3 = null
    	
    	if(params.station_id != null){
    		queue.toStation = Station.get(params.station_id)
    	}
    	else{
    		//queue.toStation = null;
    	}
		
		queue.sendTime = new Date()
    	queue.receiveTime = null
    	
    	try{
    		queue.action = qlog.action3
    		queue.actiontext = queue.action.name
    		queue.com1 = queue.fromComponent.name
    		queue.com2 = queue.toComponent.name 		
    	}
    	catch(Exception ex){
    		
    	}

		if(params.user_id != null){
			queue.user1 = User.get(params.user_id)
		}
		
		if(session.mode == 'dental' && queue.visit.an == null){
			queue.dental = true
			queue.receiveTime = null
			queue.user2 = null
		}
		
		if(queue.toComponent.id == 2){
			def visit = queue.visit
			visit.status = '2'
			visit.closeTime = new Date()
			visit.save()
		}
			
     	queue.save()

    	render queue.encodeAsJSON()  
    }
    
    def list(int componentId){
    
		render Q.findAllByToComponent(Component.get(componentId)).encodeAsJSON()  
    }
    
    def stationcount = {
    
    	def start = new Date() 
    //	start = start - 5
    	start.setHours(0) 
        start.setMinutes(0) 
        start.setSeconds(0) 
    	def c = Q.createCriteria()
    	def list = c.list {
	    	       eq("toStation", Station.get(params.station_id))
	    	       gt('addDate', start) 
		    	   isNull('an')
		    	} 
    	render list.size()
    }
    
    def before = {
    
    	println 'session.station = ' + session.station
    	
    	if(session.component != null){
    	
	    	def c = Q.createCriteria()
	    	def list = null
	    	
	    	if(session.component.type != 'med'){
	    	
		    	list = c.list {
		    	
	    	       eq('toComponent', session.component)
		    	   order('addDate', 'asc')
		    	   maxResults(200)
		    	} 
	    	}
	    	else{
	    	
		    	list = c.list {
		    	
		    	   eq('toComponent', session.component)
		    	   
		    	   if(session.station != null){
		    	   
		    	   		eq('toStation', session.station)
		    	   }
		    	   
		    	   order('addDate', 'asc')
		    	   //maxResults(100)
		    	} 	    	
	    	}
	    	     	
	   		def fooList = new ArrayList()
            
        	list.each { q ->
                def fooMap = new HashMap()
                fooMap.put('id', q.id)
                fooMap.put('firstname', q.visit.patient.firstname)
                fooMap.put('lastname', q.visit.patient.lastname)
                
                if(q.fromComponent != null){
                
                	fooMap.put('from', q.fromComponent.name)
                }
                else{
                
                	fooMap.put('from', '')
                }
                
                fooMap.put('action_text', q.actiontext)
                
                if(q.action != null){
                
                	fooMap.put('action_code', q.action.code) 
                	fooMap.put('action_name', q.action.name)
                	fooMap.put('station', q.toStation) 
                }
                
                fooMap.put('time', q.addDate.toString().substring(11,16))
 
 				if(q.receiveTime == null){
					 
 					fooMap.put('receive', false)
 				}
 				else{
 				
 					fooMap.put('receive', true)
 				}
           		
                fooMap.put('appointment', null)
                
                fooList.add(fooMap)
            }

            render fooList as JSON	   		
    	}
    	
    	render ''

    }
    
    def current = {
    	def list = Q.findAllByFromComponent(session.component)
    	list.each{q ->
    		q.visit.patient
    	}
    	render list as JSON  
    }   
    
    def after = {
    
    	def start = new Date() 
        start.setHours(0) 
        start.setMinutes(0) 
        start.setSeconds(0) 
                
   		def list = null
		if(session.component != null && session.component.type == 'med'){
	    	def c = Q.createCriteria()
	    	list = c.list {
		    	   ne("toComponent", session.component)
		    	   if(session.station != null){
		    	   		eq("toStation", session.station)
		    	   }
	    	       isNull("an")
	    	       gt('addDate', start) 
		    	   order('addDate', 'asc')
	    	} 	
		}else{
			def c = Q.createCriteria()
			    	list = c.list {
				    	   eq("fromComponent", session.component)
				    	   isNull("an")
				    	   gt('addDate', start) 
				    	   order('addDate', 'asc')
			    	} 
			    	list.each{q ->
			    		q.visit.patient
			    	}
		}		
		
	    def fooList = new ArrayList()
    	list.each { q ->
            def fooMap = new HashMap()
            fooMap.put("id", q.id)
            fooMap.put("firstname", q.visit.patient.firstname)
            fooMap.put("lastname", q.visit.patient.lastname)
            if(q.fromComponent != null){
            	fooMap.put("from", q.fromComponent.name)
            }else{
            	fooMap.put("from", '')
            }
            fooMap.put("action_text", q.actiontext)
            if(q.action != null){
            	fooMap.put("action_code", q.action.code) 
            	fooMap.put("action_name", q.action.name) 
            }
            
             fooMap.put("time", q.addDate.toString().substring(11,16))
        //    fooMap.put("owner", q.visit.patient.owner)
            fooMap.put("appointment", q.visit.patient.appointment)
            fooList.add(fooMap)
        }

        render fooList as JSON	  
            
  //  	render ''
    }  
    
    def get2 = {
    
       	def q = Q.get(params.queue_id)
   
   		q.receiveTime = new Date()
   		q.save()
   
       	if(q.visit.an != null){
       	
			params.an = q.visit.an
			q.actiontext3 = Ipd.findByAn(q.visit.an).building.name
			println q.actiontext3
		}

       	render q as JSON  
    } 

    def get3 = {
       	def q = Q.get(params.queue_id)
   
   		//q.receiveTime = new Date()
   		//q.save()
   
       	if(q.visit.an != null){
			params.an = q.visit.an
			q.actiontext3 = Ipd.findByAn(q.visit.an).building.name
			println q.actiontext3
		}  
  		//q.visit.an = null
       	render q as JSON  
    } 
        
    def load = {
    
    	def list = Q.findAllByToComponent(Component.get(params.component_id))
    	list.each{q ->
    		q.visit.patient
    	}
    	render list as JSON  
    }
    
    def loadStation = {
    //	def list = Q.findAllByToStation(Station.get(params.station_id))
    	def c = Q.createCriteria()
	    def	list = c.list {
		    	   eq("toComponent", Component.get(10))
	    	       eq("toStation", Station.get(params.station_id))
	    	       isNull("an")
		    	   order('addDate', 'asc')
	    	} 	
  
	    def fooList = new ArrayList()
    	list.each { q ->
            def fooMap = new HashMap()
            fooMap.put("id", q.id)
            fooMap.put("firstname", q.visit.patient.firstname)
            fooMap.put("lastname", q.visit.patient.lastname)
            
            if(q.fromComponent != null){
            
            	fooMap.put("from", q.fromComponent.name)
            }
            else{
            
            	fooMap.put("from", '')
            }
            
            fooMap.put("action_text", q.actiontext)
            
            if(q.action != null){
            	fooMap.put("action_code", q.action.code) 
            	fooMap.put("action_name", q.action.name) 
            }
            
            fooMap.put("time", q.addDate.toString().substring(11,16))
        	//fooMap.put("owner", q.visit.patient.owner)
            fooMap.put("appointment", q.visit.patient.appointment)
            fooList.add(fooMap)
        }

        render fooList as JSON	
    }
}
