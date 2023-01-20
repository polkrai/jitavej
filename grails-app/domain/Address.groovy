class Address {
	String hn
	String ad_ban
	String ad_moo
	String ad_road
	String ad_tambol
	String ad_ampher
	String ad_province
	String type 
	
	static constraints = {
		hn(nullable: true)
	}
	
	static mapping = {
		table 'medrec.nano_patient_address'
		id generator: 'identity'		
		version false
		columns {
			type column:'ad_type_address'	
		}			
	}
}
