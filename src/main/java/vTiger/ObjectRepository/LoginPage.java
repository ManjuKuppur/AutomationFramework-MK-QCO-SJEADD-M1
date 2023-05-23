package vTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	//Rule 1: create a seperate class foe every web page
	
	//Rule 2: identify the elements using annotations
	@FindBy(name = "user_name")
	private WebElement userNameEdt;
	
	@FindAll({@FindBy(name = "user_password"), @FindBy(xpath = "//input[@name = 'user_password']")})
	private WebElement passwordEdt;
	
	@FindBy(id = "submitButton")
	private WebElement LoginBtn;
	
	//Step 3: Create constructor to initialise these element
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule 4: provide getters to access these variables

	public WebElement getUserNameEdt() {
		return userNameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return LoginBtn;
	}
	
	
	//Business Library - optimize the test script
	
	/**
	 * This method will login to application
	 * @param USERNAME
	 * @param PASSWORD
	 */
	public void loginToApp(String USERNAME, String PASSWORD)
	{
		userNameEdt.sendKeys(USERNAME);
		passwordEdt.sendKeys(PASSWORD);
		LoginBtn.click();
		
	}
	
	

}
