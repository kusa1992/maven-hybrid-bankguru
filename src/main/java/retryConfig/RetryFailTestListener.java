package retryConfig;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailTestListener implements IRetryAnalyzer{
	private int retryCount = 0;
	private int maxRetryCount = 2;
	
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) {
			System.out.println("Retry test name: " + result.getName() + " with: " + (retryCount + 1) + "time(s).");
			retryCount++;
			return true;
		}
		return false;
	}
}
