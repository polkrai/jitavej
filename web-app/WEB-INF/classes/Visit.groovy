class Visit {
	Privilege privilege
	Patient patient
	String hn
	String an
	String vn
	String status
	Date date
	Date closeTime
	User user
	boolean closed
	String oracleId
	String image
	
    static constraints = { 
		vn(nullable:true)
		an(nullable:true)
		hn(nullable:true)
		status(nullable:true)
		patient(nullable:true)
		date(nullable:true)
		closeTime(nullable:true)
		privilege(nullable:true)
		user(nullable: true)
		closed(nullable: true)	
		oracleId(nullable: true)	
		image(nullable: true)
	} 
	
	static mapping = {
		table 'medrec.nano_visit'
		id generator: 'identity' 		
		version false
		columns {
			status column:'status_close'
			date column:'time_add'
			patient column:'id_patient'
			closeTime column:'time_toclose'
			privilege column:'privilege_id'
			user column:'user_id'	
			oracleId column:'oracle_id'		
			image column:'image_path'			
		}		
	
	}
	
}
