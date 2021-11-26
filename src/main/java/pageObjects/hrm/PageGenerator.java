package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

public class PageGenerator {
	
	public static LoginPO getLoginPage(WebDriver driver) {
		return new LoginPO(driver);
	}
	
	public static AddEmployeePO getAddEmployeePage(WebDriver driver) {
		return new AddEmployeePO(driver);
	}
	
	public static DashboardPO getDashboardPage(WebDriver driver) {
		return new DashboardPO(driver);
	}
	
	public static EmployeeListPO getEmployeeListPage(WebDriver driver) {
		return new EmployeeListPO(driver);
	}
	
	public static MyInfoPO getMyInfoPage(WebDriver driver) {
		return new MyInfoPO(driver);
	}
}
