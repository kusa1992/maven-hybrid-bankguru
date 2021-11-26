package com.nopcommerce.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.RegisterPageObject;

public class Level_03_Register_Login_Page_Object {
	WebDriver driver;
	String emailAddress, password;
	String projectLocation = System.getProperty("user.dir");

	@BeforeClass
	public void initBrowser() {
		System.setProperty("webdriver.gecko.driver", projectLocation + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		System.out.println("Driver at Testcase" + driver.toString());
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		emailAddress = getRandomEmail();
		password = "123456";
	}

	@Test
	public void Login_01_Register_To_System() {
		// Step 1: Mo URL ra -> Home Page
		driver.get("https://demo.nopcommerce.com/");
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

	public String getRandomEmail() {
		Random rand = new Random();
		return "testing" + rand.nextInt(99999) + "@live.com";
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
}
