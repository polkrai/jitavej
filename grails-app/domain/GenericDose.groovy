class GenericDose {
	Generic generic
	Dose dose
	
	static constraints = {
		generic(nullable: true)
		dose(nullable: true)
	}
	
	static mapping = {
		table 'drug.nano_drug_formal_dose'
		id generator: 'identity' 		
		version false
		columns {
			generic column:'formal_id'	
			dose column:'dose_id'
		}	
	}
	
}
