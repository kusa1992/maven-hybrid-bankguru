package reportConfig;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import commons.BaseTest;
import commons.GlobalConstants;

public class ReportNGListener implements ITestListener {
	String projectLocation = GlobalConstants.PROJECT_PATH + "/screenshortReportNG/";

	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			System.setProperty("org.uncommons.reportng.escape-output", "false");

			Object testClass = result.getInstance();
			WebDriver driver = ((BaseTest) testClass).getWebDriver();

			String screenshotPath = captureScreenshot(driver, result.getName());
			Reporter.getCurrentTestResult();

			Reporter.log("<br><a target=\"_blank\" href=\"data:image/png;base64," + screenshotPath + "\">" + "<img src=\"data:image/png;base64," + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
			Reporter.setCurrentTestResult(null);
		} catch (NoSuchSessionException e) {
			e.printStackTrace();
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
	}

	public String captureScreenshot(WebDriver driver, String screenshotName) {
		/*
		 * try { Calendar calendar = Calendar.getInstance(); SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss"); File source = ((TakesScreenshot)
		 * driver).getScreenshotAs(OutputType.FILE); String screenPath = projectLocation + screenshotName + "_" + formater.format(calendar.getTime()) + ".png";
		 * FileUtils.copyFile(source, new File(screenPath)); return screenPath; } catch (IOException e) { System.out.println("Exception while taking screenshot: " +
		 * e.getMessage()); return e.getMessage(); }
		 */
		String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		return screenshotBase64;
	}
}
