import grails.converters.*
import java.util.Locale
import java.text.*

class ReportController {

	def dn = {
  		SimpleDateFormat dateformat = new SimpleDateFormat('dd/MM/yyyy')
  
		def dn = Dn.findByPatient(Patient.get(params.patient_id))

		StringBuffer buff = new StringBuffer()
		buff.append('<HTML>')
		buff.append('<HEAD><link type=\'text/css\' rel=\'stylesheet\' href=\'/kkuec/report.css\'></HEAD>')	
	//	buff.append('<BODY onload=\'window.print(); window.close();\'>')
		buff.append('<BODY>')
		buff.append('<H4>')
		buff.append('<P ALIGN=\'center\'>แบบสัมภาษณ์สุขภาพ</P>')
		buff.append('DN: ' + dn.dn +' <BR/>')
		buff.append('ชื่อคนไข้: ' + dn.patient.firstname +' '+ dn.patient.lastname )
		buff.append('</H4><BR/>')
		
		buff.append('<TABLE>')
		
		buff.append('<TR>')
		buff.append('<TD width=400>1.เคยทำฟันหรือถูกฉีดยามาก่อนหรือไม่</TD>')
		buff.append('<TD width=100 >('+(dn.topic1r1?'X':'_')+')ไม่เคย</TD>')
		buff.append('<TD width=100>('+(!dn.topic1r1?'X':'_')+')เคย</TD>')
		buff.append('<TD width=100>ระบุ</TD>')
		buff.append('<TD width=100>'+dn.topic1t1+'</TD>')
		buff.append('</TR>')
		
		buff.append('<TR><TD><BR/></TD></TR>')
				
		
		buff.append('<TR>')
		buff.append('<TD>2.หน้ามืด เป็นลม/หมดสติ</TD>')
		buff.append('<TD width=100 >('+(dn.topic2r1?'X':'_')+')ไม่เคย</TD>')
		buff.append('<TD width=100>('+(!dn.topic2r1?'X':'_')+')เคย</TD>')
		buff.append('<TD></TD>')
		buff.append('</TR>')
		
		buff.append('<TR><TD><BR/></TD></TR>')
				
		
		buff.append('<TR>')
		buff.append('<TD>3.โรคหัวใจ</TD>')
		buff.append('<TD width=100 >('+(dn.topic3r1?'X':'_')+')ไม่เคย</TD>')
		buff.append('<TD width=100>('+(!dn.topic3r1?'X':'_')+')เคย</TD>')
		buff.append('<TD>ระบุ</TD>')
		buff.append('</TR>')		
		
		buff.append('<TR><TD><BR/></TD></TR>')
				
		
		buff.append('<TR>')
		buff.append('<TD>4.ความดันโลหิตสูง</TD>')
		buff.append('<TD width=100 >('+(dn.topic4r1?'X':'_')+')ไม่เคย</TD>')
		buff.append('<TD width=100>('+(!dn.topic4r1?'X':'_')+')เคย</TD>')
		buff.append('<TD>ระบุ</TD>')
		buff.append('</TR>')		
		
		buff.append('<TR><TD><BR/></TD></TR>')
				
		
		buff.append('<TR>')
		buff.append('<TD>5.เบาหวาน</TD>')
		buff.append('<TD width=100 >('+(dn.topic5r1?'X':'_')+')ไม่้เป็น</TD>')
		buff.append('<TD width=100>('+(!dn.topic5r1?'X':'_')+')เป็น</TD>')
		buff.append('<TD>ระบุ</TD>')
		buff.append('</TR>')		
		
		buff.append('<TR><TD><BR/></TD></TR>')
				
		
		buff.append('<TR>')
		buff.append('<TD>6.ลมชัก</TD>')
		buff.append('<TD width=100 >('+(dn.topic6r1?'X':'_')+')ไม่เคย</TD>')
		buff.append('<TD width=100>('+(!dn.topic6r1?'X':'_')+')เคย</TD>')
		buff.append('<TD></TD>')
		buff.append('</TR>')		
		
		buff.append('<TR><TD><BR/></TD></TR>')
				
		
		buff.append('<TR>')
		buff.append('<TD>7.โรคเลือด(โลหิตจาง ธาลัสซีเมียฯลฯ)</TD>')
		buff.append('<TD width=100 >('+(dn.topic7r1?'X':'_')+')ไม่เป็น</TD>')
		buff.append('<TD width=100>('+(!dn.topic7r1?'X':'_')+')เป็น</TD>')
		buff.append('<TD>ระบุ</TD>')
		buff.append('</TR>')		
		
		buff.append('<TR><TD><BR/></TD></TR>')
				
		
		buff.append('<TR>')
		buff.append('<TD>8.แพ้ยา</TD>')
		buff.append('<TD width=100 >('+(dn.topic8r1?'X':'_')+')ไม่เคย</TD>')
		buff.append('<TD width=100>('+(!dn.topic8r1?'X':'_')+')เคย</TD>')
		buff.append('<TD>ระบุ</TD>')
		buff.append('</TR>')		
	
		buff.append('<TR><TD><BR/></TD></TR>')
				

		buff.append('<TR>')
		buff.append('<TD>9.ยาที่รับประทานประจำ</TD>')
		buff.append('<TD width=100 >('+(dn.topic9r1?'X':'_')+')ไม่เคย</TD>')
		buff.append('<TD width=100>('+(!dn.topic9r1?'X':'_')+')เคย</TD>')
		buff.append('<TD></TD>')
		buff.append('</TR>')	
				
		buff.append('<TR>')
		buff.append('<TD></TD>')
		buff.append('<TD width=100 >('+(dn.topic9c1?'_':'_')+')Aspirin</TD>')
		buff.append('<TD width=200>('+(dn.topic9c2?'_':'_')+')Steroid หรือยาลูกกลอน</TD>')
		buff.append('<TD>ระบุ</TD>')
		buff.append('</TR>')		
	
		buff.append('<TR><TD><BR/></TD></TR>')
				
		
		buff.append('<TR>')
		buff.append('<TD>10.โรคอื่นๆ</TD>')
		buff.append('<TD width=100 >('+(dn.topic10c1?'_':'_')+')เคยฉายแสง</TD>')
		buff.append('<TD width=200>('+(dn.topic10c2?'_':'_')+')โรคตับ</TD>')
		buff.append('<TD></TD>')
		buff.append('</TR>')		
		
		buff.append('<TR>')
		buff.append('<TD></TD>')
		buff.append('<TD width=100 >('+(dn.topic10c3?'_':'_')+')โรคไต</TD>')
		buff.append('<TD width=200>('+(dn.topic10c4?'_':'_')+')หอบหืด</TD>')
		buff.append('<TD></TD>')
		buff.append('</TR>')	
		
		buff.append('<TR>')
		buff.append('<TD></TD>')
		buff.append('<TD width=100 >('+(dn.topic10c5?'_':'_')+')วัณโรค</TD>')
		buff.append('<TD width=200>('+(dn.topic10c6?'_':'_')+')ไทรอยด็เป็นพิษ</TD>')
		buff.append('<TD>ระบุ</TD>')
		buff.append('</TR>')			
		
		buff.append('<TR><TD><BR/></TD></TR>')
				
		buff.append('<TR>')
		buff.append('<TD>11.ตั้งครรภ์</TD>')
		buff.append('<TD width=100 >('+(dn.topic11r1?'X':'_')+')ไม่มี</TD>')
		buff.append('<TD width=100>('+(!dn.topic11r1?'X':'_')+')มี</TD>')
		buff.append('<TD>ระบุ</TD>')
		buff.append('</TR>')	

		buff.append('<TR><TD><BR/><BR/></TD></TR>')

				
		buff.append('<TR>')
		buff.append('<TD></TD>')
		buff.append('<TD></TD>')
		buff.append('<TD></TD>')
		buff.append('<TD>(_____________________)</TD>')
		buff.append('</TR>')	
		
		buff.append('<TR>')
		buff.append('<TD></TD>')
		buff.append('<TD></TD>')
		buff.append('<TD></TD>')
		buff.append('<TD>'+dn.patient.firstname +' '+ dn.patient.lastname +'</TD>')
		buff.append('</TR>')	
			
		buff.append('</TABLE>')
		

		buff.append('<BODY>')
		buff.append('</HTML>')
		
		render buff.toString()	
	}

	
}
