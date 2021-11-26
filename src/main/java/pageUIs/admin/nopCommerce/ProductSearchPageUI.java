package pageUIs.admin.nopCommerce;

public class ProductSearchPageUI {
    public static final String PRODUCT_NAME_TEXTBOX = "//input[@id='SearchProductName']";
    public static final String SEARCH_BUTTON = "//button[@id='search-products']";
    public static final String EDIT_BUTTON_BY_PRODUCT_NAME = "//td[text()='%s']/following-sibling::td/a[contains(string(),'Edit')]";
    public static final String SUCCESS_MESSAGE = "//div[contains(@class,'alert-success') and contains(string(),'%s')]";
    public static final String PRODUCT_IMAGE_BY_PRODUCT_NAME = "//td[text()='%s']/preceding::td/img[contains(@src, '%s')]";
}
