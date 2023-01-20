import grails.converters.deep.JSON
import grails.util.GrailsUtil
import org.springframework.web.context.request.*
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import javax.servlet.http.*
import java.security.MessageDigest

class UserController {

	def scaffold = User

	def changepw = {
		def pw = 'pw'
		def md = MessageDigest.getInstance("MD5")
		md.update(pw.toString().getBytes()) 
		byte[] digest = md.digest();
		BigInteger number = new BigInteger(1,digest);
        render number.toString(16);
	}


	def putusergroup = {
		def userGroup = new UserGroup()
		userGroup.name = params.name
		userGroup.oracleId = params.id
		userGroup.save()
		render userGroup.id
	}
	
	def putuser = {
		def user = new User()
		user.title = params.title
		user.firstname = params.firstname
		user.lastname = params.lastname
		user.username = params.username
		user.password = '81dc9bdb52d04dc20036dbd8313ed055'
		user.status = '1'
		user.userGroup = UserGroup.findByOracleId(params.group_id)
		user.save()
		render user.id
	}

    def login = {

   		if (params.id_sess != null) {
   			
   			session.session_id = params.id_sess	
   		}
    
    	session.component = Component.get(params.component)
   	
    	println "login >>> session.component >>> "+session.component
    	
    	/*	
    	render "SessionId > " + session.session +"<br/>"+ 
    		   "Component > " + session.compoment.name +"<br/>"+
    		   "Ip > " + session.ip +"<br/>"+
    		   "Date > " + session.dateText +"<br/>"+
    		   "Login Time > " + session.loginTime
    	*/
    	
    	def fooMap = new HashMap()
		
	    fooMap.put("session_id", session.session_id)
	    
	    render fooMap.encodeAsJSON()
    }
	
    def logout = {
    
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();	
		
		def ip = request.getRemoteAddr()
		
		def list = JSession.findAllByIp(ip)
		
		list.each(){
			it.delete()
		}
		
		render ''
	}
	
	def iframe = {
	
		session.mode = params.mode

		session.id_sess = params.id_sess
		
		session.ipd = 'false'

		println session.mode
		
		if(session.mode == 'lab_order'){
			
			session.ipd = 'true'
		}

		if(session.mode == 'record_oneday' || session.mode == 'record_unitdose' || session.mode == 'record_homemed' || session.mode == 'lab_order'){
		
			session.visit = Visit.get(params.vn_id);
			
		}
		
		if(session.mode == 'record_continuation'){
		
			session.visit = Visit.get(params.vn_id);
			session.building = Building.get(params.bui_id)
		}
		
		if(session.mode == 'history'){
		
			session.patient = Patient.get(params.pa_id)
			session.visit = Visit.get(params.vn_id)
		}
		
		if(session.mode == 'appointment'){
		
			session.building = null
			session.component = null
			session.patient = null
			
			if(params.pa_id != null){
			
				session.patient = Patient.get(params.pa_id)
			}
			
			if(params.com_id != null){
			
				session.component = Component.get(params.com_id)
			}
			
			if(params.bui_id != null){
			
				session.building = Building.get(params.bui_id)
			}				
		}		

		if(session.mode == 'refersend'){
		
			if(params.pa_id != null){
			
				session.patient = Patient.get(params.pa_id)
			}
			
		}
		
		if(session.mode == 'referreply'){
		
			if(params.pa_id != null){
				
				session.patient = Patient.get(params.pa_id)
			}
		}
					
		if(session.mode == 'med'){
		
			session.component = Component.get(10)
		}
		else if(session.mode == 'dental'){
		
			session.component = Component.get(61)
		}
		else if(session.mode == 'frontmed'){
		
			session.component = Component.get(12)
		}
		else if(session.mode == 'backmed'){
		
			session.component = Component.get(27)
		}
		else if(session.mode == 'lab'){
		
			session.component = Component.get(11)
		}
		else if(session.mode == 'remed'){
		
			session.component = Component.get(25)
		}
		else if(session.mode == 'emergency'){
		
			session.component = Component.get(21)
		}
		
		redirect(url:"/jitavej/gwt/com.neural.jitavej.Jitavej/Jitavej.html")
	}

	def mode = {

		if(session.mode == null){
		
			//session.mode = 'med'
			//session.mode = 'dental'
			//session.mode = 'frontmed'
			//session.mode = 'backmed'
			session.mode = 'lab'
			//session.mode = 'remed'
			//session.mode = 'emergency'
			//ssesion.mode = 'history'
			//session.mode = 'record_oneday'
			//session.mode = 'record_unitdose'
			//session.mode = 'record_continuation'
			//session.mode = 'record_homemed'
			//session.mode = 'lab_order'
			//session.mode = 'appointment'
			//session.mode = 'refersend'
			//session.mode = 'referreply'
		}		
		
		def fooMap = new HashMap()
		
	    fooMap.put("mode", session.mode)

        render fooMap.encodeAsJSON()

	}
	
	def visit = {
	
		if(session.visit == null){
			
			session.visit = Visit.get(1061948)
			render ''
		}
		
		render session.visit as JSON	
	}
	
	def patient = {
	
		if(session.patient == null){

			render Patient.get(83905) as JSON	
		}
		else{
			
			render session.patient as JSON	
		}
	}
	
    def component = {
    
		if(session.mode == 'med'){
		
			session.component = Component.get(10)
		}
		else if(session.mode == 'dental'){
		
			session.component = Component.get(61)
		}
		else if(session.mode == 'frontmed'){
		
			session.component = Component.get(12)
		}
		else if(session.mode == 'backmed'){
		
			session.component = Component.get(27)
		}
		else if(session.mode == 'lab'){
		
			session.component = Component.get(11)
		}
		else if(session.mode == 'remed'){
		
			session.component = Component.get(25)
		}
		else if(session.mode == 'emergency'){
		
			session.component = Component.get(21)
		}
		else if(session.mode == 'appointment'){
		
			//session.component = Component.findByType('restore')
			//session.patient = Patient.findByHn(64427)
			//session.building = Building.get(1)
		}
		
		if(session.component != null){
		
			render session.component as JSON	
		}
		else{
		
			render ''
		}
        
	}
	
    def building = {
    
    	if(session.building == null){
			//session.building = Building.get(1)
    		render ''
    	}
    	else{
    	
    		render session.building as JSON	
    	}
        
	}
	
	def station = {
	
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();	
		
		def ip = request.getRemoteAddr()
		
		println "IPRemote >>  "  + ip
		
		println "IPStation >> " + Station.findByIp(ip)
		
		if (ip == '127.0.0.1'){
			
			ip = '192.168.10.191'
		}
		
		session.station = Station.findByIp(ip)
		
		render session.station as JSON
    	
	}
		
	def changeComponent = {
		
		session.component = Component.get(params.component_id)
		
	   	render ''
	}
	
	def generator = { String alphabet, int n -> 
		new Random().with {
			(1..n).collect { alphabet[ nextInt( alphabet.length() ) ] }.join()
		}
	}
	
	def jsession = {
	
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();	
		
		def ip = request.getRemoteAddr()
		
		session.station = Station.findByIp(ip);
		
		if(session.station != null){
		
			//println session.station.name
		}
		else {
		
			//session.station = "::1";
			//println ip
		}
				
		def jsession = JSession.findByIpAndSessionId(ip, session.id_sess)
		
		session.jsession = jsession
	
        if (GrailsUtil.environment == 'development') {
        
    		if(jsession == null){
    		
    			jsession = new JSession()
    			//jsession.ip = InetAddress.getLocalHost().getHostAddress()
    			jsession.ip = request.getRemoteAddr()	
    			//jsession.sessionId = '00000000000000000'
    			jsession.sessionId = generator((('a'..'z')+('0'..'9')).join(), 26)
    			jsession.user = User.get(1)
    			jsession.status = '1'
    			jsession.expireTime = '9000000000'
    			jsession.loginTime = '9000000000'
    			jsession.logoutTime = '9000000000'
    			jsession.dateText = '2019-03-19'	
    			jsession.save();						
    		}		           
        }
	
		
		if(jsession != null){
		
			render jsession as JSON		
		}
		else{
		
			render ''
		}
		

	}
    
    def list2 = {
    
		def c = User.createCriteria()
		
	    def	list = c.list {
			or{
		   	 	eq("userGroup", UserGroup.get(33))
		   		eq("userGroup", UserGroup.get(1))			
			}

	    	eq("active", true)
		    order('firstname', 'asc')
	    } 
		
		render list.encodeAsJSON()
	}
	
	def changepassword = {
	
		def user = User.get(params.user_id);	
		
		def md = MessageDigest.getInstance("MD5")
		
		md.update(params.password1.toString().getBytes())
		
		byte[] digest = md.digest()
		
		BigInteger number = new BigInteger(1,digest)
		
	    if(user.password == number.toString(16)){
			
			md.update(params.password2.toString().getBytes()) 
			digest = md.digest();
			number = new BigInteger(1,digest);
			user.password = number.toString(16)	    	
	    	user.save()
	    	
	    	render 'true'
	    }
	    else{
	    
	    	render 'false'
	    }
	    
	}

	def changepincode = {
		
		def user = User.get(params.user_id);	
		
		def md = MessageDigest.getInstance("MD5")
		
		md.update(params.pincode1.toString().getBytes()) 
		
		byte[] digest = md.digest()
		
		BigInteger number = new BigInteger(1,digest)
		
	    if(user.pincode == number.toString(16)){
			
			md.update(params.pincode2.toString().getBytes()) 
			digest = md.digest();
			number = new BigInteger(1,digest);
			user.pincode = number.toString(16)	    	
	    	user.save()
			
	    	render 'true'
	    }
	    else{
	    
	    	render 'false'
	    }
	    
	}

	def verifypincode = {
		
		def user = User.get(params.user_id)
		
		def md = MessageDigest.getInstance("MD5")
		
		md.update(params.pincode.toString().getBytes()) 
		
		byte[] digest = md.digest()
		
		BigInteger number = new BigInteger(1,digest)
		
	    if(user.pincode == number.toString(16)){
			
	    	render 'true'
	    }
		else{
			
	    	render 'false'
	    }
	    
	}	
}