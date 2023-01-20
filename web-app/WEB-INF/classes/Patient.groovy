class Patient {
	String hn
	String title
	String firstname
	String lastname
	Appointment appointment
	User owner
	String sunchart
	String sasana
	String job
	String drugerror1
	String drugerror2
	String risk
	String mederror
	String warning
	Date birthdate
	int sex
	
	static constraints = {
		hn(nullable: true)
		appointment(nullable: true)			
		owner(nullable: true)	
		risk(nullable: true)	
		warning(nullable: true)	
		birthdate(nullable: true)	
	}
	
	static mapping = {
		table 'medrec.nano_patient'
		id generator: 'identity' 		
		version false
		columns {
			title column:'pa_pre_name'
			firstname column:'pa_name'
			lastname column:'pa_lastname'
			birthdate column:'pa_birthdate'
			appointment column:'appointment_id'
			owner column:'owner_id'		
			sunchart column:'pa_origin'
			sasana column:'pa_religion'
			job column:'pa_job'
			drugerror1 column:'allergic'					
			drugerror2 column:'allergic2'	
			mederror column:'congenital'	
			sex column:'pa_sex'
		}			
	}
}
