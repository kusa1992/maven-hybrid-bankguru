package com.nopcommerce.admin;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.admin.nopCommerce.*;

public class Level_10_Admin_Upload_File extends BaseTest {
	WebDriver driver;
	String productName = "Adobe Photoshop CS4";
	String productImg = "Cucumber.png";
	String productAlt = "Cucumber Alt";
	String productTitle = "Cucumber Title";
	String productOrder = "1";
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ProductSearchPageObject productSearchPage;
	ProductDetailPageObject productDetailPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.enterToEmailTextbox("admin@yourstore.com");
		loginPage.enterToPasswordTextbox("admin");
		dashboardPage = loginPage.clickToLoginButton();

		dashboardPage.openSubMenuPageByName(driver, "Catalog", "Products");
		productSearchPage = PageGeneratorManager.getProductSearchPage(driver);

		productSearchPage.enterToProductNameTextbox(productName);

		productSearchPage.clickToSearchButton();

		productDetailPage = productSearchPage.clickToEditButtonByProductName(productName);
	}

	@Test
	public void Admin_01_Upload_File() {
		productDetailPage.clickToExpandPanelByName("Pictures");

		productDetailPage.uploadFileAtCardName(driver, "pictures", productImg);

		Assert.assertTrue(productDetailPage.isPictureUploadedSuccessByFileName("Cucumber"));

		productDetailPage.enterToAltTextbox(productAlt);
		productDetailPage.enterToTitleTextbox(productTitle);
		productDetailPage.clickToUpDownInDislayedOrderTextbox("Increase");

		productDetailPage.clickToAddProductPictureButton();

		Assert.assertTrue(productDetailPage.isPictureImageDisplayed(productName, productOrder, productAlt, productTitle));

		productDetailPage.clickSaveButton();

		Assert.assertTrue(productSearchPage.isSuccessMessageDisplayed("The product has been updated successfully."));

		productSearchPage.enterToProductNameTextbox(productName);

		productSearchPage.clickToSearchButton();

		Assert.assertTrue(productSearchPage.isPictureImageUpdated(productName, productName));

		productSearchPage.clickToEditButtonByProductName(productName);

		productDetailPage.clickToExpandPanelByName("Pictures");

		productDetailPage.clickToDeleteButtonByPictureName(productTitle); // Accept Alert

		Assert.assertTrue(productDetailPage.isMessageDisplayedInEmptyTable(driver, "productpictures"));

		productDetailPage.clickSaveButton();

		productSearchPage.enterToProductNameTextbox(productName);

		productSearchPage.clickToSearchButton();

		Assert.assertTrue(productSearchPage.isPictureImageUpdated(productName, "default-image"));
	}

	@AfterClass
	public void cleanBrowser() {
		driver.quit();
	}
}
