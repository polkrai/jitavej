class Item {
	String code
	String name
	Group group
	int group_id
	double price
	Unit unit
	boolean deleted
	String oracleId
	boolean ipd
	
	static constraints = {
		code(nullable: true)
		name(nullable: true)
		group(nullable: true)
		group_id(nullable: true)
		price(nullable: true)
		unit(nullable: true)
		deleted(nullable: true)
		oracleId(nullable: true)
		ipd(nullable: true)

	}
	
	static mapping = {
		table 'finance.sr_item'	
		id generator: 'identity' 		
		version false
		columns {
			code column:'item_code'		
			group column:'lab_group_id'
			oracleId column:'oracle_id'
			group_id column:'group_id'
		}			
	}
	
}
