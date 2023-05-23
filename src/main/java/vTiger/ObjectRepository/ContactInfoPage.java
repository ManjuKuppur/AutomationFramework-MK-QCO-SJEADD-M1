package vTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
	//Rule 1: create a separate class foe every web page
	
	//Rule 2: identify the elements using annotations
	@FindBy(xpath = "//span[@class= 'dvHeaderText']")
	private WebElement ContactHeaderText;
	
	//Rule 3: create constructor
	public ContactInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule 4: create getters
	public WebElement getContactHeaderText() {
		return ContactHeaderText;
	}
	
	
	
	//Business Library
	
	/**
	 * This method will get the contact header text
	 * @return
	 */
	public String getContactText()
	{
		return ContactHeaderText.getText();
	}
	
	

}
