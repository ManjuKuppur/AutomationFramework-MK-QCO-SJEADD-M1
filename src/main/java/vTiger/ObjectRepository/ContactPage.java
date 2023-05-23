package vTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	
	//Rule 1: create a separate class foe every web page
	
	//Rule 2: identify the elements using annotations
	@FindBy(xpath = "//img[@title = 'Create Contact...']")
	private WebElement CreateContactLookUpImg;
	
	//Rule 3: create constructor
	public ContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule 4: create getters
	public WebElement getCreateContactLookUpImg() {
		return CreateContactLookUpImg;
	}
	
	
	//Business library
	
	/**
	 * This method will click new contact button
	 */
	public void ClickOnCreateContactLookUpImg()
	{
		CreateContactLookUpImg.click();
	}
	
	

}
