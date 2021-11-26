package com.nopcommerce.common;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.MyAccountPageObject;
import pageObjects.user.nopCommerce.OrderPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPageObject;
import pageObjects.user.nopCommerce.SearchPageObject;
import utilities.DataUtil;

public class Common_01_Login extends BaseTest {
	WebDriver driver;
	String emailAddress, password, pageName;
	DataUtil fakeData;
	public static Set<Cookie> loginPageCookie;

	@Parameters({ "browser", "url" })
	@BeforeTest
	public void beforeTest(String browserName, String appUrl) {
		log.info("Pre-Condition: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);

		emailAddress = fakeData.getEmailAddress();
		password = fakeData.getPassword();

		log.info("Common_01 - Step 01: Verify Home Page is displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Common_01 - Step 02: Click to Register link");
		registerPage = homePage.clickToRegisterLink();

		log.info("Common_01 - Step 03: Click to Male radio button");
		registerPage.clickToGenderMaleRadioButton();

		log.info("Common_01 - Step 04: Enter to Firstname textbox");
		registerPage.enterToFirstnameTextbox("Thao");

		log.info("Common_01 - Step 05: Enter to Lastname textbox");
		registerPage.enterToLastnameTextbox("Nguyen");

		log.info("Common_01 - Step 06: Enter to Email textbox with value " + emailAddress);
		registerPage.enterToEmailTextbox(emailAddress);

		log.info("Common_01 - Step 07: Enter to Password textbox with value " + password);
		registerPage.enterToPasswordTextbox(password);

		log.info("Common_01 - Step 08: Enter to Confirm Password textbox with value " + password);
		registerPage.enterToConfirmPasswordTextbox(password);

		log.info("Common_01 - Step 09: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Common_01 - Step 10: Verify success message is displayed");
		verifyTrue(registerPage.isSuccessMessageDisplayed());

		log.info("Common_01 - Step 11: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();
		System.out.println("Home Page is " + homePage.hashCode());

		log.info("Common_01 - Step 12: Verify Home Page is displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Common_01 - Step 13: Click to Login link");
		loginPage = homePage.clickToLoginLink();

		log.info("Common_01 - Step 14: Enter to Email textbox with value " + emailAddress);
		loginPage.enterToEmailTextbox(emailAddress);

		log.info("Common_01 - Step 15: Enter to Password textbox with value " + password);
		loginPage.enterToPasswordTextbox(password);

		log.info("Common_01 - Step 16: Click to Login button");
		homePage = loginPage.clickToLoginButton();

		log.info("Common_01 - Step 17: Verify Home Page is displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Common_01 - Step 18: Get all login page cookies");
		loginPageCookie = homePage.getAllCookies(driver);
		System.out.println(loginPageCookie);

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
