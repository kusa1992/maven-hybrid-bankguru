package com.liveguru.login;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.liveGuru.HomePageObject;
import pageObjects.liveGuru.LoginPageObject;
import pageObjects.liveGuru.MyDashboardPageObject;

public class Level_17_Independent_Test extends BaseTest{
	WebDriver driver;
	String emailAddress, password;
	
	@Parameters({"browser", "url"})
	@BeforeMethod // Chay truoc tung test case
	public void beforeMethod(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		System.out.println("Driver at test class = " + driver.toString());
		
		emailAddress = getRandomEmail();
		password = "123456";
		
		homePage = new HomePageObject(driver);
		homePage.clickToMyAccountFooterLink();
		loginPage = new LoginPageObject(driver);
	}

	@Test
	public void Login_01_Empty_Email_And_Password() {
		loginPage.loginToSystem("", "");

		Assert.assertEquals(loginPage.getEmptyEmailErrorMessage(), "This is a required field.");
		Assert.assertEquals(loginPage.getEmptyPasswordErrorMessage(), "This is a required field.");
	}

	@Test
	public void Login_02_Invalid_Email() {
		loginPage.refreshCurrentPage(driver);

		loginPage.loginToSystem("123@456.789", "123456");

		Assert.assertEquals(loginPage.getInvalidEmailErrorMessage(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void Login_03_Invalid_Password() {
		loginPage.loginToSystem("tsukushi@gmail.com", "123");

		Assert.assertEquals(loginPage.getInvalidPasswordErrorMessage(), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void Login_04_Incorrect_Email() {
		loginPage.loginToSystem(emailAddress, "123456");

		Assert.assertEquals(loginPage.getInvalidEmailOrPasswordErrorMessage(), "Invalid login or password.");
	}

	@Test
	public void Login_05_Incorrect_Password() {
		loginPage.loginToSystem("tsukushi@gmail.com", "123123");

		Assert.assertEquals(loginPage.getInvalidEmailOrPasswordErrorMessage(), "Invalid login or password.");
	}

	@Test
	public void Login_06_Valid_Email_And_Password() {
		loginPage.loginToSystem("tsukushi@gmail.com", "123456");

		myDashboardPage = new MyDashboardPageObject(driver);
		Assert.assertTrue(myDashboardPage.isMyDashBoardHeaderDisplayed());
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	public String getRandomEmail() {
		Random rand = new Random();
		return "testing" + rand.nextInt(99999) + "@live.com";
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	MyDashboardPageObject myDashboardPage;
}
