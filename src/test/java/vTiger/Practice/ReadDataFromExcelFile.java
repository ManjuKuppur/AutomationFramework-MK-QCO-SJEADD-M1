package vTiger.Practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelFile {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		//Step1: Open the document in java readable format using FileInputStream
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//Step2: Create a WorkBook using workbook factory
		Workbook wb = WorkbookFactory.create(fis);
		
		//Step3: get the control of Sheet
		Sheet sh = wb.getSheet("Organization");
		
		//Step4: get the control of Row
		Row ro = sh.getRow(1);
		
		//Step5: get the control of cell
		Cell ce = ro.getCell(1);
		
		
		//Step6: read the data inside the cell
		String value = ce.getStringCellValue();
		System.out.println(value);
		
		
		//Step7: Close the Workbook
		wb.close();
		

	}

}
