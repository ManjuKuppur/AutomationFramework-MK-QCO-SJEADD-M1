package vTiger.OrganizationsTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vTiger.GenericUtilities.ExcelFileUtility;
import vTiger.GenericUtilities.JavaUtility;
import vTiger.GenericUtilities.PropertyFileUtility;
import vTiger.GenericUtilities.WebDriverUtility;
import vTiger.ObjectRepository.CreateNewOrganizationPage;
import vTiger.ObjectRepository.HomePage;
import vTiger.ObjectRepository.LoginPage;
import vTiger.ObjectRepository.OrganizationInfoPage;
import vTiger.ObjectRepository.OrganizationPage;

public class CreateMultipleOrgWithIndustryTest {
	
	JavaUtility jUtil = new JavaUtility();
	ExcelFileUtility eUtil = new ExcelFileUtility();
	
	@Test (dataProvider = "multipleOrg")
	public void createMultipleOrgTest(String ORG, String INDUSTRY) throws IOException
	{
		// Create Object for Utilities
		   PropertyFileUtility pUtil = new PropertyFileUtility();
		   WebDriverUtility wUtil = new WebDriverUtility();
		
		
			//Require all data
			
			String BROWSER = pUtil.readDataFromPropertyFile("browser");
			String URL = pUtil.readDataFromPropertyFile("url");
			String USERNAME = pUtil.readDataFromPropertyFile("username");
			String PASSWORD = pUtil.readDataFromPropertyFile("password");
			
			String ORGNAME = ORG + jUtil.getRandomNumber();
			WebDriver driver = null;
			
			//Step1: launch the browser with RUNTIME POLYMORPHISM
			
			if(BROWSER.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--remote-allow-origins=*");
				driver = new ChromeDriver(co);
				
			}
			else if(BROWSER.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
			else
			{
				System.out.println("browser is not valid");
			}
			
			
			
			wUtil.maximizeWindow(driver);
			wUtil.waitForPageLoad(driver);
			driver.get(URL);
			
			//Step 2: Login to Application
			LoginPage lp = new LoginPage(driver);			
			lp.loginToApp(USERNAME, PASSWORD);
			
			
			//Step 3: Navigate to Organizations Link
			HomePage hp = new  HomePage(driver);
			hp.clickOnOrgLink();
			
			
			//Step 4: Click on Create Organization Look UP Icon
			OrganizationPage op = new OrganizationPage(driver);
			op.clickOnCreateOrgLookUpImg();
			
			//Step 5: Create Organization with Mandatory fields
			CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
			cnop.createNewOrg(ORGNAME, INDUSTRY);;
			
			//Step 6: Validate
			OrganizationInfoPage oip =  new OrganizationInfoPage(driver);
			String OrgHeader = oip.getOrgText();
			
			if(OrgHeader.contains(ORGNAME))
			{
				System.out.println(OrgHeader + "==== PASS ====");
			}
			else
			{
				System.out.println("==== FAIL ====");
			}
			
			//Step 8: Logout
			hp.getSignOutLink();
			System.out.println("Signout Successful");

		
	}
	
	@DataProvider (name = "multipleOrg")
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		return eUtil.readMultipleDataFromExcel("DataProviderOrg");
		
	}

}
