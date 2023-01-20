class ReferSend {
	Patient patient
	User user
	String no
	Date date
	String to
	String near
	boolean for1
	boolean for2
	boolean for3
	boolean for4
	String historypass
	String historynow
	String examination
	String diagnosis
	String treatment
	String cause
	String comment
	boolean police
	boolean nopolice
	
	static constraints = {
		patient(nullable: true)
		user(nullable: true)
		no(nullable: true)		
		date(nullable: true)		
		to(nullable: true)		
		near(nullable: true)
		for1(nullable: true)	
		for2(nullable: true)			
		for3(nullable: true)			
		for4(nullable: true)	
		historypass(nullable: true)	
		historynow(nullable: true)			
		examination(nullable: true)			
		diagnosis(nullable: true)			
		treatment(nullable: true)		
		cause(nullable: true)
		comment(nullable: true)		
		police(nullable: true)
		nopolice(nullable: true)			
	}
	
	static mapping = {
		table 'frontmed.neural_refer_send'
		id generator: 'identity' 		
		version false
		columns {
			patient column:'patient_id'
			user column:'user_id'
			to column:'to2'
		}		
	}
	
}
