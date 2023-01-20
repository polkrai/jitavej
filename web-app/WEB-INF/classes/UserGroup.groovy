class UserGroup {
	//String id
	String name
	String oracleId
	
	static mapping = {
		table 'jvkk.neural_user_group'
		id generator: 'identity' 		
		version false
		columns {
			//id column:'id'
			name column:'name'
			oracleId column:'oracle_id'
		}		
	}	
}
