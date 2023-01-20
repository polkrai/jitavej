class Record {

	Visit visit
	OrderItem orderItem
	Date date
	int qty
	User labApproveUser
	Date labApproveDate
	LabExternal labExternal
	double price
	
	static constraints = {
		visit(nullable: true)
		orderItem(nullable: true)
		labApproveUser(nullable: true)
		labApproveDate(nullable: true)
		labExternal(nullable: true)
	}
	
	static mapping = {
		table 'med.neural_record'
		id generator: 'identity' 			
		version false
		columns {
			visit column:'vn_id'
			orderItem column:'order_item_id'
			date column:'created_date'
			labApproveUser column:'lab_approve_user_id'			
			labApproveDate column:'lab_approve_date'
			labExternal column:'lab_external_id'			
		}
	}
	
}
