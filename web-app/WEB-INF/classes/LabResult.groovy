class LabResult {
	Record record
	LabValue labValue
	String standard	
	String result	
	int order
	
	static constraints = {
		record(nullable: true)
		labValue(nullable: true)
		standard(nullable: true)
		result(nullable: true)
	}
	
	static mapping = {
		table 'lab.neural_lab_result'
		id generator: 'increment' 		
		version false
		columns {
			record column:'record_id'
			visit column:'lab_value_id'
			order column:'order2'
		}
	}
	
}
