//import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class IframeController {

   def scaffold = Iframe
   
   def list2 = {
		
		def list = Iframe.findAllByIframe('history', [sort:'id',order:'asc']);
		
		render list.encodeAsJSON()
	    
	}
}
