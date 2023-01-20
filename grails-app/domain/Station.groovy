class Station {
	String name
	Component component
	String mode;
	String ip;
	//String ipaddress;
	User user;
	
	static constraints = {
		name(nullable: true)
		component(nullable: true)
		mode(nullable: true)
		ip(nullable: true)
		user(nullable: true)		
	}
	
	static mapping = {
		table 'med.neural_station'
		id generator: 'identity' 		
		version false
		columns {
			component column:'component_id'
			user column:'user_id'
			//ipaddress column:'ip'
		}
	}			

}
