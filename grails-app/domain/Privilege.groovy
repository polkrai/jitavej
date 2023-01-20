class Privilege {
	String name
	String privileged
	boolean deleted
	String oracleId
	
    static constraints = { 
		oracleId(nullable:true)
	} 
	
	static mapping = {
		table 'finance.sr_privilege'
		id generator: 'identity' 			
		version false
		columns {
			oracleId column:'oracle_id'	
		}		
	
	}
	
}
