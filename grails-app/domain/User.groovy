class User {
	String title2
	String firstname
	String lastname
	String username
	String password
	String pincode
	UserGroup userGroup
	String status
	String email
	String type
	boolean active
	Building building
	
	static constraints = {
		title2(nullable: true)
		status(nullable: true)
		active(nullable: true)
		firstname(nullable: true)
		lastname(nullable: true)
		username(nullable: true)
		password(nullable: true)		
		pincode(nullable: true)		
		userGroup(nullable: true)	
		email(nullable: true)
		type(nullable: true)
		building(nullable: true)
	}
	
	static mapping = {
		table 'jvkk.nano_user'
		id generator: 'identity' 		
		version false
		columns {
			title2 column:'title'
			firstname column:'name'
			lastname column:'lastname'
			password column:'user_password'
			userGroup column:'group_id'
			building column:'building_id'
		}		
	}	
}
