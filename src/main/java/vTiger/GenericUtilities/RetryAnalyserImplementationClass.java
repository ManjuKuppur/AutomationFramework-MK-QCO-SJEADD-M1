package vTiger.GenericUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This class is an implementation class for IRETRYANALYSER interface testNG
 * @author MK
 *
 */
public class RetryAnalyserImplementationClass implements IRetryAnalyzer {
	
	int count = 0;
	int retryCount = 3; // analyse manually and get the retry count

	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		while(count<retryCount)
		{
			count++;
			return true;
		}
		return false;
	}

}
