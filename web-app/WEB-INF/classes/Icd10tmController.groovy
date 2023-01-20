import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.hwpf.*;
import org.apache.poi.hwpf.extractor.*;
import java.io.*;

class Icd10tmController {

	def scaffold = Icd10tm


	def excel = {
	
		int row = 1
		try {
			println '000'
			FileInputStream myxls = new FileInputStream(new File("C:\\icd10tm.xls"))
			POIFSFileSystem fs = new POIFSFileSystem(myxls)
			HSSFWorkbook wb = new HSSFWorkbook(fs)
			
			HSSFSheet sheet = wb.getSheetAt(0); // first sheet
	
			
			while(row < 16967){
				try {
					Locale.setDefault(new Locale('en', 'US'))
				
					HSSFRow row2 = sheet.getRow(row)
					HSSFCell cell = row2.getCell((short) 0)
					def icd10 = cell.getStringCellValue().trim()
					cell = row2.getCell((short) 1)
					def icd9 = cell.getStringCellValue().trim()
					cell = row2.getCell((short) 2)
					def name = cell.getStringCellValue().trim()
					
					def icd10tm = new Icd10tm()
					icd10tm.icd10tm = icd10
					icd10tm.icd9 = icd9
					icd10tm.name = name
					icd10tm.save()
					
					println icd10tm.id
		
				   	
				} catch (Exception ex) {
					println ex
				}
			
				row++

			}

		} catch (Exception ex) {
			println ex
		}
	}	
}
