package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.MyDashboadPageUI;

public class MyDashboardPageObject extends BasePage{
	WebDriver driver;

	public MyDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isMyDashBoardHeaderDisplayed() {
		waitForElementVisible(driver, MyDashboadPageUI.MY_DASHBOARD_HEADER_TEXT);
		return isElementDisplayed(driver, MyDashboadPageUI.MY_DASHBOARD_HEADER_TEXT);
	}
}
