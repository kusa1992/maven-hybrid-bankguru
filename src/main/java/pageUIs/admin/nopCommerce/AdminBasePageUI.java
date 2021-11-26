package pageUIs.admin.nopCommerce;

public class AdminBasePageUI {
	public static final String MENU_LINK_BY_NAME = "(//ul[@role='menu']//p[contains(text(),'%s')])[1]";
	public static final String SUB_MENU_LINK_BY_NAME = "(//a/i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(text(),'%s')])[1]";
	public static final String UPLOAD_FILE_BY_CARD_NAME = "//div[@id='product-%s']//input[@title='file input']";
	public static final String NO_DATA_MESSAGE_BY_TABLE_NAME = "//table[@id='%s-grid']//td[@class='dataTables_empty' and text() = 'No data available in table']";
}
