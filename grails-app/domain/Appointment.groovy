class Appointment {
	Component component
	Patient patient
	User user
	Date date
	//Date appointment_date
	//Date createDate
	Visit visit
	String comment

	static constraints = {
		component(nullable: true)
		patient(nullable: true)
		user(nullable: true)
		date(nullable: true)
		visit(nullable: true)		
		comment(nullable: true)		
	}
	
	static mapping = {
		table 'frontmed.neural_appointment'
		id generator: 'increment' 		
		version false
		columns {
			component column:'component_id'
			patient column:'patient_id'
			user column:'user_id'
			visit column:'visit_id'
			date column:'appointment_date'
			comment column:'comment'		
		}		
	}
	
}
