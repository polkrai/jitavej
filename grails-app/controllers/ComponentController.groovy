import grails.converters.deep.JSON

class ComponentController {

	def scaffold = Component
	
    def list2 = {
   // 	println "ComponentController --> list()!!!!";
    	def list = Component.list([sort:'name',order:'asc']);
    	
        def fooList = new ArrayList()
        list.each { component ->
            def fooMap = new HashMap()
            fooMap.put("id", component.id)
            fooMap.put("name", component.name)
            fooMap.put("type", component.type)
            fooList.add(fooMap)
        }

        render fooList as JSON	
    }

	
}
