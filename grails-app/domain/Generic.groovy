class Generic {
	String name
	boolean risk
	
	static constraints = {
		name(nullable: true)
	}
	
	static mapping = {
		table 'drug.nano_drug_formal'
		id generator: 'identity' 		
		version false
		columns {

		}			
	}
	
}
