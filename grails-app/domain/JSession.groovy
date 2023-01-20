class JSession {
	String ip
	String loginTime
	String expireTime
	String logoutTime
	String status
	String dateText
	User user
	String sessionId
	
	static constraints = {
		ip(nullable: true)
		loginTime(nullable: true)
		expireTime(nullable: true)
		logoutTime(nullable: true)
		status(nullable: true)
		dateText(nullable: true)
		user(nullable: true)
		sessionId(nullable: true)		
	}
	
	static mapping = {
		table 'jvkk.nano_session'
		id generator: 'identity' 			
		version false
		columns {
			ip column:'session_ip_address'
			loginTime column:'session_time_login'
			expireTime column:'session_time_expire'
			logoutTime column:'session_time_logout'
			status column:'session_status'
			dateText column:'session_date_login'
			user column:'session_user_id'
			sessionId column:'id_sess'
			
		}		
	}
	
}
