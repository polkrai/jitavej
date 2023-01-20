class Unit {
	String name
	boolean deleted
	
	static mapping = {
		table 'finance.sr_item_unit'
		id generator: 'identity' 		
		version false
		columns {
			name column:'name'
			deleted column:'deleted'
		}			
	}
	
}
