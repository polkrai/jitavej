class Stock {
	Drug drug
	int number
	int buffer
	
	static constraints = {
		drug(nullable: true)
		
	}
	
	static mapping = {
		table 'drug.nano_drugstore'
		id generator: 'identity' 			
		version false
		columns {
			drug column:'drug_id'
			number column:'value'
			buffer column:'buffer'
		}			
	}
	
}
