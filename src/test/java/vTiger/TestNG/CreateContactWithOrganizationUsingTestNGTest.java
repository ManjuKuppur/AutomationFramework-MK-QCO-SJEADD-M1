package vTiger.TestNG;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import vTiger.GenericUtilities.BaseClass;
import vTiger.ObjectRepository.ContactInfoPage;
import vTiger.ObjectRepository.ContactPage;
import vTiger.ObjectRepository.CreateNewContactPage;
import vTiger.ObjectRepository.CreateNewOrganizationPage;
import vTiger.ObjectRepository.HomePage;
import vTiger.ObjectRepository.OrganizationInfoPage;
import vTiger.ObjectRepository.OrganizationPage;

@Listeners(vTiger.GenericUtilities.ListenersImplimentationClass.class)

public class CreateContactWithOrganizationUsingTestNGTest extends BaseClass {
	
	@Test
	public void createContactWithOrgTest() throws IOException 
	{
		
				String ORGNAME = eUtil.readDatafromExcelFile("Contact", 4, 3) + jUtil.getRandomNumber();
				String LASTNAME = eUtil.readDatafromExcelFile("Contact", 4, 2);
				
				
				//Step 3: click on organization button
				HomePage hp = new HomePage(driver);
				hp.clickOnOrgLink();
				
				
				//Step 4: click on create organization page
				OrganizationPage op = new OrganizationPage(driver);
				op.clickOnCreateOrgLookUpImg();
				
				
				//Step 5: enter the required fields
				CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
				cnop.createNewOrg(ORGNAME);
				

				//Step 7: Validate
				OrganizationInfoPage oip = new OrganizationInfoPage(driver);
				String OrgHedder =  oip.getOrgText();
				Assert.assertTrue(OrgHedder.contains(ORGNAME));
				
				//Step 8: click on contact
				hp.clickOnContactLink();
				
				//Step 9: create new contact
				ContactPage cp = new ContactPage(driver);
				cp.ClickOnCreateContactLookUpImg();
				
				//Step 10: enter the required fields and select ORG name
				CreateNewContactPage cncp = new CreateNewContactPage(driver);
				cncp.createContact(driver, LASTNAME, ORGNAME);
				//wUtil.takeScreenShot(driver, "Contact");
				
				//Step 11: validate
				ContactInfoPage cip = new ContactInfoPage(driver);
				String contHeader = cip.getContactText();
				Assert.assertTrue(contHeader.contains(LASTNAME));
				
				
			
	}

}
