class OrderIcd10 {
	Order order
	Order dental
	Visit visit
	Icd10 icd10
	String code
	Date date
	int priority
	
	static constraints = {
		order(nullable: true)
		dental(nullable: true)
		visit(nullable: true)
		icd10(nullable: true)
		date(nullable: true)	
		priority(nullable: true)	
	}
	
	static mapping = {
		table 'med.neural_order_icd10'
		id generator: 'identity' 				
		version false
		columns {
			order column:'order_id'
			dental column:'dental_id'
			visit column:'visit_id'
			item column:'icd10_id'	
		}
	}
	
}
