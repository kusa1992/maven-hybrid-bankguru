package com.nopcommerce.login;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.MyAccountPageObject;
import pageObjects.user.nopCommerce.OrderPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPageObject;
import pageObjects.user.nopCommerce.SearchPageObject;
import utilities.DataUtil;

@Epic("Web")
@Feature("User")
public class Level_13_Register_Login_Log_Allure extends BaseTest {
	WebDriver driver;
	String emailAddress, password, pageName;
	DataUtil fakeData;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);

		emailAddress = fakeData.getEmailAddress();
		password = fakeData.getPassword();
	}

	@Story("Register")
	@Severity(SeverityLevel.NORMAL)
	@Description("Register to system and verify registered success")
	@Test
	public void User_01_Register_To_System(Method method) {
		log.info("User_01_Register - Step 01: Verify Home Page is displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("User_01_Register - Step 02: Click to Register link");
		registerPage = homePage.clickToRegisterLink();

		log.info("User_01_Register - Step 03: Click to Male radio button");
		registerPage.clickToGenderMaleRadioButton();

		log.info("User_01_Register - Step 04: Enter to Firstname textbox");
		registerPage.enterToFirstnameTextbox("Thao");

		log.info("User_01_Register - Step 05: Enter to Lastname textbox");
		registerPage.enterToLastnameTextbox("Nguyen");

		log.info("User_01_Register - Step 06: Enter to Email textbox with value " + emailAddress);
		registerPage.enterToEmailTextbox(emailAddress);

		log.info("User_01_Register - Step 07: Enter to Password textbox with value " + password);
		registerPage.enterToPasswordTextbox(password);

		log.info("User_01_Register - Step 08: Enter to Confirm Password textbox with value " + password);
		registerPage.enterToConfirmPasswordTextbox(password);

		log.info("User_01_Register - Step 09: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("User_01_Register - Step 10: Verify success message is displayed");
		verifyTrue(registerPage.isSuccessMessageDisplayed());

		log.info("User_01_Register - Step 11: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();
		System.out.println("Home Page is " + homePage.hashCode());

		log.info("User_01_Register - Step 12: Verify Home Page is displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());
	}

	@Story("Login")
	@Severity(SeverityLevel.NORMAL)
	@Description("Login to system and verify logged in success ")
	@Test
	public void User_02_Login_To_System(Method method) {
		log.info("User_02_Login - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();

		log.info("User_02_Login - Step 02: Enter to Email textbox with value " + emailAddress);
		loginPage.enterToEmailTextbox(emailAddress);

		log.info("User_02_Login - Step 03: Enter to Password textbox with value " + password);
		loginPage.enterToPasswordTextbox(password);

		log.info("User_02_Login - Step 04: Click to Login button");
		homePage = loginPage.clickToLoginButton();

		log.info("User_02_Login - Step 05: Verify Home Page is displayed");
		verifyFalse(homePage.isHomePageSliderDisplayed());
		homePage.sleepInSecond(2);
	}

	@AfterClass
	public void cleanBrowser() {
		driver.quit();
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	SearchPageObject searchPage;
	MyAccountPageObject myAccountPage;
	OrderPageObject orderPage;
}
