class Group {
	String name
	String type
	String oracleId
	
	static mapping = {
		table 'lab.neural_lab_group'	
		id generator: 'identity' 		
		version false
		columns {
			oracleId column:'oracle_id'
		}	
	}
	
}
