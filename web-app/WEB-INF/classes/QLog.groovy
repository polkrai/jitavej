class QLog {
	String an
	String vn
	Component fromComponent
	Component toComponent
	Component sendComponent	
	Station toStation	
	User user1
	User user2
	Date addDate
	Date sendTime
	Date receiveTime
	Visit visit
	Action action
	Action action3
	
	static constraints = {
		an(nullable: true)	
		vn(nullable: true)	
		fromComponent(nullable: true)	
		toComponent(nullable: true)	
		sendComponent(nullable: true)	
		toStation(nullable: true)	
		user1(nullable: true)
		user2(nullable: true)
		addDate(nullable: true)
		sendTime(nullable: true)	
		receiveTime(nullable: true)	
		visit(nullable: true)	
		action(nullable: true)	
		action3(nullable: true)			
	
	}
	
	static mapping = {
		table 'jvkk.nano_queue_log'
		id generator: 'identity' 			
		version false
		columns {
			fromComponent column:'com_id1'
			toComponent column:'com_id2'
			sendComponent column:'com_id3'
			toStation column:'station_id'
			user1 column:'user1'
			user2 column:'user2'
			addDate column:'date_add'		
			sendTime column:'time_send'
			receiveTime column:'time_receive'
			visit column:'vn_id'
			action column:'action_id'
			action3 column:'action_id3'
		}		
	}
	
}
