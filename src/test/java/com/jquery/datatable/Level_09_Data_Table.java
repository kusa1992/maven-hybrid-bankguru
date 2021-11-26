package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.HomePageObject;
import pageObjects.jQuery.PageGeneratorManager;

public class Level_09_Data_Table extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}
	
	public void Login_01_Paging() {
		homePage.openPageByNumber("15");
		Assert.assertTrue(homePage.isPageActiveByNumber("15"));

		homePage.openPageByNumber("5");
		Assert.assertTrue(homePage.isPageActiveByNumber("5"));

		homePage.openPageByNumber("20");
		Assert.assertTrue(homePage.isPageActiveByNumber("20"));
	}


	public void Login_02_Input_Header_Textbox() {
		// Input
		homePage.inputToHeaderTextboxByName("Females", "283821");
		homePage.refreshCurrentPage(driver);
		
		homePage.inputToHeaderTextboxByName("Males", "349238");
		homePage.refreshCurrentPage(driver);
		
		homePage.inputToHeaderTextboxByName("Country", "Albania");
		homePage.refreshCurrentPage(driver);
		
		homePage.inputToHeaderTextboxByName("Total", "49397");
		homePage.refreshCurrentPage(driver);
	}
	
	public void Login_03_Click_Icon() {
		// click to Icon
		homePage.clickToIconByCountryName("Algeria", "remove");
		homePage.refreshCurrentPage(driver);
		
		homePage.clickToIconByCountryName("Aruba", "remove");
		homePage.refreshCurrentPage(driver);
		
		homePage.clickToIconByCountryName("Arab Rep of Egypt", "edit");
		homePage.refreshCurrentPage(driver);
		
		homePage.clickToIconByCountryName("Antigua and Barbuda", "edit");
		homePage.refreshCurrentPage(driver);	
	}
	
	public void TC_04_Verify_Row_Values() {
		homePage.inputToHeaderTextboxByName("Country", "Afghanistan");
		Assert.assertTrue(homePage.isRowValueDisplayed("384187", "Afghanistan", "407124", "791312"));
		homePage.refreshCurrentPage(driver);
		
		homePage.inputToHeaderTextboxByName("Country", "Armenia");
		Assert.assertTrue(homePage.isRowValueDisplayed("15999", "Armenia", "16456", "32487"));
		homePage.refreshCurrentPage(driver);
	}
	
	public void Login_05_Input_To_Row_Textbox() {
		homePage.inputToTextboxByRowNumber("Contact Person", "3", "John Kenedy");
		
		homePage.inputToTextboxByRowNumber("Order Placed", "1", "5");
		
		homePage.inputToTextboxByRowNumber("Company", "2", "Facebook");
		
		homePage.inputToTextboxByRowNumber("Member Since", "2", "11251964");
	}
	
	@Test
	public void Login_06_Click_Icon_At_Row() {
		homePage.clickToIconByRowNumber("3", "Remove Current Row");
		
		homePage.clickToIconByRowNumber("2", "Remove Current Row");
		
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
	}
	
	@AfterClass
	public void cleanBrowser() {
		driver.quit();
	}
}
