package vTiger.Practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionPractice {
	
	@Test
	public void assertionPractice()
	{
		System.out.println("Step 1");
		System.out.println("Step 2");
		Assert.assertEquals(true, false);
		System.out.println("Step 3");
		System.out.println("Step 4");
	}
	
	@Test
	public void practice()
	{
		SoftAssert sa = new SoftAssert();
		
		System.out.println("Second Step 1");
		System.out.println("Second Step 2");
		sa.assertEquals(false, true);
		System.out.println("Second Step 3");
		System.out.println("Second Step 4");
		sa.assertEquals(1, 0);
		System.out.println("Second Step 5");
		sa.assertAll(); //log all the assertion failure
	}

}
