class Drug {
	String code
	String name
	String account
	Item item
	Generic generic
	String oracle1
	String oracle2
	String oracle3
	String oracle4
	String oracle5
	String oracle6
	String oracle7
	String oracle8
	String oracle9
	String oracle10
	int status
	boolean drug_from_home
	
	static constraints = {
		item(nullable: true)
		generic(nullable: true)
		account(nullable: true)
		oracle1(nullable: true)		
		oracle2(nullable: true)		
		oracle3(nullable: true)		
		oracle4(nullable: true)				
		oracle5(nullable: true)		
		oracle6(nullable: true)		
		oracle7(nullable: true)		
		oracle8(nullable: true)		
		oracle9(nullable: true)		
		oracle10(nullable: true)	
		status(nullable: true)
		drug_from_home(nullable: true)
	}
	
	static mapping = {
		table 'drug.nano_drug'
		id generator: 'identity' 			
		version false
		columns {
			code column:'initial_name'
			name column:'generic_name'
			account column:'account_type'
			item column:'item_id'
			generic column:'formal_group_id'
			oracle1 column:'oracle_id1'		
			oracle2 column:'oracle_id2'		
			oracle3 column:'oracle_id3'		
			oracle4 column:'oracle_id4'		
			oracle5 column:'oracle_id5'		
			oracle6 column:'oracle_id6'		
			oracle7 column:'oracle_id7'		
			oracle8 column:'oracle_id8'		
			oracle9 column:'oracle_id9'		
			oracle10 column:'oracle_id10'	
		}			
	}
	
}