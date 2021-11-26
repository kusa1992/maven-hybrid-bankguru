package com.nopcommerce.login;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPageObject;
import utilities.DataUtil;

public class Register_Validation extends BaseTest {
	WebDriver driver;
	String firstname, lastname, emailAddress, password;
	DataUtil fakeData;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void BeforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		
		emailAddress = fakeData.getEmailAddress();
		password = fakeData.getPassword();
		firstname = fakeData.getFirstName();
		lastname = fakeData.getLastName();
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		homePage = PageGeneratorManager.getHomePage(driver);
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());

		registerPage = homePage.clickToRegisterLink();
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getFirstNameErrorMessageText(), "First name is required.");
		Assert.assertEquals(registerPage.getLastNameErrorMessageText(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmailErrorMessageText(), "Email is required.");
		Assert.assertEquals(registerPage.getPasswordErrorMessageText(), "Password is required.");
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(), "Password is required.");
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		registerPage.refreshCurrentPage(driver);
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstname);
		registerPage.enterToLastnameTextbox(lastname);
		registerPage.enterToEmailTextbox("123@@8yashahime");
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(password);
		
		Assert.assertEquals(registerPage.getEmailErrorMessageText(), "Wrong email");
	}
	
	@Test
	public void TC_03_Register_Existing_Email() {
		registerPage.refreshCurrentPage(driver);
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstname);
		registerPage.enterToLastnameTextbox(lastname);
		registerPage.enterToEmailTextbox("qact770@gmail.com");
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getEmailExistingErrorMessageText(), "The specified email already exists");
	}
	
	@Test
	public void TC_04_Register_Password_Less_Than_6_Char() {
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstname);
		registerPage.enterToLastnameTextbox(lastname);
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("12345");
		registerPage.enterToConfirmPasswordTextbox("12345");
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getInvalidPasswordFirstErrorMessage(), "Password must meet the following rules:");
		Assert.assertEquals(registerPage.getInvalidPasswordSecondErrorMessage(), "must have at least 6 characters");
	}
	
	@Test
	public void TC_05_Register_Wrong_Password() {
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstname);
		registerPage.enterToLastnameTextbox(lastname);
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("123456");
		registerPage.enterToConfirmPasswordTextbox("123457");
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(), "The password and confirmation password do not match.");
	}
	
	@Test
	public void TC_06_Register_Correct_Data() {
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstname);
		registerPage.enterToLastnameTextbox(lastname);
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("123456");
		registerPage.enterToConfirmPasswordTextbox("123456");
		registerPage.clickToRegisterButton();
		
		Assert.assertTrue(registerPage.isSuccessMessageDisplayed());
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}

	HomePageObject homePage;
	RegisterPageObject registerPage;
}
