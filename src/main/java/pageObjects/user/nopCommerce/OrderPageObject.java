package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class OrderPageObject extends BasePage {
	WebDriver driver;

	public OrderPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
