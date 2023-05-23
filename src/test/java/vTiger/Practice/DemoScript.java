package vTiger.Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoScript {

	public static void main(String[] args) throws IOException {
		
		//Generate Random Numbers
		Random ran = new Random();
		int random = ran.nextInt(1000);
		
		//Require all data
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
		FileInputStream fis1 = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String ORGNAME = wb.getSheet("Organization").getRow(1).getCell(2).getStringCellValue()+random;
		
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
		
		
//		WebDriverManager.firefoxdriver().setup();
//		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		
		//Step 2: Login to Application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		
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
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Signout Successful");



	}

}
