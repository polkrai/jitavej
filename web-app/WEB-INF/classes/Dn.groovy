class Dn {
	String dn
	Patient patient
	User user
	
	boolean topic1r1
	String topic1t1
	
	boolean topic2r1
	
	boolean topic3r1
	String topic3t1
	
	boolean topic4r1
	String topic4t1
	
	boolean topic5r1
	String topic5t1
	String topic5t2
	
	boolean topic6r1	
	
	boolean topic7r1
	String topic7t1
	
	boolean topic8r1
	String topic8t1
	
	boolean topic9r1
	String topic9t1
	boolean topic9c1
	boolean topic9c2
	
	boolean topic10c1
	boolean topic10c2
	boolean topic10c3
	boolean topic10c4
	boolean topic10c5
	boolean topic10c6
	String topic10t1
	
	boolean topic11r1
	String topic11t1
	
	static constraints = {
		dn(nullable: true)
		patient(nullable: true)
		user(nullable: true)
	}
	
	static mapping = {
		table 'dental.neural_dn'
		id generator: 'identity' 		
		version false
		columns {

		}			
	}
}
