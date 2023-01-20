class DrugError {
	Patient patient
	Generic generic
	String comment

	static constraints = {
		patient(nullable: true)
		generic(nullable: true)
		comment(nullable: true)		

	}
	
	static mapping = {
		table 'drug.nano_patient_allergic'
		id generator: 'identity' 			
		version false
		columns {
			patient column:'pa_id'
			generic column:'drug_formal_id'			
			comment column:'description'
		}			
	}
	
}
