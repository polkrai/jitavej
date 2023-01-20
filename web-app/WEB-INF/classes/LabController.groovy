import org.codehaus.groovy.grails.web.json.JSONObject
import grails.converters.*
import grails.converters.deep.JSON
import java.sql.Connection;
import net.sf.jasperreports.engine.JRException
import net.sf.jasperreports.engine.JRExporter
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JRExporterParameter
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.util.JRProperties;

class LabController {
	
	def scaffold = LabValue
	
	def sessionFactory 

	def printreport = {
		
		def jasperFile = servletContext.getResource("/reports/lab.jasper")
		//JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(parameters.inputCollection);
	
		InputStream input = jasperFile.openStream()
	
		def order = Order.get(params.order_id)
	
		params.hn = order.visit.patient.hn
		params.patient = order.visit.patient.title + " " + order.visit.patient.firstname + " " + order.visit.patient.lastname
		params.year = new Date() - order.visit.patient.birthdate.getYear()
		//params.doctor = order.user.firstname +" "+ order.user.lastname
		
		params.vn = order.visit.vn
		
		if(order.visit.an != null){
		
			params.an = order.visit.an
			params.building = Ipd.findByAn(order.visit.an).building.name
		}
		
		params.date = order.date
		params.order_id = (int)order.id
	
		def hibernateSession = sessionFactory.currentSession
		def hibernateConnection = hibernateSession.connection() 
   
		JRExporter exporter = new JRPdfExporter();
		
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(input, params, hibernateConnection)

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteArray)
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint)
		exporter.exportReport()			
				
		//response.setHeader("Content-disposition", "inline; filename=app.pdf")
		//response.contentType = "application/pdf"
		
		
		response.setHeader "Content-disposition", "inline; filename=app.pdf"
		response.contentType = 'application/pdf'
		
		//response.outputStream << reportService.generateReport(reportFile,params).toByteArray()			

		response.outputStream << byteArray.toByteArray()
		response.outputStream.flush()

	}
	
	def printreport2 = {
	
		def jasperFile = servletContext.getResource("/reports/lab2.jasper")

		InputStream input = jasperFile.openStream()
	
		def order = Order.get(params.order_id)
	
		params.hn = order.visit.patient.hn
		params.patient = order.visit.patient.title +" "+ order.visit.patient.firstname +" "+order.visit.patient.lastname
		params.year = new Date().getYear() - order.visit.patient.birthdate.getYear()
		//params.doctor = order.user.firstname +" "+ order.user.lastname
		params.vn = order.visit.vn
		params.date = order.date
		params.order_id = (int)order.id
		
		if(order.visit.an != null){
			params.an = order.visit.an
			params.building = Ipd.findByAn(order.visit.an).building.name
		}
	
		def hibernateSession = sessionFactory.currentSession 
		def hibernateConnection = hibernateSession.connection() 
   
		JRExporter exporter = new JRPdfExporter()
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream()
		JasperPrint jasperPrint = JasperFillManager.fillReport(input, params, hibernateConnection)

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteArray)
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint)
		exporter.exportReport()			
				
		response.setHeader("Content-disposition", "inline; filename=app.pdf");
		response.contentType = "application/pdf"
		//response.outputStream << reportService.generateReport(reportFile,params).toByteArray()			

		response.outputStream << byteArray.toByteArray()			

	}
	
	
	def printreport3 = {
	
        def jasperFile = servletContext.getResource("/reports/jasper_report_template.jasper")
		//JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(parameters.inputCollection);
	
		InputStream input = jasperFile.openStream()
	
		def order = Order.get(params.order_id)
	
		params.hn = order.visit.patient.hn
		params.patient = order.visit.patient.title +" "+ order.visit.patient.firstname +" "+order.visit.patient.lastname
		params.year = new Date().getYear() - order.visit.patient.birthdate.getYear()
		//params.doctor = order.user.firstname +" "+ order.user.lastname
		
		params.vn = order.visit.vn
		
		if(order.visit.an != null){
		
			params.an = order.visit.an
			params.building = Ipd.findByAn(order.visit.an).building.name
		}
		
		params.date = order.date
		params.order_id = (int)order.id
	
		def hibernateSession = sessionFactory.currentSession
		def hibernateConnection = hibernateSession.connection() 
   
		JRExporter exporter = new JRPdfExporter();
		
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(input, params, hibernateConnection)

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteArray)
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint)
		exporter.exportReport()			
				
		response.setHeader("Content-disposition", "inline; filename=app.pdf");
		response.contentType = "application/pdf"
		
		//response.outputStream << reportService.generateReport(reportFile,params).toByteArray()			

		response.outputStream << byteArray.toByteArray()
    }
    
	
	def importlabvalue = {
	
		def value = new LabValue()
		value.name = params.name
		value.postfix = params.postfix
		value.standard = params.standard
		value.type = params.type
		value.oracleId = params.oracle_id
		value.chioce1 = params.helplist
		
		if(params.helplist == null || params.helplist == ''){
			
			value.chioce1 = ""
		}
		
		value.chioce2 = ""
		value.chioce3 = ""
		value.chioce4 = ""
		value.chioce5 = ""
		value.deleted = false
		value.save()
		
		render value.id
	}
		
	def importlabitemvalue = {
	
		def itemvalue = new LabItemValue()
		itemvalue.item = Item.findByOracleId(params.item_id)
		itemvalue.labValue = LabValue.findByOracleId(params.value_id)
		itemvalue.order = Integer.parseInt(params.order)
		itemvalue.save()
		render itemvalue.id
	}
	
	def putresult = {
	
		def values = JSON.parse(params.values)
		println "Class of jsonArray: ${values.class.name}"
		
		values.each {  
			def result = LabResult.get(it.getAt("id"))
			result.result = it.getAt("result")
			result.save()
		}
		
		render ''
	}
	
    def suggest = {
    
    	println "ItemService > suggest()!!!!";
    	String key = params.key
    	println key
  
    	def list = Item.findAllByNameIlike("%"+key+"%", [max:5,sort:'id']);
 
        render list.encodeAsJSON()
    }
    
    def suggestlab = {
    
    	println "ItemService > suggest()!!!!";
    	String key = params.key
    	println key
  
    	def list = Item.findAllByNameIlikeAndGroup("%"+key+"%", Group.get(4), [max:5,sort:'id']);
 
        render list.encodeAsJSON()
    }
	
    def list2 = {
    
    	println "ItemService --> list()!!cs!!";
    	def list = Item.list();
        render list.encodeAsJSON()
    }

    def valuelist = {
    
    	def c = LabValue.createCriteria()
    	def list = c.list {
    	    order('name', 'asc')
    	}
    	
        render list as JSON
	}

	def values = {
	
    	def c = LabItemValue.createCriteria()
    	def list = c.list {
    		eq("item", Item.get(params.item_id))
    		order('order', 'asc')
    	}
    	
        render list as JSON
	}
	
    def findByName = {
    
    	println "ItemService > findByName()!!p!! " + params.name;
    	def item = Item.findByName(params.name)
        render item.encodeAsJSON()
    } 
	
    def results = {
    
		println params.record_id
        def results = LabResult.findAllByRecord(Record.get(params.record_id), [sort:'id'])
 		def fooList = new ArrayList()
 		results.each { result ->
 			def fooMap = new HashMap()
 			fooMap.put("id", result.id)
 			fooMap.put("name", result.labValue.name)
 			fooMap.put("standard", result.standard)
 			fooMap.put("result", result.result.toString().trim())
			fooMap.put("ckdstagas", null)
			
			def result_trim = result.result.toString().trim();
			
			if (result.labValue.id == 3462 && result_trim != '') {
				
				double result_double = Double.parseDouble(result_trim);
				
				if (result_double > 0 && result_double < 15) {
					fooMap.put("ckdstagas", "ระยะที่ 5")
				}
				else if(result_double >= 15 && result_double <= 29){
					fooMap.put("ckdstagas", "ระยะที่ 4")
				}
				else if(result_double >= 30 && result_double <= 44){
					fooMap.put("ckdstagas", "ระยะที่ 3b")
				}
				else if(result_double >= 45 && result_double <= 59){
					fooMap.put("ckdstagas", "ระยะที่ 3a")
				}
				else if(result_double >= 60 && result_double <= 89){
					fooMap.put("ckdstagas", "ระยะที่ 2")
				}
				
			}
		
 			fooMap.put("postfix", result.labValue.postfix)
 			fooMap.put("chioce1", result.labValue.chioce1)
 			fooMap.put("chioce2", result.labValue.chioce2)
 			fooMap.put("chioce3", result.labValue.chioce3)
 			fooMap.put("chioce4", result.labValue.chioce4)
 			fooMap.put("chioce5", result.labValue.chioce5)

 			fooList.add(fooMap)
 		}

 		render fooList as JSON		 

	}    
	
    def orders = {
    
		println params.patient_id
        def c = Order.createCriteria()
    	def orders = c.list {
	    	eq('patient', Patient.get(params.patient_id))
			eq('type', 'lab')
			eq('deleted', false)
	        order('date', 'desc')
    	}
    	
        def fooList = new ArrayList()
        
		orders.each { order ->
            def fooMap = new HashMap()
            fooMap.put("id", order.id)
            fooMap.put("time", order.date)
            fooMap.put("vn", order.visit.vn)
            fooMap.put("scanresult", order.scanLabResult)
            fooList.add(fooMap)
        }

        render fooList as JSON		
	}
	
    def records = {
    
		println params.order_id
		
    	def c = Record.createCriteria()
    	
    	def list = c.list {
    	
	       orderItem {
	       
	    	   eq("order", Order.get(params.order_id))
	       }
	       			
	       order('id', 'asc')
    	} 

        def fooList = new ArrayList()
        
        list.each { record ->
            def fooMap = new HashMap()
            fooMap.put("id", record.id)
            fooMap.put("item", record.orderItem.item.name)
            fooMap.put("price", record.orderItem.item.price)
            fooList.add(fooMap)
        }

        render fooList as JSON		

	}	
	
	def report = {
	
		def startdate = new Date(Long.parseLong(params.startdate))
		startdate = startdate - 1
		def enddate = new Date(Long.parseLong(params.enddate))
		println startdate 
		println enddate
		
		def c = Item.createCriteria()
    	def list = c.list {
    	       isNotNull('group')	
    	       eq('deleted', false)
    	       //maxResults(5)
    	       order('name', 'asc')
    	} 
		
        def fooList = new ArrayList()
        list.each { item ->
            def fooMap = new HashMap()
            fooMap.put('name', item.name) 
            
	  		def c2 = OrderItem.createCriteria()
	    	def list2 = c2.list {
	    		eq('item', item)	
	    		order{
	    			eq('type', 'lab')
	    			ge('date', startdate)
	    			le('date', enddate)
	    			eq('deleted', false)
	    			//maxResults(5)
	    			
	    		}
	    	}           
          
            fooMap.put('qty', list2.size())
            
            if(list2.size() > -1){
            
            	fooList.add(fooMap)
            }
        }

        render fooList as JSON			

	}
}
