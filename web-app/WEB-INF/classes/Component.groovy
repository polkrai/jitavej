class Component {
	String name
	String link
	String status
	String type

	static constraints = {
		link(nullable: true)
		status(nullable: true)
		type(nullable: true)
	}
	
	static mapping = {
		table 'jvkk.nano_components'
		id generator: 'identity' 			
		version false
		columns {
			name column:'com_name'
			link column:'com_link'
			status column:'com_status'
			type column:'type'
		}		
	}
	
}
