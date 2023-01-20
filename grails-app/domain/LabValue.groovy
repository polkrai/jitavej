class LabValue {
	String name
	String type
	String postfix
	String standard
	boolean deleted
	String oracleId
	String chioce1
	String chioce2
	String chioce3
	String chioce4
	String chioce5

	static constraints = {
		chioce1(nullable: true)
		chioce2(nullable: true)		
		chioce3(nullable: true)		
		chioce4(nullable: true)		
		chioce5(nullable: true)		
	}
	
	static mapping = {
		table 'lab.neural_lab_value'	
		version false
		columns {
			oracleId column:'oracle_id'
		}			
	}
	
}
