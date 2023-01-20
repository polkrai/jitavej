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

class AppointmentController {

	def scaffold = Appointment

	def printreport = {
	
		Locale.setDefault(new Locale("th", "TH"));
		
		def jasperFile = servletContext.getResource("/reports/appointment.jasper")
		
	//	JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(parameters.inputCollection);
	
		InputStream input = jasperFile.openStream()
	
		def appointment = Appointment.get(params.appointment_id)
	
		params.hn = appointment.patient.hn
		params.patient = appointment.patient.title +' '+ appointment.patient.firstname +' '+appointment.patient.lastname
		
		if(appointment.user != null){
			params.doctor = appointment.user.firstname +' '+ appointment.user.lastname
		}
		else{
		
			params.doctor = ''
		}
		
		params.date = appointment.date.plus(1)
		
		if(session.jsession != null && session.jsession.user != null){
			params.user = session.jsession.user.firstname +' '+ session.jsession.user.lastname
		}

		params.comment = appointment.comment
	
		JRExporter exporter = new JRPdfExporter()
		
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream()
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(input, params)

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteArray)
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint)
		exporter.exportReport()			
		
			
		response.setHeader("Content-disposition", "inline; filename=app.pdf");
		response.contentType = "application/pdf"
	//	response.outputStream << reportService.generateReport(reportFile,params).toByteArray()			

		response.outputStream << byteArray.toByteArray()			
		Locale.setDefault(new Locale("en", "US"));	
	}

	
	
    def put = {
    
	//	println "component > " + params.component_id
		Date date = new Date(Long.parseLong(params.date))
	//	println "date > " + date
		
		def appointment = new Appointment();
		
		appointment.user = User.get(params.user_id)		
		appointment.patient = Patient.get(params.patient_id)		
		appointment.date = date
		appointment.comment = params.comment
		
		
		if(params.appointment_id != '0'){
		
			appointment = Appointment.get(params.appointment_id)
		}		
		
		if(session.mode == 'dental'){
			appointment.component = Component.get(49)
		}
		
		if(params.component_id != ''){
		
			appointment.component = Component.get(params.component_id)
			
			if(appointment.component.type == 'restore'){
				
		    	def c = Visit.createCriteria()
		    	def list = c.list {
	    	    	   eq("patient", appointment.patient)
		    	       order('id', 'desc')
		    	}  
		    	println 'list.size() ' + list.size()
		    	if(list.size() > 0){
		    		def visit = list.get(0)
		    		appointment.visit = visit
		    	}
			}
			
		}
	
		appointment.save()
		
		
		def patient = Patient.get(params.patient_id)
		patient.appointment = appointment
		patient.save()
		
		render ''
    }
	
	def delete2 = {
		def appointment = Appointment.get(params.appointment_id)
		def patient = Patient.findByAppointment(appointment);
		if(patient != null){
			patient.appointment = null
			patient.save()			
		}

		appointment.delete()
		render ''
	}
	
    def list2 = {
    
		//	println "component > " + params.component_id
		
		def component = Component.get(params.component_id)
		def user = User.get(params.user_id)
			
		//println "user > " + user
		
		def list
		
		if(component == 0 && user == 0){
		
			list = Appointment.findAllById(new Date(Long.parseLong(params.date)));
		}
		else if(component == null && user == null){
		
			list = Appointment.findAllByDate(new Date(Long.parseLong(params.date)), [sort: "id", order: "desc"]);
		}
		else if(component != null && user == null){
		
			list = Appointment.findAllByDateAndComponent(new Date(Long.parseLong(params.date)), component);
		}
		else if(component == null && user != null){
		
			list = Appointment.findAllByDateAndUser(new Date(Long.parseLong(params.date)), user);
		}
		else {
		
	    	def c = Appointment.createCriteria()
	    	
	    	list = c.list {
    	    	   eq("component", component)
    	    	   eq("date", new Date(Long.parseLong(params.date)))
    	    	   eq("user", user)
	    	       order('id', 'desc')
	    	}  			
		}
		
		if(params.building_id != null){
			def building = Building.get(params.building_id)
			def removes = new ArrayList()
			list.each { appointment ->
				if(appointment.visit == null){
					removes.add(appointment)
				}
				else{
				
					def ipd = Ipd.findByAn(appointment.visit.an)
					if(ipd.building.id != building.id){
						removes.add(appointment)
					}				
				}

			}
			
			list.removeAll(removes)
			
		}
	
        def fooList = new ArrayList()
        
    	list.each { a ->
            def fooMap = new HashMap()
            if(a.component != null){
              fooMap.put("component", a.component.name)
            }
            fooMap.put("id", a.id)
            fooMap.put("patient", a.patient.firstname +' '+ a.patient.lastname )
            if(a.user != null){
           	 	fooMap.put("doctor", a.user.firstname +' '+ a.user.lastname )
            }else{
            	fooMap.put("doctor", '' )
            }
            
            fooMap.put("date", a.date)
            fooMap.put("comment", a.comment)
            fooList.add(fooMap)
        }

           render fooList as JSON	 

    }
	
}
