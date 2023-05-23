package vTiger.Practice;

import org.testng.annotations.Test;

public class ReadDataFromCmdLine {
	
	@Test
	public void readDta() 
	{
		String BVALUE = System.getProperty("browser");
		System.out.println(BVALUE);
		
		String UVALUE = System.getProperty("url");
		System.out.println(UVALUE);
		
		String PVALUE = System.getProperty("passward");
		System.out.println(PVALUE);
	}

}
