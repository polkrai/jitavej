class ReferReply {
	Patient patient
	User user
	String no
	Date date
	String to
	String examination
	String diagnosis
	String treatment
	String comment
	
	static constraints = {
		patient(nullable: true)
		user(nullable: true)
		no(nullable: true)		
		date(nullable: true)		
		to(nullable: true)		
		examination(nullable: true)			
		diagnosis(nullable: true)			
		treatment(nullable: true)		
		comment(nullable: true)
	
	}
	
	static mapping = {
		table 'frontmed.neural_refer_reply'
		id generator: 'identity' 		
		version false
		columns {
			patient column:'patient_id'
			user column:'user_id'
			to column:'to2'
		}		
	}
	
}
