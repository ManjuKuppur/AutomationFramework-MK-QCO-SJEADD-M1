package vTiger.Practice;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import vTiger.GenericUtilities.ExcelFileUtility;
import vTiger.GenericUtilities.JavaUtility;
import vTiger.GenericUtilities.PropertyFileUtility;
import vTiger.GenericUtilities.WebDriverUtility;
import vTiger.ObjectRepository.LoginPage;

public class DemoScriptUsingUtilities {
public static void main(String[] args) throws IOException {
		
	   // Create Object for Utilities
	   PropertyFileUtility pUtil = new PropertyFileUtility();
	   JavaUtility jUtil = new JavaUtility();
	   ExcelFileUtility eUtil = new ExcelFileUtility();
	   WebDriverUtility wUtil = new WebDriverUtility();
	
	
		//Require all data
		
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		
		String ORGNAME = eUtil.readDatafromExcelFile("Organization", 1, 2)+jUtil.getRandomNumber();
		
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
//		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//		driver.findElement(By.id("submitButton")).click();
		
		LoginPage lp = new LoginPage(driver);
//		lp.getUserNameEdt().sendKeys(USERNAME);
//		lp.getPasswordEdt().sendKeys(PASSWORD);
//		lp.getLoginBtn().click();
		
		lp.loginToApp(USERNAME, PASSWORD);
		
		
		//Step 3: Navigate to Organizations Link
		driver.findElement(By.linkText("Organizations")).click();
		
		
		//Step 4: Click on Create Organization Look UP Icon
		driver.findElement(By.xpath("// img [@title = 'Create Organization...']")).click();
		
		
		//Step 5: Create Organization with Mandatory fields
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		
		
		//Step 6: Save
		driver.findElement(By.xpath("//input [@title = 'Save [Alt+S]']")).click();
		
		
		//Step 7: Validate
		String OrgHeader = driver.findElement(By.xpath("//span[@class = 'dvHeaderText']")).getText();
		
		if(OrgHeader.contains(ORGNAME))
		{
			System.out.println("==== PASS ====");
		}
		else
		{
			System.out.println("==== FAIL ====");
		}
		
		//Step 8: Logout
		WebElement ele = driver.findElement(By.xpath("//img [@ src = 'themes/softed/images/user.PNG']"));
		wUtil.mouseHoverAction(driver, ele);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Signout Successful");



	}


}
