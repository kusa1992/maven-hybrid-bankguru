package pageObjects.admin.nopCommerce;

import org.openqa.selenium.WebDriver;


public class PageGeneratorManager {
    private PageGeneratorManager() {

    }

    public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static DashboardPageObject getDashboardPage(WebDriver driver) {
        return new  DashboardPageObject(driver);
    }

    public static ProductSearchPageObject getProductSearchPage(WebDriver driver) {
        return new ProductSearchPageObject(driver);
    }

    public static ProductDetailPageObject getProductDetailPage(WebDriver driver) {
        return new ProductDetailPageObject(driver);
    }
}
