class Building {
	String name
	int day

	static constraints = {
		name(nullable: true)

	}
	
	static mapping = {
		table 'ipd.mi_building'
		id generator: 'identity' 		
		version false
		columns {
			id column: 'building_id'
			name column: 'building_name'
			day column: 'day'
		}
	}			

}
