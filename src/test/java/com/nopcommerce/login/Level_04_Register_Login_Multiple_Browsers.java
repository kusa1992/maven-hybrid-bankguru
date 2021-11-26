package com.nopcommerce.login;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.RegisterPageObject;
import utilities.DataUtil;

public class Level_04_Register_Login_Multiple_Browsers extends BaseTest {
	WebDriver driver;
	String emailAddress, password;
	DataUtil fakeData;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		System.out.println("Driver at test class = " + driver.toString());

		emailAddress = fakeData.getEmailAddress();
		password = fakeData.getPassword();
	}

	@Test
	public void Login_01_Register_To_System() {
		homePage = new HomePageObject(driver);

		// Step 2: Verify Home Page Slider displayed
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());

		// Step 3: Click to Register link -> Register Page
		homePage.clickToRegisterLink();

		registerPage = new RegisterPageObject(driver);

		// Step 4: Click to Gender male radio
		registerPage.clickToGenderMaleRadioButton();

		// Step 5: Input to Firstname textbox
		registerPage.enterToFirstnameTextbox("Thao");

		// Step 6: Input to Lastname textbox
		registerPage.enterToLastnameTextbox("Nguyen");

		// Step 7: Input to Email textbox
		registerPage.enterToEmailTextbox(emailAddress);

		// Step 8: Input to Password textbox
		registerPage.enterToPasswordTextbox(password);

		// Step 9: Input to Confirm password textbox
		registerPage.enterToConfirmPasswordTextbox(password);

		// Step 10: Click to Register button
		registerPage.clickToRegisterButton();

		// Step 11: Verify Success message displayed
		Assert.assertTrue(registerPage.isSuccessMessageDisplayed());

		// Step 12: Click to Logout link -> Home Page
		registerPage.clickToLogoutLink();
		homePage = new HomePageObject(driver);

		// Step 13: Verify Home Page Slider displayed
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());
	}

	@Test
	public void Login_02_Login_To_System() {
		// Step 1: Click to login link
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);

		// Step 2: Input to Email textbox
		loginPage.enterToEmailTextbox(emailAddress);

		// Step 3: Input to Password textbox
		loginPage.enterToPasswordTextbox(password);

		// Step 4: Click to Login button
		loginPage.clickToLoginButton();
		homePage = new HomePageObject(driver);

		// Step 5: Verify Home Page Logo displayed
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());
	}

	@AfterClass
	public void cleanBrowser() {
		driver.quit();
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
}
