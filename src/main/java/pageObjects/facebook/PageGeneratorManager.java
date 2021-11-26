package pageObjects.facebook;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    private PageGeneratorManager() {

    }
    
    public static RegisterPageObject getRegisterPageObject(WebDriver driver) {
    	return new RegisterPageObject(driver);
    }
}