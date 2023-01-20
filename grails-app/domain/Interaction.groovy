class Interaction {

	Generic generic1
	Generic generic2
	String comments
	String notorder
	boolean deleted
	
	static constraints = {
		generic1(nullable: true)
		generic2(nullable: true)
		comments(nullable: true)
		notorder(nullable: true)	
	}
	
	static mapping = {
		table 'drug.nano_drug_interaction'
		id generator: 'identity' 			
		version false
		columns {
			generic1 column:'drug_formal_id1'
			generic2 column:'drug_formal_id2'
			comments column:'comment'
			notorder column:'not_order'
		}			
	}
	
}
