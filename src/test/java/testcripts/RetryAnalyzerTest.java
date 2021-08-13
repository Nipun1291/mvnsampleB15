package testcripts;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerTest implements IRetryAnalyzer{

	int retryAttemptCounter = 0;
	int maxRetry = 3;
	public boolean retry(ITestResult result) {
		if(!result.isSuccess())
		{
			if(retryAttemptCounter < maxRetry)
			{
				retryAttemptCounter++;
				return true;
			}
		}
		return false;
	}
	
	

}
