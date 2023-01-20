import grails.converters.deep.JSON
import org.springframework.web.context.request.RequestContextHolder
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class StationController {

	def scaffold = Station

    def list2 = {
		
		def list = Station.list([sort:'id',order:'asc']);
		
		render list.encodeAsJSON()
	    
	}
	
	def list3 = {
	    
	    if(session.station != null){
	    
	    	def list = Station.get(session.station)
			
			render session.station
		}
		else {
		
			def list = Station.list([sort:'id',order:'asc']);
			
			render list.encodeAsJSON()
		} 
	    
	}
	
    def findAllByComponent = {
    
	    def list = Station.findAllByComponent(session.component, [sort:'id',order:'asc']);
	    
	    render list.encodeAsJSON()
	}
	
	def findByIp = {
	
		def ip = request.getRemoteAddr()			
	     
	    //def list = Station.findByIp(ip.toString())
	    
	    if(session.station != null){
	    
	    	def list = Station.get(session.station)
		}
		
	    render session.station //list as JSON
	}
	
}
