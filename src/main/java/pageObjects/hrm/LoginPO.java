package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class LoginPO extends BasePage {
	WebDriver driver;

	public LoginPO(WebDriver driver) {
		this.driver = driver;
	}

}
