class Approve {
	String approve_user
	String lab_type
	String scan_labresult
	Order order
	
	static constraints = {
		approve_user(nullable: true)
		lab_type(nullable: true)
		scan_labresult(nullable: true)
		order(nullable: true)
	}
	
	static mapping = {
		table 'lab.lab_approve'
		id generator: 'identity' 		
		version false
		columns {
			approve_user column:'lab_approve_user_id'
			lab_type column:'labtype'
			scan_labresult column:'path_scan_labresult'
			order column:'lab_order'
			
		}
	}
}
