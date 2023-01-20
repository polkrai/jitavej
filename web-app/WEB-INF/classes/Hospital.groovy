class Hospital {
	String code
	String name
	
	static mapping = {
		table 'medrec.nano_hospital_code'	
		id generator: 'identity' 		
		version false
		columns {
			id column:'h_id'
			code column:'h_code'
			name column:'h_name'
		}			
	}
	
}
