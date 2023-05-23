package vTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	
	//Rule 1: create a seperate class foe every web page
	
	//Rule 2: identify the elements using annotations
	@FindBy(xpath = "// img [@title = 'Create Organization...']")
	private WebElement CreateOrgLookUpImg;
	
	//Rule 3: create constructor initialise the web elements
	public OrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule 4: create getters (utilization)
	public WebElement getCreateOrgLookUpIcon() {
		return CreateOrgLookUpImg;
	}
	
	
	//Business library
	
	/**
	 * This method will open new organization page
	 */
	public void clickOnCreateOrgLookUpImg()
	{
		CreateOrgLookUpImg.click();
	}

	
	

}
