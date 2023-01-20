class LabItemValue {
	Item item
	LabValue labValue
	int order

	static constraints = {
		labValue(nullable: true)
		item(nullable: true)
	}
	
	static mapping = {
		table 'lab.neural_lab_item_value'	
		version false
		columns {
			item column:'item_id'
			labValue column:'lab_value_id'
			order column:'order2'
		}			
	}
	
}
