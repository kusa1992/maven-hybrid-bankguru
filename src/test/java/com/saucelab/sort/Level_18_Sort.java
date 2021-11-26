package com.saucelab.sort;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.sauceLab.InventoryPageObject;
import pageObjects.sauceLab.LoginPageObject;
import pageObjects.sauceLab.PageGenerator;

public class Level_18_Sort extends BaseTest {
	WebDriver driver;
	String emailAddress, password, pageName, company, date, month, year;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-Condition: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);

		loginPage = PageGenerator.getLoginPage(driver);
		loginPage.enterToUserNameTextbox("standard_user");
		loginPage.enterToPasswordTextbox("secret_sauce");
		inventoryPage = loginPage.clicktoLoginButton();
	}

	@Test
	public void Sort_01_Name() {
		inventoryPage.selectItemInSortDropDown("Name (A to Z)");
		verifyTrue(inventoryPage.isProductNameSortAscending());
		
		inventoryPage.selectItemInSortDropDown("Name (Z to A)");
		verifyTrue(inventoryPage.isProductNameSortDescending());
	}
	
	@Test
	public void Sort_02_Name() {
		inventoryPage.selectItemInSortDropDown("Name (A to Z)");
		verifyTrue(inventoryPage.isProductNameSortDescending());
	}

	@Test
	public void Sort_03_Price() {
		inventoryPage.selectItemInSortDropDown("Price (low to high)");
		verifyTrue(inventoryPage.isProductNamePriceAscending());
		
		inventoryPage.selectItemInSortDropDown("Price (high to low)");
		verifyTrue(inventoryPage.isProductNamePriceDescending());
	}
	
	@Parameters({"browser"})
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition: Close browser '" + browserName + "'");
		cleanBrowserAndDriver();
	}

	LoginPageObject loginPage;
	InventoryPageObject inventoryPage;
}
