class Action {
	Component component
	String code
	String name
	int order

	static constraints = {
		component(nullable: true)			
		name(nullable: true)	
		code(nullable: true)
	}
	
	static mapping = {
		table 'jvkk.neural_action'
		id generator: 'identity' 			
		version false
		columns {
			component column:'com_id'		
			code column:'code'
			name column:'name'
			order column:'order2'
		}		
	}
	
}
