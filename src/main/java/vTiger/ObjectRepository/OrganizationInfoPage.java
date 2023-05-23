package vTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	
	//Rule 1: create a separate class foe every web page
	
	//Rule 2: identify the elements using annotations
	@FindBy(xpath = "//span[@class = 'dvHeaderText']")
	private WebElement OrgHeaderText;
	
	//Rule 3: create constructor to initialize
	public OrganizationInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule 4: create getters
	public WebElement getOrgHeaderText() {
		return OrgHeaderText;
	}
	
	
	//Business Library
	
	/**
	 * This method will return Org Header Text
	 * @return
	 */
	public String getOrgText()
	{
		return OrgHeaderText.getText();
		
	}
	
	
	

}
