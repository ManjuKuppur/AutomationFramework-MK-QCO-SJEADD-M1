package vTiger.Practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderApperalsPractice {
	
	@Test (dataProvider = "getData")
	public void apperalsData(String BrandName, int Price, int Size)
	{
		System.out.println(BrandName + "      " + Price + "       " + Size);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] data = new Object[5][3];
		
		data[0][0] = "Jockey";
		data[0][1] = 800;
		data[0][2] = 38;
		
		data[1][0] = "Allen Soly";
		data[1][1] = 1500;
		data[1][2] = 40;
		
		data[2][0] = "Ramraj";
		data[2][1] = 2000;
		data[2][2] = 36;
		
		data[3][0] = "Udayam";
		data[3][1] = 1200;
		data[3][2] = 30;
		
		data[4][0] = "Peater England";
		data[4][1] = 2000;
		data[4][2] = 38;
		
		return data;
		
	}

}
