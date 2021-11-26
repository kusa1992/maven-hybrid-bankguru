package pageObjects.sauceLab;

import org.openqa.selenium.WebDriver;

public class PageGenerator {
	private PageGenerator() {
		
	}

	public static LoginPageObject getLoginPage(WebDriver driver) {
	        return new LoginPageObject(driver);
	}

	public static InventoryPageObject getInventoryPage(WebDriver driver) {
        return new InventoryPageObject(driver);
}

}
