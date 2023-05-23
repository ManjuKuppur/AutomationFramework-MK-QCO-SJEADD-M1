package vTiger.Practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataInToExcelFile {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// Step1: open the document in java readable file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//Step2: create the workbook
		Workbook wb = WorkbookFactory.create(fis);
		
		//Step3: get the control of sheet
		Sheet sh = wb.getSheet("Contact");
		
		//Step4: get the control of Row
		Row ro = sh.createRow(5);
		
		//Step5: get the control of cell
		Cell ce = ro.createCell(5);
		
		//Step6: set the value in to the cell
		ce.setCellValue("Very Good.!!!");
		
		//Step7: open the file in writable format
		FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//Step8: write the data into workbook
		wb.write(fos);
		
		//Sheet9: close the workbook
		wb.close();
		System.out.println("Data is entered and workbook is closed");

	}

}
