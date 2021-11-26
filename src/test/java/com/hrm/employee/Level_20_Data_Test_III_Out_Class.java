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

public class Level_20_Data_Test_III_Out_Class extends BaseTest {
	WebDriver driver;
	String employeeID;
	String avatarFilePath = GlobalConstants.UPLOAD_FOLDER_PATH + "Avatar.png";
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);
		employeeData = Employee.getEmployee();
		
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
		
		log.info("Add_New_01 - Step 03: Enter valid info to 'First Name' textbox");
		addEmployeePage.enterToTextBoxByID(driver, "firstName", Employee.AddEmployee.FIRSTNAME);
		
		log.info("Add_New_01 - Step 04: Enter valid info to 'Last Name' textbox");
		addEmployeePage.enterToTextBoxByID(driver, "lastName", Employee.AddEmployee.LASTNAME);
		
		log.info("Add_New_01 - Step 05: Get value of 'Employee ID'");
		employeeID = addEmployeePage.getTextboxValueByID(driver, "employeeId");
		
		log.info("Add_New_01 - Step 06: Click to 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");
		
		log.info("Add_New_01 - Step 07: Enter valid info to 'User Name' textbox");
		addEmployeePage.enterToTextBoxByID(driver, "user_name", Employee.AddEmployee.USERNAME);
		
		log.info("Add_New_01 - Step 08: Enter valid info to 'Password' textbox");
		addEmployeePage.enterToTextBoxByID(driver, "user_password", Employee.AddEmployee.PASSWORD);
		
		log.info("Add_New_01 - Step 09: Enter valid info to 'Confirm Password' textbox");
		addEmployeePage.enterToTextBoxByID(driver, "re_password", Employee.AddEmployee.PASSWORD);
		
		log.info("Add_New_01 - Step 10: Select '" + Employee.AddEmployee.STATUS_VALUE + "' value in 'Status' dropdown");
		addEmployeePage.selectItemInDropdownByID(driver, "status", Employee.AddEmployee.STATUS_VALUE);
		
		log.info("Add_New_01 - Step 11: Click to 'Save' button");
		addEmployeePage.clickToButtonByID(driver, "btnSave");
		myInfoPage = PageGenerator.getMyInfoPage(driver);
		
		log.info("Add_New_01 - Step 12: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		
		log.info("Add_New_01 - Step 13: Enter valid info to 'Employee Name' textbox");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		employeeListPage.enterToTextBoxByID(driver, "empsearch_employee_name_empName", Employee.AddEmployee.FULLNAME);
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		
		log.info("Add_New_01 - Step 14: Click 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		
		log.info("Add_New_01 - Step 15: Verify Employee Information is displayed at 'Result Table'");
		verifyEquals(employeeListPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"), employeeID);
		verifyEquals(employeeListPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1"), Employee.AddEmployee.FIRSTNAME);
		verifyEquals(employeeListPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), Employee.AddEmployee.LASTNAME);	
	}

	@Test
	public void Employee_02_Upload_Avatar() {
		log.info("Upload_Avatar_02 - Step 01: Login with Employee role");
		loginPage = employeeListPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, Employee.AddEmployee.USERNAME, Employee.AddEmployee.PASSWORD);
		
		log.info("Upload_Avatar_02 - Step 02: Open 'Personal Details' page");
		dashboardPage.openMenuPage(driver, "My Info");
		myInfoPage = PageGenerator.getMyInfoPage(driver);
		
		log.info("Upload_Avatar_02 - Step 03: Click to Change Photo image");
		myInfoPage.clickToChangePhotoImage();
		
		log.info("Upload_Avatar_02 - Step 04: Upload new Avatar image");
		myInfoPage.uploadFileByID(driver, "file", avatarFilePath);
		
		log.info("Upload_Avatar_02 - Step 05: Click to 'Upload' button");
		myInfoPage.clickToButtonByID(driver, "btnSave");
		
		log.info("Upload_Avatar_02 - Step 06: Verify Success Message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Uploaded"));
		
		log.info("Upload_Avatar_02 - Step 07: Verify new Avatar image is displayed");
		verifyTrue(myInfoPage.isNewAvatarImageDisplayed());
	}
	
	@Test
	public void Employee_03_Personal_Details() {
		log.info("Personal_Details_03 - Step 01: Open 'Personal Details' form at sidebar");
		myInfoPage.openTabAtSideBarByName("Personal Details");
		
		log.info("Personal_Details_03 - Step 02: Verify all fields at 'Personal Details' form are disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmpFirstName"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmpLastName"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmployeeId"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtLicenNo"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtNICNo"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtSINNo"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_optGender_1"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_optGender_2"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_cmbMarital"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_cmbNation"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_DOB"));
		
		log.info("Personal_Details_03 - Step 03: Click to 'Edit' button at 'Personal Dertails' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");
		
		log.info("Personal_Details_03 - Step 04: Verify 'Employee Id' textbox is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmployeeId"));
		
		log.info("Personal_Details_03 - Step 05: Verify 'Driver's License Number' textbox is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtLicenNo"));
		
		log.info("Personal_Details_03 - Step 06: Verify 'SSN Number' textbox is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtNICNo"));
		
		log.info("Personal_Details_03 - Step 06: Verify 'SSN Number' textbox is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtSINNo"));
		
		log.info("Personal_Details_03 - Step 08: Verify 'Date of Birth' textbox is disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_DOB"));
		
		log.info("Personal_Details_03 - Step 09: Enter new value to 'First Name' textbox");
		myInfoPage.enterToTextBoxByID(driver, "personal_txtEmpFirstName", Employee.PersonalDetails.FIRSTNAME);
		
		log.info("Personal_Details_03 - Step 10: Enter new value to 'Last Name' textbox");
		myInfoPage.enterToTextBoxByID(driver, "personal_txtEmpLastName", Employee.PersonalDetails.LASTNAME);
		
		log.info("Personal_Details_03 - Step 11: Select new value to 'Gender' radio button");
		myInfoPage.clickToRadioByLabel(driver, Employee.PersonalDetails.GENDER);
		
		log.info("Personal_Details_03 - Step 13: Select new value to 'Marital Status' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "personal_cmbMarital", Employee.PersonalDetails.MARITAL_STATUS);
		
		log.info("Personal_Details_03 - Step 12: Select new value to 'Nationality' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "personal_cmbNation", Employee.PersonalDetails.NATIONALITY);
		
		log.info("Personal_Details_03 - Step 14: Click to 'Save' button at 'Personal Dertails' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");
		
		log.info("Personal_Details_03 - Step 15: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Personal_Details_03 - Step 16: Verify 'First Name' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpFirstName"), Employee.PersonalDetails.FIRSTNAME);
		
		log.info("Personal_Details_03 - Step 17: Verify 'Last Name' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpLastName"), Employee.PersonalDetails.LASTNAME);
		
		log.info("Personal_Details_03 - Step 18: Verify 'Gender' textbox is updated successfully");
		verifyTrue(myInfoPage.isRadioSelectedByLabel(driver, Employee.PersonalDetails.GENDER));
		
		log.info("Personal_Details_03 - Step 19: Verify 'Marital Status' textbox is updated successfully");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "personal_cmbMarital"), Employee.PersonalDetails.MARITAL_STATUS);
		
		log.info("Personal_Details_03 - Step 20: Verify 'Nationality' textbox is updated successfully");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "personal_cmbNation"), Employee.PersonalDetails.NATIONALITY);
		
		log.info("Personal_Details_03 - Step 21: Verify 'Employee ID' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmployeeId"), employeeID);
	}
	
	@Parameters({"browser"})
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
	Employee employeeData;
}
