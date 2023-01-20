import grails.converters.deep.JSONimport org.codehaus.groovy.grails.web.json.JSONObjectimport grails.converters.*class DoseController {
	def scaffold = Dose		def trim = {		def list = Drug.list()		list.each{			println it.code			it.code = it.code.trim()			it.save()		}	}		def putoracle = {		def dose = new Dose()		dose.code = params.code		dose.name1 = params.name1		dose.name2 = params.name2			dose.name3 = params.name3		dose.name4 = params.name4		dose.frequency = 1		dose.pertime = 1		dose.oracleId = params.oracle_id		dose.save()		render dose.id	}	def put3 = {		def dose = new Dose()		dose.code = params.dosetext	//	dose.description = params.dosetext				if(params.frequency == null){			dose.frequency = 1		}else{			dose.frequency = Double.parseDouble(params.frequency)		}		if(params.pertime == null){			dose.pertime = 1		}else{			dose.pertime = Double.parseDouble(params.pertime)		}				dose.time = params.time		dose.save()						GenericDose genericDose = new GenericDose()		genericDose.generic = Drug.get(params.drug_id).generic		genericDose.dose = dose		genericDose.save()				render dose.id	}	
    def suggest = {
    	String key = params.key    //	println key    	    	def c = Dose.createCriteria()    	def list = c.list {    	       ilike('code', ""+key+"%")    	       eq('deleted', false)    	       order('code', 'asc')    	}     	        def fooList = new ArrayList()        list.each { drug ->            def fooMap = new HashMap()            fooMap.put("code", drug.code)            fooList.add(fooMap)        }       render fooList as JSON	    	

    }
        def findByCode = {    	def dose = Dose.findByCodeIlike(params.code)   		render dose as JSON    } 	    def list2 = {   // 	println "DoseService --> list()!!!!";    	def list = Dose.list([sort:'name',order:'asc']);        render list.encodeAsJSON()    }    def findAllByDrug = {	//   println "DoseService > findByName()!!!!";	   def dose = Dose.findByDose(params.dose)	   dose.item	   render dose as JSON	} 	
    def get2 = {
   // 	println "DoseService > findByName()!!!!";
    	def dose = Dose.get(Integer.parseInt(params.dose_id))        render dose as JSON
    } 
    
    def order = {
   // 	println "DoseService > " + params.data
    	def jsonArray = JSON.parse(params.data)    	println "Class of jsonArray: ${jsonArray.class.name}"    	jsonArray.each {     	//	def inputObject = JSON.parse(it)    	//	def dose = new Dose( inputObject )      	    int start = it.toString().indexOf(":")    		int end = it.toString().indexOf(",")    		    		String tmp = it.toString().substring(start+1, end)    		println "tmp: "+ tmp    		int doseId = Integer.parseInt(tmp)    		    		Dose dose = Dose.get(doseId)    		      		println "Dose: "+ dose.name    	}
    	render ""
    }
    	def test1 = {		def json = Dose.get(2).encodeAsJSON()		println json		def inputObject = JSON.parse(json)				Dose dose = new Dose(inputObject)		dose.name = dose.name + "-json"		dose.save()		render json	}
}
