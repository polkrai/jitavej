class Icd10Controller {

	def scaffold = Icd10
	
    def repair = {
    	def c = OrderIcd10.createCriteria()
    	def list = c.list {
    		isNull('icd10')
    	} 
    	
    	
    	
    	list.each{
    		def icd10 = Icd10.findByCode(it.code)
    		
    		if(icd10 == null){
    			icd10 = new Icd10()
    			icd10.code = it.code
    			icd10.name = ''
    			icd10.save()
    			println icd10.id
    			
    			
    		}
    		it.icd10 = icd10
    		it.save()
    	}
        render ''
    }  

	def putoracle = {
		def icd10 = new Icd10()
		icd10.code = params.code
		icd10.name = params.name			
		icd10.save()
		render icd10.id
	}
	
    def list2 = {
    	println "Icd10Service --> list()!!!!";
    	def list = Icd10.list();
        render list.encodeAsJSON()
    }
    
    def suggest = {
	 //   	println "Icd10Service > suggest()!!!!";
	    	String key = params.key

	  /*  	def c = Icd10.createCriteria()
	    	def list = c.list {
	    	       ilike('name', "%"+key+"%"),
	    	       ilike('name', "%"+key+"%") 
	    	} 
	    */	
	  //  	def list = Icd10.findAllByCodeIlikeOrNameIlike("%"+key+"%","%"+key+"%", [max:5, offset:0], [sort:'code',order:'asc'] )

    	def c = Icd10.createCriteria()
    	def list = c.list {
	    	or {
	    		 ilike('code', "%"+key+"%")
	    		 ilike('name', "%"+key+"%")
	    	}	   
	    	ne('name', '')
	    	eq('deleted', false)
    	    maxResults(10)
    	    //order('code', 'asc')
    	} 
    	
        render list.encodeAsJSON()
	}

    def findByCode = {
    	def c = Icd10.createCriteria()
    	def list = c.list {
    		ilike('code', params.code)
	    	ne('name', '')
    	    maxResults(10)
    	    order('code', 'asc')
    	} 
    	if(list.size > 0){
    		render list.get(0).encodeAsJSON()
    	}
        render ''
    }   
    
	def genorder = {
		def b = Order.createCriteria()
    	def orders = b.list {
	    	eq('type', 'drug')
	    	ge('id', new Long(40000))
	    	le('id', new Long(50000))
	    	eq('deleted', false)
	    //	order('id', 'asc')
    	} 		
		orders.each{order0 ->
			
			def c = OrderIcd10.createCriteria()
	    	def icd10s = c.list {
				eq('order', order0)
		    	order('id', 'asc')
	    	} 			
			int priority = 1
			if(icd10s.size > 1){
				icd10s.each{icd10 ->
				//	println patient.id +' '+ patient.hn +' '+order0.id +' '+icd10.code +' '+icd10.priority
					icd10.priority = (priority++)
					icd10.save()
				// 	println order0.id +' '+icd10.code +' '+icd10.priority
					
				}				
			}

		}
		println 'ok genorder'
		render 'ok'
	}
	
	def genorder2 = {
			def b = Order.createCriteria()
	    	def orders = b.list {
		    	eq('type', 'drug')
		    	ge('id', new Long(380000))
		    	le('id', new Long(390000))
		    	eq('deleted', false)
		    //	order('id', 'asc')
	    	} 		
		//	println orders.size
			
			orders.each{order0 ->
				
				def c = OrderIcd10.createCriteria()
		    	def icd10s = c.list {
					eq('order', order0)
			    	order('id', 'asc')
		    	} 			
				int priority = 1
				if(icd10s.size > 1){
					icd10s.each{icd10 ->
					//	println patient.id +' '+ patient.hn +' '+order0.id +' '+icd10.code +' '+icd10.priority
						icd10.priority = (priority++)
						icd10.save()
					// 	println order0.id +' '+icd10.code +' '+icd10.priority
						
					}				
				}

			}
			println 'ok genorder2'
			render 'ok2'
		}
}
