import grails.converters.deep.JSON
//import org.springframework.web.context.request.RequestContextHolder
//import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class ActionController {

	def scaffold = Action
	
    def list2 = {
		
		println "ComponentController --> list()!!!!" + Component.get(params.component_id);
		
		def list = Action.findAllByComponent(Component.get(params.component_id),[sort:'order', order:'asc']);
		
        def fooList = new ArrayList()
        
        list.each { action ->
            def fooMap = new HashMap()
            fooMap.put("id", action.id)
            fooMap.put("code", action.code)
            fooMap.put("name", action.name)
            fooMap.put("order2", action.order)
            fooList.add(fooMap)
        }

        render fooList as JSON	
    }

}
