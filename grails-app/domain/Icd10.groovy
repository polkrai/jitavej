class Icd10 {
	String code
	String name
	boolean deleted
	
	static mapping = {
		table 'med.neural_icd10'
		id generator: 'identity' 		
		version false
		columns {
			code column:'code'
			name column:'name'
		}		
	}
}
