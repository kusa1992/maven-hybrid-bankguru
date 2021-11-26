package com.facebook.register;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.PageGeneratorManager;
import pageObjects.facebook.RegisterPageObject;

public class Level_12_Register_Login_Assert_Verify extends BaseTest {
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
	public void Register_01_Verify() {
		// Failed lan 1
		verifyFalse(registerPage.isEmailTextboxDisplayed());

		registerPage.enterToEmailTextbox(emailAddress);
		// Failed lan 2
		verifyFalse(registerPage.isConfirmEmailTextboxDisplayed());
		
		registerPage.enterToEmailTextbox("");
		registerPage.sleepInSecond(1);
		
		verifyFalse(registerPage.isConfirmEmailTextboxDisplayed());
		
		verifyTrue(registerPage.isConfirmEmailTextboxUndisplayed());
		
		// Failed lan 3
		verifyTrue(registerPage.isLoginButtonDiplayed());
	}
	
	@AfterClass
	public void cleanBrowser() {
		driver.quit();
	}
}
