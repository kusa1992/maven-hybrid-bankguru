package com.facebook.register;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.PageGeneratorManager;
import pageObjects.facebook.RegisterPageObject;

public class Level_11_Register_Login_Element_Undisplayed extends BaseTest {
	WebDriver driver;
	String emailAddress, password;
	RegisterPageObject registerPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);

		registerPage = PageGeneratorManager.getRegisterPageObject(driver);

		emailAddress = "qact770@gmail.com";
	}

	@Test
	public void Register_01_Element_Displayed() {
		// Displayed: Visible on UI + Exist in DOM
		// waitVisible
		// isDisplayed
		Assert.assertTrue(registerPage.isEmailTextboxDisplayed());

		// Business: Input to Email textbox
		// Confirm email isdisplayed
		registerPage.enterToEmailTextbox(emailAddress);
		Assert.assertTrue(registerPage.isConfirmEmailTextboxDisplayed());
	}

	@Test
	public void Register_02_Element_Undisplayed_In_DOM() {
		// Undisplay: Invisible on UI + Exist in DOM
		// isDisplayed
		// Business: clear email textbox
		// Confirm email is undisplayed
		registerPage.enterToEmailTextbox("");
		registerPage.sleepInSecond(1);
		Assert.assertFalse(registerPage.isConfirmEmailTextboxDisplayed());
		Assert.assertTrue(registerPage.isConfirmEmailTextboxUndisplayed());
	}

	@Test
	public void Register_03_Element_Undisplayed_Not_In_DOM() {
		// Undisplayed: Invisible on UI + not exist in DOM
		// isDisplayed -> false (try-catch)
		// Wait maxium implicit timeout 20s
		// Phu dinh
		Assert.assertFalse(registerPage.isLoginButtonDiplayed());
	}

	@Test
	public void Register_04_Element_Undisplayed_Not_In_DOM() {
		// Undisplayed: Invisible on UI + not exist in DOM
		// findElements
		// override implicit timeout

		// Khang dinh
		Assert.assertTrue(registerPage.isLoginButtonUndiplayed());
	}

	@AfterClass
	public void cleanBrowser() {
		driver.quit();
	}
}
