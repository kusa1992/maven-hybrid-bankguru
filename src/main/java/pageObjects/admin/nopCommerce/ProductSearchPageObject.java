package pageObjects.admin.nopCommerce;

import commons.BasePage;
import pageUIs.admin.nopCommerce.ProductSearchPageUI;

import org.openqa.selenium.WebDriver;

public class ProductSearchPageObject extends BasePage {
    WebDriver driver;

    public ProductSearchPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToProductNameTextbox(String productName) {
    	waitForElementVisible(driver, ProductSearchPageUI.PRODUCT_NAME_TEXTBOX);
    	sendkeyToElement(driver, ProductSearchPageUI.PRODUCT_NAME_TEXTBOX, productName);
    }

    public void clickToSearchButton() {
    	waitForElementClickable(driver, ProductSearchPageUI.SEARCH_BUTTON);
    	clickToElement(driver, ProductSearchPageUI.SEARCH_BUTTON);
    }

    public ProductDetailPageObject clickToEditButtonByProductName(String productName) {
    	waitForElementClickable(driver, ProductSearchPageUI.EDIT_BUTTON_BY_PRODUCT_NAME, productName);
    	clickToElement(driver, ProductSearchPageUI.EDIT_BUTTON_BY_PRODUCT_NAME, productName);
    	return PageGeneratorManager.getProductDetailPage(driver);
    }

    public boolean isSuccessMessageDisplayed(String successMessage) {
    	waitForElementVisible(driver, ProductSearchPageUI.SUCCESS_MESSAGE, successMessage);
        return isElementDisplayed(driver, ProductSearchPageUI.SUCCESS_MESSAGE, successMessage);
    }

    public boolean isPictureImageUpdated(String productName, String productImageName) {
    	productImageName = productImageName.replace(" ", "-").toLowerCase();
    	waitForElementVisible(driver, ProductSearchPageUI.PRODUCT_IMAGE_BY_PRODUCT_NAME, productName, productImageName);
        return isElementDisplayed(driver, ProductSearchPageUI.PRODUCT_IMAGE_BY_PRODUCT_NAME, productName, productImageName);
    }
}
