package vTiger.OrganizationsTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import vTiger.GenericUtilities.BaseClass;

import vTiger.ObjectRepository.CreateNewOrganizationPage;
import vTiger.ObjectRepository.HomePage;
import vTiger.ObjectRepository.OrganizationInfoPage;
import vTiger.ObjectRepository.OrganizationPage;

@Listeners (vTiger.GenericUtilities.ListenersImplimentationClass.class)
public class CreateNewOrganizationTest extends BaseClass {
	
	@Test (groups = "SmokeSuite")
	public void createNewOrgTest() throws IOException
	{
					
			String ORGNAME = eUtil.readDatafromExcelFile("Organization", 1, 2)+jUtil.getRandomNumber();
			
			
			//Step 3: Navigate to Organizations Link
			HomePage hp = new  HomePage(driver);
			hp.clickOnOrgLink();
			
			
			//Step 4: Click on Create Organization Look UP Icon
			OrganizationPage op = new OrganizationPage(driver);
			op.clickOnCreateOrgLookUpImg();
			//Assert.fail();
			
			//Step 5: Create Organization with Mandatory fields
			CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
			cnop.createNewOrg(ORGNAME);
			
			//Step 6: Validate
			OrganizationInfoPage oip =  new OrganizationInfoPage(driver);
			String OrgHeader = oip.getOrgText();
			Assert.assertTrue(OrgHeader.contains(ORGNAME));
			
				
	}
	
	@Test (groups = "RegressionSuite")
	public void demo()
	{
		System.out.println("Demo RUN");
	}

}
