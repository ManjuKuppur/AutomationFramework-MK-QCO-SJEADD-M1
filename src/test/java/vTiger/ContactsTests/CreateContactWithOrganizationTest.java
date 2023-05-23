package vTiger.ContactsTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import vTiger.GenericUtilities.ExcelFileUtility;
import vTiger.GenericUtilities.JavaUtility;
import vTiger.GenericUtilities.PropertyFileUtility;
import vTiger.GenericUtilities.WebDriverUtility;
import vTiger.ObjectRepository.ContactInfoPage;
import vTiger.ObjectRepository.ContactPage;
import vTiger.ObjectRepository.CreateNewContactPage;
import vTiger.ObjectRepository.CreateNewOrganizationPage;
import vTiger.ObjectRepository.HomePage;
import vTiger.ObjectRepository.LoginPage;
import vTiger.ObjectRepository.OrganizationInfoPage;
import vTiger.ObjectRepository.OrganizationPage;

public class CreateContactWithOrganizationTest {

	public static void main(String[] args) throws IOException {
		
		//Create utility objects
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		
		//Require all data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		String URL = pUtil.readDataFromPropertyFile("url");
		
		
		String ORGNAME = eUtil.readDatafromExcelFile("Contact", 4, 3) + jUtil.getRandomNumber();
		String LASTNAME = eUtil.readDatafromExcelFile("Contact", 4, 2);
		
		
		WebDriver driver = null;
		
		
		
		//Step 1: Launch the Browser
		
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
		
		
		//Step 2: Login to application
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		
		//Step 3: click on organization button
		HomePage hp = new HomePage(driver);
		hp.clickOnOrgLink();
		
		
		//Step 4: click on create organization page
		OrganizationPage op = new OrganizationPage(driver);
		op.clickOnCreateOrgLookUpImg();
		
		
		//Step 5: enter the required fields
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrg(ORGNAME);
		

		//Step 7: Validate
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String OrgHedder =  oip.getOrgText();
		
		if(OrgHedder.contains(ORGNAME))
		{
			System.out.println("Organization Created Successful");
		}
		else
		{
			System.out.println("Organization NOT created");
		}
		
		//Step 8: click on contact
		hp.clickOnContactLink();
		
		//Step 9: create new contact
		ContactPage cp = new ContactPage(driver);
		cp.ClickOnCreateContactLookUpImg();
		
		//Step 10: enter the required fields and select ORG name
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createContact(driver, LASTNAME, ORGNAME);
		//wUtil.takeScreenShot(driver, "Contact");
		
		//Step 11: validate
		ContactInfoPage cip = new ContactInfoPage(driver);
		String contHeader = cip.getContactText();
		
		if(contHeader.contains(LASTNAME))
		{
			System.out.println("====PASS====");
		}
		else
		{
			System.out.println("====FAIL====");
		}
		
		//Step 12: Logout
		hp.logoutOfApp(driver);
		System.out.println("SignOut Successfull");
		
		
		
		
	}

}
