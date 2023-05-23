package vTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vTiger.GenericUtilities.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility {
	
	//Rule 1: create a seperate class foe every web page
	
	//Rule 2: identify the elements using annotations
	@FindBy(name = "accountname")
	private WebElement OrgNameEdt;
	
	@FindBy(name = "industry")
	private WebElement IndustryDropdown;
	
	@FindBy(xpath = "//input [@title = 'Save [Alt+S]']")
	private WebElement SaveBtn;
	
	//Rule 3: create constructor to initialize elements
	public CreateNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule 4: create getters
	public WebElement getOrgNameEdt() {
		return OrgNameEdt;
	}

	public WebElement getIndustryDropdown() {
		return IndustryDropdown;
	}
	
	public WebElement getSaveBtn() {
		return SaveBtn;
	}
	
	
	//Business library
	
	/**
	 * This method will create new Organization with mandatory field
	 * @param ORGNAME
	 */
	public void createNewOrg(String ORGNAME)
	{
		OrgNameEdt.sendKeys(ORGNAME);
		SaveBtn.click();
		
	}
	
	/**
	 * This method will create Org with Industry and save
	 * @param ORGNAME
	 * @param INDUSTRY
	 */
	public void createNewOrg(String ORGNAME, String INDUSTRY)
	{
		OrgNameEdt.sendKeys(ORGNAME);
		HandleDropdown(IndustryDropdown, INDUSTRY);
		SaveBtn.click();
	}

}
