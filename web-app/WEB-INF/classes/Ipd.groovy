class Ipd {
	Building building
	String an

	static constraints = {
		building(nullable: true)
		an(nullable: true)
	}
	
	static mapping = {
		table 'ipd.mi_ipd_main'
		id generator: 'identity' 		
		version false
		columns {
			id column: 'auto_id'
			an column: 'an'
			building column: 'building'
		}
	}			

}
