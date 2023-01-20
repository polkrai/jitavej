import grails.converters.deep.JSON

class PatientController {

	def scaffold = Patient
	
	def index1() {
	
		return 1
	}
	
    def trim = {
		
	}
	
    def list2 =  {
		
    	println "GRAILS --> list()!!!!";
   	
    	def list = Patient.list();
		
		//def list = Patient.get(params.patient_id)
		
        render list.encodeAsJSON()
    }
	
	def putoracle = {
		
		def patient = new Patient()
		
		patient.hn = params.hn
		patient.firstname = params.firstname
		patient.lastname = params.lastname
		patient.birthdate = new Date(Long.parseLong(params.date))
		patient.save()
		
		def address = new Address()
		address.hn = params.hn
		address.save()
		
		render patient.id
	}
	
    def suggest = {
	    
			def dns
	        if(params.dental != null){
	        	dns = Dn.createCriteria().list {	
		    		if(params.key != ''){		
		    			ilike('dn', params.key + '%')   
		    		}
		    		maxResults(20)
		    		order('dn', 'asc') 
	        	}
		      
	        }
	    
	        def fooList = new ArrayList()
	    	
	        dns.each { dn ->
	            def fooMap = new HashMap()
	            fooMap.put("hn", dn.patient.hn)
	            fooMap.put("dn", dn.dn)	
	            fooMap.put("firstname", dn.patient.firstname)
	            fooMap.put("lastname", dn.patient.lastname)	            
	            fooList.add(fooMap)
	        }
	        
	    	def list = Patient.createCriteria().list {	
	    		if(params.key != ''){
	    			or{			
	        			eq('hn', params.key)   
	        			ilike('firstname',  params.key + '%')    	
	        			ilike('lastname', params.key + '%')    	
	        			
	    			}

	    		}
	    		maxResults(20)
	    		order('hn', 'asc') 
	       	}
	    	
	        list.each { patient ->
	            def fooMap = new HashMap()
				fooMap.put("id", patient.hn)
	            fooMap.put("hn", patient.hn)
	            fooMap.put("firstname", patient.firstname)
	            fooMap.put("lastname", patient.lastname)
	            
	            if(params.dental != null){
    	            fooMap.put("dn", '')	
	        		def dn = Dn.findByPatient(patient)
	        		if(dn != null){
	    	            fooMap.put("dn", dn.dn)	
	        		}
	            }
	            
	            fooList.add(fooMap)
	        }

	        render fooList as JSON		
	    }
	
	def setowner = {
		
			def patient = Patient.get(params.patient_id)
			def owner = User.get(params.owner_id);
			patient.owner = owner
			patient.save()
	    	render '' 
	    }

	def setmederror = {		
			def patient = Patient.get(params.patient_id)
			patient.mederror = params.mederror
			patient.save()
	    	render '' 
	    }
	
	def setwarning = {		
			def patient = Patient.get(params.patient_id)
			patient.warning = params.warning
			patient.save()
	    	render '' 
	    }
	
    def findByHn = {
		
        	def list = Patient.findAllByHn(params.hn)
			
        	if(list.size() > 0){
					
        		render list.get(0) as JSON  
        	}
			
        	render ''  
        } 	
	
	def address2 = {
		Address address = Address.findByHnAndType(params.hn, '1')
		if(address != null){
			render address as JSON		
		}
		
		render new Address() as JSON			
	}
	
	def getQueueIdByVn = {
		
		def visit_id = null
		def visit_vn = null
		
		def v = Visit.createCriteria()
		def listv = v.list {
			eq('vn', params.vn)
			order('id', 'desc')
			maxResults(1)
		}
		
		listv.each { vid ->
			visit_id = vid.id
			visit_vn = vid.vn
		}
		
		def c = Q.createCriteria()
	    def list = null
		
		list = c.list {
			if(visit_vn != ''){
				
				eq('vn', visit_vn)
			}
			else {
				
				eq('toComponent', session.component)
			}
			
			order('addDate', 'asc')
			maxResults(1)
		}

		def fooList = new ArrayList()
		
		list.each { queue ->
			def fooMap = new HashMap()
			fooMap.put("id",  queue.id)

			fooList.add(fooMap)
		}
	
		render fooList as JSON

	}

	def getQueueIdByHn = {

		def visit_vn = null
		
		def v = Visit.createCriteria()
		def listv = v.list {
			eq('hn', params.hn)
			order('id', 'desc')
			maxResults(1)
		}
		
		listv.each { vid ->
		
			visit_vn = vid.vn
		}
		
		def list = Q.createCriteria().list {
			eq('vn', visit_vn)
			order('vn', 'asc')
			maxResults(1)
		}
		
		def json = []
		
		list.each { queue ->
			json = [id: queue.id]
		}
	
		render json as JSON

	}
		
	def getidQueue = {
	
		def v = Visit.createCriteria()
		def listv = v.list {
			eq('hn', params.hn)
			order('id', 'desc')
			maxResults(1)
		}
		
		def fooList = new ArrayList()
		
		listv.each { visit ->

			def list = Q.createCriteria().list {	
				eq('vn', visit.vn)
				order('vn', 'asc')
				maxResults(1)
			}
			
			list.each { queue ->
				def fooMap = new HashMap()
				fooMap.put("id", queue.id)
			
				fooList.add(fooMap)
			}
		}
		
		render fooList as JSON
	}
	
	def getlastVisit = {
		
		def json = []
		
		def visit = Visit.createCriteria().list {
			eq('hn', params.hn)
			order('id', 'desc')
			maxResults(1)
		}
		
		visit.each { v ->
		
			def queue = Q.createCriteria().list {
				eq('visit.id', v.id)
				//order('vn', 'asc')
				maxResults(1)
			}
		
			queue.each { q ->
				json = [id: q.id]
			}
				
			//def json = [id: queue.id]
		}
		
		render json as JSON
	}

}
