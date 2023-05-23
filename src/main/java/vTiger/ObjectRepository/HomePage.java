package vTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vTiger.GenericUtilities.WebDriverUtility;

public class HomePage  extends WebDriverUtility {
	
	//Rule 1: create a seperate class foe every web page
	
	//Rule 2: identify the elements using annotations
	@FindBy(linkText = "Organizations")
	private WebElement OrganizationsLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement ContactsLink;
	
	@FindBy(xpath = "//img [@ src = 'themes/softed/images/user.PNG']")
	private WebElement AdministratorImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement SignOutLink;
	
	//Rule 3: create constructor to initialise the elements
	public HomePage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		
	}
	
	//Rule 4: provide getters to access these variables

	public WebElement getOrganizationsLink() {
		return OrganizationsLink;
	}

	public WebElement getContactsLink() {
		return ContactsLink;
	}

	public WebElement getAdministratorImg() {
		return AdministratorImg;
	}

	public WebElement getSignOutLink() {
		return SignOutLink;
	}
	
	
	//Business Library - Optimise the test script
	
	/**
	 * This method will click on organization link
	 */
	public void clickOnOrgLink()
	{
		OrganizationsLink.click();
	}
	
	/**
	 * This method will click on contact link
	 */
	public void clickOnContactLink()
	{
		ContactsLink.click();
	}
	
	
	public void logoutOfApp(WebDriver driver)
	{
		mouseHoverAction(driver, AdministratorImg);
		SignOutLink.click();
	}

	

}
