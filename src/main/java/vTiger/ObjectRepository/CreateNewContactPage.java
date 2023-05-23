package vTiger.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vTiger.GenericUtilities.WebDriverUtility;

public class CreateNewContactPage extends WebDriverUtility{
	
	//Rule 1: create a separate class foe every web page
	
	//Rule 2: identify the elements using annotations
	@FindBy(name = "lastname")
	private WebElement LastNameEdt;
	
	@FindBy(xpath = "//input[@name = 'account_name']/following-sibling::img[@title = 'Select']")
	private WebElement OrgLookUpImg;
	
	@FindBy(name = "search_text")
	private WebElement OrgSearchTxt;
	
	@FindBy(name = "search")
	private WebElement OrgSearchBtn;
	
	@FindBy(xpath = "//input [@title = 'Save [Alt+S]']")
	private WebElement SaveBtn;
	
	//Rule 3: create constructors
	public CreateNewContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule 4: create getters
	public WebElement getLastNameEdt() {
		return LastNameEdt;
	}

	public WebElement getOrgLookUpImg() {
		return OrgLookUpImg;
	}

	public WebElement getOrgSearchTxt() {
		return OrgSearchTxt;
	}

	public WebElement getOrgSearchBtn() {
		return OrgSearchBtn;
	}

	public WebElement getSaveBtn() {
		return SaveBtn;
	}
	
	
	//Business library
	
	/**
	 * This method will create contact with lastname
	 * @param LASTNAME
	 */
	public void createContact(String LASTNAME)
	{
		LastNameEdt.sendKeys(LASTNAME);
		SaveBtn.click();
	}
	
	
	/**
	 * This method will create new contact with lastname and organization
	 * @param driver
	 * @param LASTNAME
	 * @param ORGNAME
	 */
	public void createContact(WebDriver driver, String LASTNAME, String ORGNAME)
	{
		LastNameEdt.sendKeys(LASTNAME);
		OrgLookUpImg.click();
		switchToWindow(driver, "Accounts");
		OrgSearchTxt.sendKeys(ORGNAME);
		OrgSearchBtn.click();
		driver.findElement(By.xpath("//a[.='"+ ORGNAME +"']")).click();
		switchToWindow(driver, "Contacts");
		SaveBtn.click();
		
	}
	
	
	

}
