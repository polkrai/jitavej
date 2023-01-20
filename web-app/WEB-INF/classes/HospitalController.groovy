class HospitalController {

	def scaffold = Hospital

    def suggest = {
    	def c = Hospital.createCriteria()
    	def list = c.list {
	    	or {
	    		 ilike('code', "%"+params.key+"%")
	    		 ilike('name', "%"+params.key+"%")
	    	}	   
	    	ne('name', '')
    	    maxResults(100)
    	    order('code', 'asc')
    	}
        render list.encodeAsJSON()
	}
	
}
