class Dose {
	String code
	String name1
	String name2
	String name3
	String name4
	double pertime
	double frequency
	String time
	boolean deleted
	String oracleId
	
	static constraints = {
		name1(nullable: true)
		name2(nullable: true)
		name3(nullable: true)
		name4(nullable: true)		
		time(nullable: true)
		deleted(nullable: true)		
		oracleId(nullable: true)
	}
	
	static mapping = {
		table 'drug.nano_drug_dose'
		id generator: 'identity' 			
		version false
		columns {
			code column:'dose_med_name'		
			pertime column:'quantity'	
			oracleId column:'oracle_id'				
		}	
	}
	
}
