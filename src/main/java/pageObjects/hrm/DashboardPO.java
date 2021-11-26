package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class DashboardPO extends BasePage {
	WebDriver driver;

	public DashboardPO(WebDriver driver) {
		this.driver = driver;
	}
}
