class Icd10tm {
	String icd10tm
	String icd9
	String name

	static constraints = {
		icd10tm(nullable: true)			
		icd9(nullable: true)	
		name(nullable: true)
	}
	
	static mapping = {
		table 'med.neural_icd10tm'
		id generator: 'identity' 			
		version false	
	}
	
}
