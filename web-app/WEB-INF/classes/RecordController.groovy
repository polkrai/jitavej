import org.codehaus.groovy.grails.web.json.JSONArrayimport org.codehaus.groovy.grails.web.json.JSONObjectimport grails.converters.deep.JSONclass RecordController {
	def scaffold = Record    def list2 = {    	    	println "RecordService --> list()!!!!";	    	def list = Record.list();	        render list.encodeAsJSON()	}
    
    def save = {		render ""	}
    	def test1 = {			def json = Record.get(2).encodeAsJSON()		println json		def inputObject = JSON.parse(json)				Record record = new Record(inputObject)		//record.name = record.name + "-json"		//record.save()		println "record.name > " + record.name		render json	}
}
