import org.codehaus.groovy.grails.web.json.JSONObjectimport grails.converters.*class UnitController {
	def scaffold = Unit	
    def suggest = {
    	println "ItemService > suggest()!!!!";
    	String key = params.key    	def limit = params.limit
    	def list = Item.list(max:5,sort:'id');
        render list.encodeAsJSON()
    }
        def list2 = {	    println "ItemService --> list()!!!!";	    def list = Item.list();	    render list.encodeAsJSON()	}	
    def findByName = {
    	println "ItemService > findByName()!!!!";
    	def item = Item.findByName(params.name)
        render item.encodeAsJSON()
    } 
    }
