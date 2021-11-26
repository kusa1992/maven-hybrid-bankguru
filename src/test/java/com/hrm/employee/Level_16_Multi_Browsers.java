package com.hrm.employee;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.data.hrm.Employee;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.hrm.AddEmployeePO;
import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.EmployeeListPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.MyInfoPO;
import pageObjects.hrm.PageGenerator;

public class Level_16_Multi_Browsers extends BaseTest {
	WebDriver driver;
	String employeeID;
	String avatarFilePath = GlobalConstants.UPLOAD_FOLDER_PATH + "Avatar.png";
	String contractFilePath = GlobalConstants.UPLOAD_FOLDER_PATH + "Labour Agreement.docx";

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);

		log.info("Pre-Condition - Step 02: Login with Admin role");
		dashboardPage = loginPage.loginToSystem(driver, Employee.Role.ADMIN_USERNAME, Employee.Role.ADMIN_PASSWORD);
	}

	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Add_New_01 - Step 01: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Add_New_01 - Step 02: Click to 'Add' button");
		employeeListPage.clickToButtonByID(driver, "btnAdd");
		addEmployeePage = PageGenerator.getAddEmployeePage(driver);

		log.info("Add_New_01 - Step 03: Enter valid info to 'First Name' textbox with value: " + Employee.AddEmployee.FIRSTNAME);
		addEmployeePage.enterToTextBoxByID(driver, "firstName", Employee.AddEmployee.FIRSTNAME);

		log.info("Add_New_01 - Step 04: Enter valid info to 'Last Name' textbox with value: " + Employee.AddEmployee.LASTNAME);
		addEmployeePage.enterToTextBoxByID(driver, "lastName", Employee.AddEmployee.LASTNAME);

		log.info("Add_New_01 - Step 05: Get value of 'Employee ID'");
		employeeID = addEmployeePage.getTextboxValueByID(driver, "employeeId");

		log.info("Add_New_01 - Step 06: Click to 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

		log.info("Add_New_01 - Step 07: Enter valid info to 'User Name' textbox with value: " + Employee.AddEmployee.USERNAME);
		addEmployeePage.enterToTextBoxByID(driver, "user_name", Employee.AddEmployee.USERNAME);

		log.info("Add_New_01 - Step 08: Enter valid info to 'Password' textbox with value: " + Employee.AddEmployee.PASSWORD);
		addEmployeePage.enterToTextBoxByID(driver, "user_password", Employee.AddEmployee.PASSWORD);

		log.info("Add_New_01 - Step 09: Enter valid info to 'Confirm Password' textbox with value: " + Employee.AddEmployee.PASSWORD);
		addEmployeePage.enterToTextBoxByID(driver, "re_password", Employee.AddEmployee.PASSWORD);

		log.info("Add_New_01 - Step 10: Select '" + Employee.AddEmployee.STATUS_VALUE + "' value in 'Status' dropdown");
		addEmployeePage.selectItemInDropdownByID(driver, "status", Employee.AddEmployee.STATUS_VALUE);

		log.info("Add_New_01 - Step 11: Click to 'Save' button");
		addEmployeePage.clickToButtonByID(driver, "btnSave");
		myInfoPage = PageGenerator.getMyInfoPage(driver);

		log.info("Add_New_01 - Step 12: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Add_New_01 - Step 13: Enter valid info to 'Employee Name' textbox with value: " + Employee.AddEmployee.FULLNAME);
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		employeeListPage.enterToTextBoxByID(driver, "empsearch_employee_name_empName", Employee.AddEmployee.FULLNAME);
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Add_New_01 - Step 14: Click 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");

		log.info("Add_New_01 - Step 15: Verify Employee Information is displayed at 'Result Table'");
		verifyEquals(employeeListPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"), employeeID);
		verifyEquals(employeeListPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1"), Employee.AddEmployee.FIRSTNAME);
		verifyEquals(employeeListPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), Employee.AddEmployee.LASTNAME);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition: Close browser '" + browserName + "'");
		cleanBrowserAndDriver();
	}

	LoginPO loginPage;
	AddEmployeePO addEmployeePage;
	DashboardPO dashboardPage;
	EmployeeListPO employeeListPage;
	MyInfoPO myInfoPage;
}
