package commons;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import reportConfig.VerificationFailures;

public class BaseTest {
	private WebDriver driver;
	private String projectLocation = System.getProperty("user.dir");
	// Init log
	protected final Log log;

	// Constructor
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	private enum BROWSER {
		CHROME, FIREFOX, IE, SAFARI, EDGE_LEGACY, EDGE_CHROMIUM, H_CHROME, H_FIREFOX, COC_COC, OPERA;
	}
	
	private enum ENVIRONMENT {
		DEV, TESTING, STAGING, PRODUCTION
	}

	protected WebDriver getBrowserDriver(String browserName) {
		BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
		if (browser == BROWSER.FIREFOX) {
			System.setProperty("webdriver.gecko.driver", projectLocation + getSlash("browserDrivers") + "geckodriver");
			driver = new FirefoxDriver();
			System.out.println("Driver init at Base Test = " + driver.toString());
		} else if (browser == BROWSER.CHROME) {
			System.setProperty("webdriver.chrome.driver", projectLocation + getSlash("browserDrivers") + "chromedriver");
			driver = new ChromeDriver();
		} else {
			throw new RuntimeException("Please enter the correct browser name!");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(GlobalConstants.DEV_APP_URL);
		return driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
		if (browser == BROWSER.FIREFOX) {
			//WebDriverManager.firefoxdriver().setup();
			System.setProperty("webdriver.gecko.driver", projectLocation + getSlash("browserDrivers") + "geckodriver");
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.PROJECT_PATH + getSlash("browserLogs") + "Firefox.log");

			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("intl.accept_languages", "vi-vn, vi, en-us, en");
			driver = new FirefoxDriver(options);

		} else if (browser == BROWSER.CHROME) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singleton("enable-automation"));

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);

			driver = new ChromeDriver(options);

		} else if (browser == BROWSER.EDGE_CHROMIUM) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		} else if (browser == BROWSER.SAFARI) {
			driver = new SafariDriver();

		} else if (browser == BROWSER.H_CHROME) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);

		} else if (browser == BROWSER.H_FIREFOX) {
			System.setProperty("webdriver.gecko.driver", GlobalConstants.PROJECT_PATH + getSlash("browserDrivers") + "geckodriver");
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);

		} else if (browser == BROWSER.IE) {
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();

		} else if (browser == BROWSER.COC_COC) {
			WebDriverManager.chromedriver().driverVersion("94.0.4606.113").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
			
		} else if (browser == BROWSER.OPERA) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
			
		} else {
			throw new RuntimeException("Please enter the correct browser name!");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appUrl);

		System.out.println("Driver init at Base Test = " + driver.toString());
		return driver;
	}
	
	private String getEnvironmentValue(String environmentName) {
		String envUrl= null;
		ENVIRONMENT environment = ENVIRONMENT.valueOf(environmentName.toUpperCase());
		if (environment == ENVIRONMENT.DEV) {
			envUrl = "https://demo.guru99.com/v1";
		} else if (environment == ENVIRONMENT.TESTING) {
			envUrl = "https://demo.guru99.com/v2";
		} else if (environment == ENVIRONMENT.STAGING) {
			envUrl = "https://demo.guru99.com/v3";
		} else if (environment == ENVIRONMENT.PRODUCTION) {
			envUrl = "https://demo.guru99.com/v4";
		} return envUrl;
	}

	public WebDriver getWebDriver() {
		return this.driver;
	}

	private String getSlash(String folderName) {
		String separator = File.separator;
		return separator + folderName + separator;
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999999);
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	@BeforeSuite
	public void deleteAllFilesInAllureJson() {
		try {
			String workingDir = System.getProperty("user.dir");
			String pathFolderDownload = workingDir + "/allure-json";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile() && !listOfFiles[i].getName().contains("environment.properties")) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	protected void cleanBrowserAndDriver() {
		String cmd = "";
		try {
			// Get OS name and convert to lower case
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + osName);

			// Executable driver
			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			System.out.println("Driver instance = " + driver.toString());

			// Browser
			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				log.info(e.getMessage());
			} catch (InterruptedException e) {
				log.info(e.getMessage());
			}
		}
	}

	protected void showBrowserConsoleLogs(WebDriver driver) {
		if (driver.toString().contains("chrome")) {
			LogEntries logsEntries = driver.manage().logs().get("browser");
			List<LogEntry> logList = logsEntries.getAll();
			for (LogEntry logging : logList) {
				log.info("-------------" + logging.getLevel().toString() + "------------- \n" + logging.getMessage());
			}
		}

	}
}