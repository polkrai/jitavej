import grails.converters.deep.JSON

import java.sql.Timestamp
import java.util.HashMap

import net.sf.jasperreports.engine.JRException
import net.sf.jasperreports.engine.JRExporter
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JRExporterParameter
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.engine.export.*;
import java.util.Locale;

class ReferController {
	def scaffold = Appointment

	def printrefersend = {
		
			Locale.setDefault(new Locale("th", "TH"));	
			
			def jasperFile = servletContext.getResource("/reports/refer1.jasper")
			//JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(parameters.inputCollection);
		
			InputStream input = jasperFile.openStream()
		
			def refer = ReferSend.get(params.refersend_id)
		
			params.no = refer.no
			params.to = refer.to
			params.near = refer.near
			params.patient = refer.patient.title +' '+ refer.patient.firstname +' '+refer.patient.lastname
			params.sex = refer.patient.sex
			params.age = new Date().getYear() - refer.patient.birthdate.getYear()
			
			def address = Address.findByHn(refer.patient.hn)
			
			params.ban = address.ad_ban
			params.moo = address.ad_moo
			params.road = address.ad_road
			params.tambon = address.ad_tambol
			params.amphur = address.ad_ampher
			params.province = address.ad_province
			
			params.doctor = refer.user.title2 +' '+ refer.user.firstname +' '+ refer.user.lastname
			params.date = refer.date //.plus(1)
			params.for1 = refer.for1
			params.for2 = refer.for2		
			params.for3 = refer.for3		
			params.for4 = refer.for4		
			params.historypass = refer.historypass
			params.historynow = refer.historynow		
			params.examination = refer.examination	
			params.diagnosis = refer.diagnosis	
			params.treatment = refer.treatment	
			params.cause = refer.cause	
			params.comment = refer.comment
			
			params.police = refer.police
			params.nopolice = refer.nopolice
			
			JRExporter exporter = new JRPdfExporter()
			ByteArrayOutputStream byteArray = new ByteArrayOutputStream()
			JasperPrint jasperPrint = JasperFillManager.fillReport(input, params)

			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteArray)
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint)
			exporter.exportReport()			
			
			response.setHeader("Content-disposition", "inline; filename=app.pdf");
			response.contentType = "application/pdf"
			//response.outputStream << reportService.generateReport(reportFile,params).toByteArray()			

			response.outputStream << byteArray.toByteArray()			
			Locale.setDefault(new Locale("en", "US"));	
		}
	
	def printreferreply = {
		
		Locale.setDefault(new Locale("th", "TH"));	
		def jasperFile = servletContext.getResource("/reports/refer2.jasper")
		//JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(parameters.inputCollection);
	
		InputStream input = jasperFile.openStream()
	
		def refer = ReferReply.get(params.referreply_id)
	
		params.no = refer.no
		params.to = refer.to
		params.patient = refer.patient.title +' '+ refer.patient.firstname +' '+refer.patient.lastname
		params.sex = refer.patient.sex
		params.age = new Date().getYear() - refer.patient.birthdate.getYear()

		params.doctor = refer.user.title2 +' '+ refer.user.firstname +' '+ refer.user.lastname
		params.date = refer.date //.plus(1)
	
		params.examination = refer.examination	
		params.diagnosis = refer.diagnosis	
		params.treatment = refer.treatment	

		params.comment = refer.comment

		JRExporter exporter = new JRPdfExporter()
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream()
		JasperPrint jasperPrint = JasperFillManager.fillReport(input, params)

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteArray)
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint)
		exporter.exportReport()			
		
		response.setHeader("Content-disposition", "inline; filename=app.pdf");
		response.contentType = "application/pdf"
		//response.outputStream << reportService.generateReport(reportFile,params).toByteArray()			

		response.outputStream << byteArray.toByteArray()			
		Locale.setDefault(new Locale("en", "US"));	
	}

    def lastdrugs = {
	    	def icd10s = []
	    	
		    def c1 = Order.createCriteria()
	    	def orders = c1.list {
		    	   eq('patient', Patient.get(params.patient_id))
		    	   eq('type', 'drug')
		    	   eq('deleted', false)
		       	   maxResults(1)
	    	       order('id', 'desc')
	    	} 

			if(orders.size > 0){
		    	def c = OrderItem.createCriteria()
		    	def orderItems = c.list {
		    			eq('order', orders.get(0))
		    	}  

		        def fooList = new ArrayList()
		        orderItems.each { orderItem ->
		            def fooMap = new HashMap()
		            fooMap.put("drug", orderItem.drug.code)
		            fooMap.put("dose", orderItem.dose.code)
		            fooMap.put("qty", orderItem.qty)
		            fooList.add(fooMap)
		        }
		        
			   	render fooList as JSON  
		   	}
		   	render ''
		}

    def lastdoctor = {
	    	def icd10s = []
	    	
		    def c1 = Order.createCriteria()
	    	def orders = c1.list {
		    	   eq('patient', Patient.get(params.patient_id))
		    	   eq('type', 'drug')
		    	   eq('deleted', false)
		       	   maxResults(1)
	    	       order('id', 'desc')
	    	} 

			if(orders.size > 0){
			   	render orders.get(0).user as JSON  
		   	}
		   	render ''
		}
	
    def lasticd10s = {
	    	def icd10s = []
	    	
		    def c1 = Order.createCriteria()
	    	def orders = c1.list {
		    	   eq('patient', Patient.get(params.patient_id))
		    	   eq('type', 'drug')
		    	   eq('deleted', false)
		       	   maxResults(1)
	    	       order('id', 'desc')
	    	} 

			if(orders.size > 0){
		    	def c = OrderIcd10.createCriteria()
		    	def orderIcd10s = c.list {
		    			eq('order', orders.get(0))
		    	}  
		    	
		    	orderIcd10s.each{
		    		icd10s.add(it.icd10)
		    	}
			   	render icd10s as JSON  
		   	}
		   	render ''
		}
	
    def putsend = {
		def referSend
		
		if(params.refer_id != null){
			referSend = ReferSend.get(params.refer_id)
		}else{
			referSend = new ReferSend();
			referSend.date = new Date()
			referSend.user = User.get(params.user_id)
			referSend.patient = Patient.get(params.patient_id)		

		}
		
		referSend.no = params.no
		referSend.to = params.to
		referSend.near = params.near
		
		if(params.for1 != null){
			referSend.for1 = true
		}
		if(params.for2 != null){
			referSend.for2 = true
		}
		if(params.for3 != null){
			referSend.for3 = true
		}
		if(params.for4 != null){
			referSend.for4 = true
		}
		
		referSend.historypass = params.historypass
		referSend.historynow = params.historynow
		referSend.examination = params.examination
		referSend.diagnosis = params.diagnosis
		referSend.treatment = params.treatment
		referSend.cause = params.cause
		referSend.comment = params.comment
		
		if(params.police != null){
			referSend.police = params.police
		}
		if(params.nopolice != null){
			referSend.nopolice = params.nopolice
		}

		referSend.save()

		render ''
    }
	
    def putreply = {

			println params.for1
				
			def referReply = new ReferReply();
			referReply.no = params.no
			referReply.to = params.to
	
			referReply.date = new Date()
			
			referReply.user = User.get(params.user_id)		
			referReply.patient = Patient.get(params.patient_id)		

			referReply.examination = params.examination
			referReply.diagnosis = params.diagnosis
			referReply.treatment = params.treatment

			referReply.comment = params.comment

			referReply.save()

			render ''
	    }
	
	def deleterefersend = {
		def referSend = ReferSend.get(params.refersend_id)
		referSend.delete()
		render ''
	}
	
	def deletereferreply = {
			def referReply = ReferReply.get(params.referreply_id)
			referReply.delete()
			render ''
		}
	
    def sendlist = {
		println "params.date > " + params.date
		def date = new Date(Long.parseLong(params.date))
	//	date.setHour(0)

		
		def user = User.get(params.user_id)		
		println "user > " + user
		def refers
		if(user == null){
			refers = ReferSend.createCriteria().list {
				   between('date', date, date+1)
				   order('date')
	    	} 

		}else{
			refers = ReferSend.createCriteria().list {
				   between('date', date, date+1)
				   eq("user", user)
				   order('date')
	    	} 
		}
	
        def fooList = new ArrayList()
        refers.each { a ->
            def fooMap = new HashMap()
            fooMap.put("id", a.id)
            fooMap.put("no", a.no)
            fooMap.put("to", a.to)
            fooMap.put("near", a.near)
            
            fooMap.put("for1", a.for1)
            fooMap.put("for2", a.for2)
            fooMap.put("for3", a.for3)
            fooMap.put("for4", a.for4)
           
            fooMap.put("historypass", a.historypass)
            fooMap.put("historynow", a.historynow)
            fooMap.put("examination", a.examination)
            fooMap.put("diagnosis", a.diagnosis)
            fooMap.put("treatment", a.treatment)
            fooMap.put("cause", a.cause)
            fooMap.put("comment", a.comment)
            
            fooMap.put("police", a.police)
            fooMap.put("nopolice", a.nopolice)
     
		
            fooMap.put("patient", a.patient.firstname +' '+ a.patient.lastname )
            fooMap.put("doctor", a.user.firstname +' '+ a.user.lastname )
            fooMap.put("date", a.date)
            fooMap.put("comment", a.comment)
            
            fooList.add(fooMap)
        }
           render fooList as JSON	 
   }
	
	   def replylist = {
			println "params.date > " + params.date
			def date = new Date(Long.parseLong(params.date))
		//	date.setHour(0)

			
			def user = User.get(params.user_id)		
			println "user > " + user
			def refers
			if(user == null){
				refers = ReferReply.createCriteria().list {
					   between('date', date, date+1)
		    	} 

			}else{
				refers = ReferReply.createCriteria().list {
					   between('date', date, date+1)
					   eq("user", user)

		    	} 
			}
		
	        def fooList = new ArrayList()
	        refers.each { a ->
	            def fooMap = new HashMap()
	            fooMap.put("id", a.id)
	            fooMap.put("no", a.no)
	            fooMap.put("patient", a.patient.firstname +' '+ a.patient.lastname )
	            fooMap.put("doctor", a.user.firstname +' '+ a.user.lastname )
	            fooMap.put("date", a.date)
	            fooMap.put("comment", a.comment)
	            fooList.add(fooMap)
	        }
	           render fooList as JSON	 
	   }
}
