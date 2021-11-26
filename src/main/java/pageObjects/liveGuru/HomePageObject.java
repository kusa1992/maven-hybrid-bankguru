package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.HomePageUI;

public class HomePageObject extends BasePage{
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToMyAccountFooterLink() {
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_FOOTER);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_FOOTER);
	}
}
