class Order {
	Visit visit
	Patient patient
	User user
	String type
	String mode
	Date date
	Date stopDate	
	Date remedEndDate
	boolean remed
	boolean stop
	boolean paid
	boolean deleted
	String review
	String oracleId
	String medtext
	boolean is_approve
	String scanLabResult

	static constraints = {
		visit(nullable: true)
		patient(nullable: true)
		user(nullable: true)
		date(nullable: true)
		stopDate(nullable: true)
		type(nullable: true)
		remedEndDate(nullable: true)
		review(nullable: true)
		oracleId(nullable: true)
		medtext(nullable: true)
		scanLabResult(nullable: true)
	}
	
	static mapping = {
		table 'med.neural_order'
		id generator: 'identity' 		
		version false
		columns {
			visit column:'vn_id'
			patient column:'pa_id'
			user column:'user_id'
			date column:'order_date'
			stopDate column:'stop_date'
			type column:'order_type'
			remedEndDate column:'remed_end_date'
			review column:'review'	
			medtext column:'med_comment'	
			oracleId column:'oracle_id'
			scanLabResult column:'path_scan_labresult'
		}
	}
	
}