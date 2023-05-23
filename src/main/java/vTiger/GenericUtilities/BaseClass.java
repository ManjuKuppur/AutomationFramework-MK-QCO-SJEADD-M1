package vTiger.GenericUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import vTiger.ObjectRepository.HomePage;
import vTiger.ObjectRepository.LoginPage;

public class BaseClass {
	
	public PropertyFileUtility pUtil = new PropertyFileUtility();
	public JavaUtility jUtil = new JavaUtility();
	public ExcelFileUtility eUtil = new ExcelFileUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	
	public WebDriver driver = null;
	public static WebDriver sDriver;
	
	@BeforeSuite (alwaysRun = true)
	public void bsConfig()
	{
		System.out.println("------- DataBase connection Successful-------");
	}
	
	//@Parameters("BROWSER")
	//@BeforeTest
	@BeforeClass (groups = {"SmokeSuite", "RegressionSuite"})
	public void bcConfig(/*String BROWSER*/) throws IOException
	{
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(co);
			System.out.println(BROWSER + "Browser launched successfully");
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println(BROWSER + "Browser launched successfully");
		}
		else
		{
			System.out.println(" Invalid Browser Name ");
		}
		
		sDriver = driver;
		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver);
		driver.get(URL);
		
	}
	
	@BeforeMethod (groups = {"SmokeSuite", "RegressionSuite"})
	public void bmConfig() throws IOException
	{
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		System.out.println("Login is successful");
	}
	
	@AfterMethod (groups = {"SmokeSuite", "RegressionSuite"})
	public void amCongig()
	{
		HomePage hp = new HomePage(driver);
		hp.logoutOfApp(driver);
		System.out.println("Logout Successfully");
	}
	
	//@AfterTest
	@AfterClass (groups = {"SmokeSuite", "RegressionSuite"})
	public void acConfig()
	{
		driver.quit();
		System.out.println("Browser is successfully closed");
	}
	
	@AfterSuite (groups = {"SmokeSuite", "RegressionSuite"})
	public void asConfig()
	{
		System.out.println("------- DataBase closed Successful-------");
	}
	

}
