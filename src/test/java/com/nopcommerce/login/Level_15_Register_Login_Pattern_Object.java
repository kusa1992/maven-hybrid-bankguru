package com.nopcommerce.login;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.MyAccountPageObject;
import pageObjects.user.nopCommerce.OrderPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPageObject;
import pageObjects.user.nopCommerce.SearchPageObject;
import utilities.DataUtil;

public class Level_15_Register_Login_Pattern_Object extends BaseTest {
	WebDriver driver;
	String emailAddress, password, pageName, company, date, month, year;
	DataUtil fakeData;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-Condition: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);

		emailAddress = fakeData.getEmailAddress();
		password = fakeData.getPassword();
		company = "Shift";
		date = "27";
		month = "October";
		year = "1998";
	}

	@Test
	public void User_01_Register_To_System() {
		log.info("User_01_Register - Step 01: Verify Home Page is displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("User_01_Register - Step 02: Click to Register link");
		homePage.openHeaderPageByName(driver, "Register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		
		log.info("User_01_Register - Step 03: Click to Male radio button");
		registerPage.clickToRadioButtonByText(driver, "Male");
		
		log.info("User_01_Register - Step 04: Enter to Firstname textbox");
		registerPage.enterToTextboxByID(driver, "FirstName", "Emmy");
		// tham so thu nhat: detect duoc day la textbox nao dua vao ID truyen vao
		// tham so thu hai: gia tri de input vao textbox
		
		log.info("User_01_Register - Step 05: Enter to Lastname textbox");
		registerPage.enterToTextboxByID(driver, "LastName", "Emmy");
		
		log.info("User_01_Register - Step 06: Select item from Date dropdown");
		registerPage.selectDropdownByName(driver, "DateOfBirthDay", date);
		log.info("User_01_Register - Step 07: Select item from Month dropdown");
		registerPage.selectDropdownByName(driver, "DateOfBirthMonth", month);
		log.info("User_01_Register - Step 08: Select item from Year dropdown");
		registerPage.selectDropdownByName(driver, "DateOfBirthYear", year);
		
		log.info("User_01_Register - Step 09: Enter to Email textbox with value " + emailAddress);
		registerPage.enterToTextboxByID(driver, "Email", emailAddress);
		
		log.info("User_01_Register - Step 10: Enter to Company textbox with value " + company);
		registerPage.enterToTextboxByID(driver, "Company", company);
		
		log.info("User_01_Register - Step 11: Enter to Password textbox with value " + password);
		registerPage.enterToTextboxByID(driver, "Password", password);
		
		log.info("User_01_Register - Step 12: Enter to Confirm Password textbox with value " + password);
		registerPage.enterToTextboxByID(driver, "ConfirmPassword", password);
		
		log.info("User_01_Register - Step 13: Click to Register button");
		registerPage.clickToButtonByText(driver, "Register");
		
		log.info("User_01_Register - Step 14: Verify success message is displayed");
		verifyTrue(registerPage.isSuccessMessageDisplayed());
		
		log.info("User_01_Register - Step 15: Click to Logout link");
		registerPage.openHeaderPageByName(driver, "Log out");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("User_01_Register - Step 16: Verify Home Page is displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());
	}

	@Test
	public void User_02_Login_To_System() {
		log.info("User_02_Login - Step 01: Click to Login link");
		homePage.openHeaderPageByName(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		log.info("User_02_Login - Step 02: Enter to Email textbox with value " + emailAddress);
		loginPage.enterToTextboxByID(driver, "Email", emailAddress);
		
		log.info("User_02_Login - Step 03: Enter to Password textbox with value " + password);
		loginPage.enterToTextboxByID(driver, "Password", password);
		
		log.info("User_02_Login - Step 04: Click to Login button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getHomePage(driver);
		
		log.info("User_02_Login - Step 05: Verify Home Page is displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());
	}
	
	@Parameters({"browser"})
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition: Close browser '" + browserName + "'");
		cleanBrowserAndDriver();
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	SearchPageObject searchPage;
	MyAccountPageObject myAccountPage;
	OrderPageObject orderPage;
}
