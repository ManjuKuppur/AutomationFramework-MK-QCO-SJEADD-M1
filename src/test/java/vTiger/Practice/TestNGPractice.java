package vTiger.Practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGPractice {
	
	@Test (invocationCount = 2, priority = 2)
	public void createUSer()
	{
		Assert.fail();
		System.out.println("create user");
	}
	
	@Test (priority = 1, enabled = false)
	public void modifyYser()
	{
		System.out.println("modifyUser");
	}
	
	@Test (priority = 3, dependsOnMethods = "createUSer" )
	public void deleteUser()
	{
		System.out.println("deleteUser");
	}

}
