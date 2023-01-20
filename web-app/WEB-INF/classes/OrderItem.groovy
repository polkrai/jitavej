class OrderItem {
	Order order
	Visit visit
	User user
	String type
	Drug drug
	Dose dose
	String doseText
	String time
	Item item
	String reason
	Date startDate
	Date endDate
	Date check_start_date
	Date check_end_date
	Date date
	double frequency
	double pertime
	double balance
	double qty
	double price
	boolean stop
	boolean deleted
	boolean unit_dose
	int med_status
	int drug_status
	String med_comment
	String drug_comment	
	String tooth
	String surfaces

	static constraints = {
		order(nullable: true)
		visit(nullable: true)
		user(nullable: true)
		drug(nullable: true)
		dose(nullable: true)
		item(nullable: true)
		reason(nullable: true)
		unit_dose(nullable: true)
		startDate(nullable: true)
		endDate(nullable: true)
		date(nullable: true)
		type(nullable: true)
		med_comment(nullable: true)
		drug_comment(nullable: true)
		doseText(nullable: true)
		time(nullable: true)		
		tooth(nullable: true)
		surfaces(nullable: true)				
	}
	
	static mapping = {
		table 'med.neural_order_item'
		id generator: 'identity' 		
		version false
		columns {
			order column:'order_id'
			visit column:'vn_id'
			user column:'user_id'	
			drug column:'drug_id'	
			dose column:'dose_id'	
			item column:'item_id'	
			startDate column:'start_date'	
			endDate column:'end_date'	
			date column:'date_add'		
			doseText column:'dose'	
			time column:'time'	
			tooth column:'tooth'	
			surfaces column:'surfaces'						
		}
	}
	
}
