import grails.converters.deep.JSON

class VisitController {

	def scaffold = Visit

	def repairitem = {

	    def c = OrderItem.createCriteria()
    	def orderitems = c.list {
	    	   isNotNull('drug')
			   isNull('item')
			   maxResults(2000)
			   //   gt('id',(long)2500000)
			//   order('id', 'desc')
    	}
    	int row = 0
    	orderitems.each { 
	    	println row +' '+it.drug.name
	    	it.item = it.drug.item
	    	it.save()
	    	row++
	     }

	     render 'ok'	 
	}

	def putimage = {
		def visit = Visit.findByVn(params.vn)
		visit.image = params.image_path
		visit.save()
		render 'ok'
	}

    def list2 = {
    	render Visit.list().encodeAsJSON()  
    }

	def ipd = {
	   	def c = Visit.createCriteria()
    	def list = c.list {
	    	   eq('patient', Patient.get(params.patient_id))
	    	   isNotNull('an')
    	       order('id', 'desc')
    	} 	
	   	
        def fooList = new ArrayList()
        list.each { visit ->
            def fooMap = new HashMap()
            fooMap.put("id", visit.id)
            fooMap.put("an", visit.an)
            fooMap.put("startdate", visit.date) 
            fooMap.put("enddate", visit.closeTime) 
            fooList.add(fooMap)
        }

        render fooList as JSON	
	}
	
	def putremed = {

		def patient = Patient.findByHn(params['hn'])	
		
    	def bb = Visit.createCriteria()
    	def visits = bb.list {
	    	   eq('patient', patient)
	       	   maxResults(1)
    	       order('id', 'desc')
    	} 
    	if(visits.size() == 1){
    		def visit = visits.get(0)
 
			def order = new Order()
			order.visit = visit
			order.patient = patient
			if(params['doctor'] != null){
				order.user = User.findByUsername(params['doctor'])
			}
			order.type = 'drug'
			order.mode = 'oneday'
			order.date = new Date(Long.parseLong(params['startdate']))
			order.remedEndDate = new Date(Long.parseLong(params['enddate']))			
			order.paid = true
			order.stop = true
			order.remed = true
			order.oracleId = params.oracle_id
			order.save()	
			
	
			/////////////////////////////////////////////		
			def itemcount = Integer.parseInt(params.itemcount)
			for(int i=1; i<=itemcount; i++){	
				def drugname = params['drug'+i]
	
		    	def c = Drug.createCriteria()
		    	def list = c.list {
		 	    	or {
			    		 eq('oracle1', drugname)
			    		 eq('oracle2', drugname)
			    		 eq('oracle3', drugname)
			    		 eq('oracle4', drugname)
			    		 eq('oracle5', drugname)
			    		 eq('oracle6', drugname)
			    		 eq('oracle7', drugname)
			    		 eq('oracle8', drugname)
			    		 eq('oracle9', drugname)
			    		 eq('oracle10', drugname)	    		 
			    	}    	      
		
		    	} 
		    	
				def drug = null
				
				if(list.size() > 0){
					drug = list.get(0)
				}
			
				if(drug == null){
					drug = Drug.get(1)
				}
	
			//	println drug.name
				def orderItem = new OrderItem();
				orderItem.order = order
				orderItem.visit = visit
				orderItem.type = order.type
				orderItem.date = order.date
				orderItem.startDate = order.date
				orderItem.endDate = order.date
				orderItem.check_start_date = order.date
				orderItem.check_end_date = order.date	
				orderItem.drug = drug
				orderItem.item = drug.item	
				
				orderItem.dose = Dose.findByOracleId(params['dose'+i])
	
				if(drug.id != 1){
					orderItem.doseText = orderItem.dose.code
				}else{
					orderItem.doseText = params['drugtext'+i] +' ('+ orderItem.dose.code + ')'
				}
							
				orderItem.time = ''
				orderItem.frequency = 0
				orderItem.pertime = 0
				orderItem.med_status = 1
				orderItem.drug_status = 0
				orderItem.med_comment = ''
				orderItem.drug_comment = ''					
				orderItem.qty = Double.parseDouble(params['qty'+i])
				orderItem.price = 0
				orderItem.save()		
			} 
 
    	}

		render 'tomcat'
	}
	
		
	def putalloracle = {

		def date = new Date().getTime()
		
//println "00000 = "	 + date

		def patient = Patient.findByHn(params['hn'])	
		
		def visit = new Visit()
		visit.vn = params['vn']
		visit.an = ''
		visit.user = User.findByUsername(params['medrec'])
		visit.date = new Date(Long.parseLong(params['date']))
		visit.closeTime = visit.date
		visit.privilege = Privilege.findByOracleId(params['sit'])
		visit.status = 1
		visit.patient = patient
		visit.hn = params['hn']
		visit.oracleId = params['vn']
		visit.closed = true
		visit.save()
	
		def order = new Order()
		order.visit = visit
		order.patient = patient
		if(params['med'] != null){
			order.user = User.findByUsername(params['med'])
		}
		order.type = 'drug'
		order.mode = 'oneday'
		order.date = visit.date
		order.paid = true
		order.stop = true
		order.remed = false
		order.oracleId = params.vn1
		order.save()	
		
//println "111111 = "	+  (new Date().getTime() - date)

		///////////////////////////////////////////////
		def icd10count = Integer.parseInt(params.icd10count)
		for(int k=1; k<=icd10count; k++){	
			def ordericd10 = new OrderIcd10()
			ordericd10.order = order
			ordericd10.visit = visit
			def icd10 = Icd10.findByCode(params['icd10'+k])
			if(icd10 == null){
				icd10 = new Icd10()
				icd10.code = params['icd10'+k]
				icd10.save()
			}
			ordericd10.icd10 = icd10
			ordericd10.code = icd10.code
			ordericd10.date = order.date
			ordericd10.save()
		}
		
//println "222222 = "	+  (new Date().getTime() - date)
		
		/////////////////////////////////////////////		
		def itemcount = Integer.parseInt(params.itemcount)
		for(int i=1; i<=itemcount; i++){	
			def drugname = params['drug'+i]

	    	def c = Drug.createCriteria()
	    	def list = c.list {
	 	    	or {
		    		 eq('oracle1', drugname)
		    		 eq('oracle2', drugname)
		    		 eq('oracle3', drugname)
		    		 eq('oracle4', drugname)
		    		 eq('oracle5', drugname)
		    		 eq('oracle6', drugname)
		    		 eq('oracle7', drugname)
		    		 eq('oracle8', drugname)
		    		 eq('oracle9', drugname)
		    		 eq('oracle10', drugname)	    		 
		    	}    	      
	
	    	} 
	    	
			def drug = null
			
			if(list.size() > 0){
				drug = list.get(0)
			}
		
			if(drug == null){
				drug = Drug.get(1)
			}
			
//println "33333 = "	+  (new Date().getTime() - date)
			
		//	println drug.name
			def orderItem = new OrderItem();
			orderItem.order = order
			orderItem.visit = visit
			orderItem.type = order.type
			orderItem.date = order.date
			orderItem.startDate = order.date
			orderItem.endDate = order.date
			orderItem.check_start_date = order.date
			orderItem.check_end_date = order.date	
			orderItem.drug = drug
			orderItem.item = drug.item	
			
			orderItem.dose = Dose.findByOracleId(params['dose'+i])
//println "353535 = "	+  (new Date().getTime() - date)

			if(drug.id != 1){
				orderItem.doseText = orderItem.dose.code
			}else{
				orderItem.doseText = params['drugtext'+i] +' ('+ orderItem.dose.code + ')'
			}
			
			
			orderItem.time = ''
			orderItem.frequency = 0
			orderItem.pertime = 0
		//	orderItem.item = null
			orderItem.med_status = 1
			orderItem.drug_status = 0
			orderItem.med_comment = ''
			orderItem.drug_comment = ''					
			orderItem.qty = Double.parseDouble(params['qty'+i])
			orderItem.price = Double.parseDouble(params['price'+i])
			orderItem.save()		
			
			//println "444444 = "	+  (new Date().getTime() - date)

			def record = new Record()
			record.orderItem = orderItem
			record.visit = visit
			record.date = order.date
			record.qty = Double.parseDouble(params['qty'+i])
			record.price = Double.parseDouble(params['price'+i])
			record.save()					
			
			//println "555555 = "	+  (new Date().getTime() - date)			

		}

		render 'tomcat'
	}
	
	def putoracle = {
		for(int i=1; i<=10; i++){
			
			def patient = Patient.findByHn(params['hn'+i])	
			
			def visit = new Visit()
			visit.vn = params['vn'+i]
			visit.an = ''
			visit.user = User.findByUsername(params['medrec'+i])
			visit.date = new Date(Long.parseLong(params['date'+i]))
			visit.closeTime = visit.date
			visit.privilege = Privilege.findByOracleId(params['sit'+i])
			visit.status = 1
			visit.patient = patient
			visit.hn = params['hn'+i]
			visit.oracleId = params['vn'+i]
			visit.closed = true
			visit.save()
		
			def order = new Order()
			order.visit = visit
			order.patient = patient
			if(params['med'+i] != null){
				order.user = User.findByUsername(params['med'+i])
			}
			order.type = 'drug'
			order.mode = 'oneday'
			order.date = visit.date
			order.paid = true
			order.stop = true
			order.remed = false
			order.oracleId = params.vn1
			order.save()	
		}
		render ''
	}	


	
	def putitemoracle = {
			
		def drug1 = Drug.get(1)	
		def item1 = Item.get(1)	
			
		for(int i=1; i<=10; i++){
			
			if(params['prscno'+i] != null){
				
				def order = Order.findByOracleId(params['prscno'+i])	
				if(order == null){
					println params['prscno'+i] +" is NULL"
				}else{
					def visit = order.visit
					
					def orderItem = new OrderItem();
					orderItem.order = order
					orderItem.visit = visit
					orderItem.type = order.type
					orderItem.date = order.date
					orderItem.startDate = order.date
					orderItem.endDate = order.date
					orderItem.check_start_date = order.date
					orderItem.check_end_date = order.date	
					orderItem.drug = drug1	
					orderItem.item = item1	
					orderItem.dose = null
					orderItem.doseText = params['dosetext'+i]
					orderItem.time = ''
					orderItem.frequency = 0
					orderItem.pertime = 0
					orderItem.item = null
					orderItem.med_status = 1
					orderItem.drug_status = 0
					orderItem.med_comment = ''
					orderItem.drug_comment = ''					
					orderItem.qty = Double.parseDouble(params['qty'+i])
					orderItem.price = Double.parseDouble(params['price'+i])
					orderItem.save()		

					def record = new Record()
					record.orderItem = orderItem
					record.visit = visit
					record.date = order.date
					record.qty = Double.parseDouble(params['qty'+i])
					record.price = Double.parseDouble(params['price'+i])
					record.save()					
				}
			}
		}

		render ''
	}	
	
	def editimg = {
			def b = Visit.createCriteria()
	    	def visits = b.list {
		    	ge('id', new Long(860985))
		    	le('id', new Long(1000000))
		    	isNotNull('image')
		    //	eq('deleted', false)
		    //	order('id', 'asc')
	    	} 
			println visits.size
			visits.each{
				def year = it.date.toString().substring(0,4)
				def month = it.date.toString().substring(5,7)
				it.image = '/nano/opd_scan/'+year+'/'+year +'_'+month+'/'+it.vn+ '.jpg'
				
			//	it.save()
			}
			render 'ok'
	}
	
	
}
