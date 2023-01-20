class Q {
	String an
	String vn
	Component fromComponent
	User user1
	Component toComponent
	Component reComponent	
	User user2
	Date sendTime
	Date receiveTime
	Visit visit
	Date addDate
	Action action
	Action action3
	String actiontext
	String actiontext3	
	String com1
	String com2
	Station fromStation
	Station toStation
	Building building
	boolean dental

	static constraints = {
		fromComponent(nullable: true)	
		toComponent(nullable: true)	
		reComponent(nullable: true)			
		user1(nullable: true)
		user2(nullable: true)
		visit(nullable: true)		
		sendTime(nullable: true)	
		receiveTime(nullable: true)	
		addDate(nullable: true)
		an(nullable: true)	
		vn(nullable: true)	
		action(nullable: true)	
		action3(nullable: true)	
		actiontext(nullable: true)	
		actiontext3(nullable: true)	
		com1(nullable: true)	
		com2(nullable: true)			
		fromStation(nullable: true)	
		toStation(nullable: true)	
		building(nullable: true)	
		dental(nullable: true)	
				
	}
	
	static mapping = {
		table 'jvkk.nano_queue'
		id generator: 'identity' 			
		version false
		columns {
			fromComponent column:'com_id1'
			toComponent column:'com_id2'
			reComponent column:'com_id3'			
			user1 column:'user1'
			user2 column:'user2'
			action column:'action_id'
			action3 column:'action_id3'		
			actiontext column:'action'
			actiontext3 column:'action3'
			sendTime column:'time_send'
			receiveTime column:'time_receive'
			visit column:'vn_id'
			addDate column:'date_add'
			fromStation column:'station_id1'
			toStation column:'station_id2'	
			building column:'building'						
		}		
	}
	
}
