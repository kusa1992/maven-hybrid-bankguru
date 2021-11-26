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

public class Level_16_Live_Coding extends BaseTest {
	WebDriver driver;
	String employeeID;
	String avatarFilePath = GlobalConstants.UPLOAD_FOLDER_PATH + "Avatar.png";
	String contractFilePath = GlobalConstants.UPLOAD_FOLDER_PATH + "Labour Agreement.docx";
	
	@Parameters({"browser", "url"})
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

	@Test
	public void Employee_02_Upload_Avatar() {
		log.info("Upload_Avatar_02 - Step 01: Login with Employee role: " + Employee.AddEmployee.USERNAME + ", " + Employee.AddEmployee.PASSWORD);
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

		log.info("Personal_Details_03 - Step 09: Enter new value to 'First Name' textbox with value: " + Employee.PersonalDetails.FIRSTNAME);
		myInfoPage.enterToTextBoxByID(driver, "personal_txtEmpFirstName", Employee.PersonalDetails.FIRSTNAME);

		log.info("Personal_Details_03 - Step 10: Enter new value to 'Last Name' textbox with value: " + Employee.PersonalDetails.LASTNAME);
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

	@Test
	public void Employee_04_Contact_Details() {
		log.info("Contact_Details_04 - Step 01: Open 'Contact Details' form at sidebar");
		myInfoPage.openTabAtSideBarByName("Contact Details");

		log.info("Contact_Details_04 - Step 02: Verify all fields at 'Contact Details' form are disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_street1"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_street2"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_city"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_province"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_emp_zipcode"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_country"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_emp_hm_telephone"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_emp_mobile"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_emp_work_telephone"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_emp_work_email"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_emp_oth_email"));

		log.info("Contact_Details_04 - Step 03: Click to 'Edit' button at 'Contact Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Contact_Details_04 - Step 04: Enter new value to 'Address Street 1' textbox with value: " + Employee.ContactDetails.ADDRESS_1);
		myInfoPage.enterToTextBoxByID(driver, "contact_street1", Employee.ContactDetails.ADDRESS_1);

		log.info("Contact_Details_04 - Step 05: Enter new value to 'Address Street 2' textbox with value: " + Employee.ContactDetails.ADDRESS_2);
		myInfoPage.enterToTextBoxByID(driver, "contact_street2", Employee.ContactDetails.ADDRESS_2);

		log.info("Contact_Details_04 - Step 06: Enter new value to 'City' textbox with value: " + Employee.ContactDetails.CITY);
		myInfoPage.enterToTextBoxByID(driver, "contact_city", Employee.ContactDetails.CITY);

		log.info("Contact_Details_04 - Step 07: Enter new value to 'State/Province' textbox with value: " + Employee.ContactDetails.STATE);
		myInfoPage.enterToTextBoxByID(driver, "contact_province", Employee.ContactDetails.STATE);

		log.info("Contact_Details_04 - Step 09: Enter new value to 'Zip/Postal Code' textbox with value: " + Employee.ContactDetails.ZIPCODE);
		myInfoPage.enterToTextBoxByID(driver, "contact_emp_zipcode", Employee.ContactDetails.ZIPCODE);

		log.info("Contact_Details_04 - Step 10: Select new value to 'Country' dropdown with value: " + Employee.ContactDetails.COUNTRY);
		myInfoPage.selectItemInDropdownByID(driver, "contact_country", Employee.ContactDetails.COUNTRY);

		log.info("Contact_Details_04 - Step 11: Enter new value to 'Home Telephone' textbox with value: " + Employee.ContactDetails.HOME_TELEPHONE);
		myInfoPage.enterToTextBoxByID(driver, "contact_emp_hm_telephone", Employee.ContactDetails.HOME_TELEPHONE);

		log.info("Contact_Details_04 - Step 12: Enter new value to 'Mobile' textbox with value: " + Employee.ContactDetails.MOBILE);
		myInfoPage.enterToTextBoxByID(driver, "contact_emp_mobile", Employee.ContactDetails.MOBILE);

		log.info("Contact_Details_04 - Step 13: Enter new value to 'Work Telephone' textbox");
		myInfoPage.enterToTextBoxByID(driver, "contact_emp_work_telephone", Employee.ContactDetails.WORK_TELEPHONE);

		log.info("Contact_Details_04 - Step 14: Enter new value to 'Work Email' textbox with value: " + Employee.ContactDetails.WORK_EMAIL);
		myInfoPage.enterToTextBoxByID(driver, "contact_emp_work_email", Employee.ContactDetails.WORK_EMAIL);

		log.info("Contact_Details_04 - Step 15: Enter new value to 'Other Email' textbox with value: " + Employee.ContactDetails.OTHER_EMAIL);
		myInfoPage.enterToTextBoxByID(driver, "contact_emp_oth_email", Employee.ContactDetails.OTHER_EMAIL);

		log.info("Contact_Details_04 - Step 16: Click to 'Save' button at 'Contact Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Contact_Details_04 - Step 17: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Contact_Details_04 - Step 18: Verify 'Address Street 1' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_street1"), Employee.ContactDetails.ADDRESS_1);

		log.info("Contact_Details_04 - Step 19: Verify 'Address Street 1' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_street2"), Employee.ContactDetails.ADDRESS_2);

		log.info("Contact_Details_04 - Step 20: Verify 'City' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_city"), Employee.ContactDetails.CITY);

		log.info("Contact_Details_04 - Step 21: Verify 'State/Province' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_province"), Employee.ContactDetails.STATE);

		log.info("Contact_Details_04 - Step 22: Verify 'Zip/Postal Code' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_zipcode"), Employee.ContactDetails.ZIPCODE);

		log.info("Contact_Details_04 - Step 22: Verify 'Country' dropdown is updated successfully");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "contact_country"), Employee.ContactDetails.COUNTRY);

		log.info("Contact_Details_04 - Step 23: Verify 'Home Telephone' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_hm_telephone"), Employee.ContactDetails.HOME_TELEPHONE);

		log.info("Contact_Details_04 - Step 24: Verify 'Mobile' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_mobile"), Employee.ContactDetails.MOBILE);

		log.info("Contact_Details_04 - Step 25: Verify 'Work Telephone' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_work_telephone"), Employee.ContactDetails.WORK_TELEPHONE);

		log.info("Contact_Details_04 - Step 26: Verify 'Work Email' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_work_email"), Employee.ContactDetails.WORK_EMAIL);

		log.info("Contact_Details_04 - Step 27: Verify 'Other Email' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_oth_email"), Employee.ContactDetails.OTHER_EMAIL);
	}

	@Test
	public void Employee_05_Emergency_Contacts() {
		log.info("Emergency_Contacts_05 - Step 01: Open 'Emergency_Contacts' form at sidebar");
		myInfoPage.openTabAtSideBarByName("Emergency Contacts");

		log.info("Emergency_Contacts_05 - Step 02: Click 'Add' button at 'Assigned Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnAddContact");

		log.info("Emergency_Contacts_05 - Step 03: Enter new value to 'Name' textbox with value: " + Employee.EmergencyContacts.NAME_1);
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_name", Employee.EmergencyContacts.NAME_1);

		log.info("Emergency_Contacts_05 - Step 04: Enter new value to 'Relationship' textbox with value: " + Employee.EmergencyContacts.RELATIONSHIP_1);
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_relationship", Employee.EmergencyContacts.RELATIONSHIP_1);

		log.info("Emergency_Contacts_05 - Step 05: Enter new value to 'Home Telephone' textbox with value: " + Employee.EmergencyContacts.HOME_TELEPHONE_1);
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_homePhone", Employee.EmergencyContacts.HOME_TELEPHONE_1);

		log.info("Emergency_Contacts_05 - Step 06: Enter new value to 'Mobile' textbox with value: " + Employee.EmergencyContacts.MOBILE_1);
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_mobilePhone", Employee.EmergencyContacts.MOBILE_1);

		log.info("Emergency_Contacts_05 - Step 07: Enter new value to 'Work Telephone' textbox with value: " + Employee.EmergencyContacts.WORK_TELEPHONE_1);
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_workPhone", Employee.EmergencyContacts.WORK_TELEPHONE_1);

		log.info("Emergency_Contacts_05 - Step 08: Click 'Save' button at 'Assigned Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveEContact");

		log.info("Emergency_Contacts_05 - Step 09: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Emergency_Contacts_05 - Step 10: Click 'Add' button at 'Assigned Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnAddContact");

		log.info("Emergency_Contacts_05 - Step 03: Enter new value to 'Name' textbox with value: " + Employee.EmergencyContacts.NAME_2);
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_name", Employee.EmergencyContacts.NAME_2);

		log.info("Emergency_Contacts_05 - Step 04: Enter new value to 'Relationship' textbox with value: " + Employee.EmergencyContacts.RELATIONSHIP_2);
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_relationship", Employee.EmergencyContacts.RELATIONSHIP_2);

		log.info("Emergency_Contacts_05 - Step 05: Enter new value to 'Home Telephone' textbox with value: " + Employee.EmergencyContacts.HOME_TELEPHONE_2);
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_homePhone", Employee.EmergencyContacts.HOME_TELEPHONE_2);

		log.info("Emergency_Contacts_05 - Step 06: Enter new value to 'Mobile' textbox with value: " + Employee.EmergencyContacts.MOBILE_2);
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_mobilePhone", Employee.EmergencyContacts.MOBILE_2);

		log.info("Emergency_Contacts_05 - Step 07: Enter new value to 'Work Telephone' textbox with value: " + Employee.EmergencyContacts.WORK_TELEPHONE_2);
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_workPhone", Employee.EmergencyContacts.WORK_TELEPHONE_2);

		log.info("Emergency_Contacts_05 - Step 08: Click 'Save' button at 'Assigned Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveEContact");

		log.info("Emergency_Contacts_05 - Step 09: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		myInfoPage.sleepInSecond(2);
		
		log.info("Emergency_Contacts_05 - Step 10: Verify Emergency Contacts are displayed at 'Emergency Contacts' table");
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Name", "1"), Employee.EmergencyContacts.NAME_1);
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Relationship", "1"), Employee.EmergencyContacts.RELATIONSHIP_2);
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Name", "2"), Employee.EmergencyContacts.NAME_1);
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Home Telephone", "2"), Employee.EmergencyContacts.HOME_TELEPHONE_1);
		myInfoPage.sleepInSecond(2);
	}

	@Test
	public void Employee_06_Assigned_Dependents() {
		log.info("Assigned_Dependents_06 - Step 01: Open 'Assigned_Dependents' form at sidebar");
		myInfoPage.openTabAtSideBarByName("Dependents");

		log.info("Assigned_Dependents_06 - Step 02: Click 'Add' button at 'Assigned Dependents' form");
		myInfoPage.clickToButtonByID(driver, "btnAddDependent");

		log.info("Assigned_Dependents_06 - Step 03: Enter new value to 'Name' textbox with value: " + Employee.Dependents.NAME_1);
		myInfoPage.enterToTextBoxByID(driver, "dependent_name", Employee.Dependents.NAME_1);

		log.info("Assigned_Dependents_06 - Step 04: Select new value to 'Relationship' dropdown: " + Employee.Dependents.RELATIONSHIP_1);
		myInfoPage.selectItemInDropdownByID(driver, "dependent_relationshipType", Employee.Dependents.RELATIONSHIP_1);

		log.info("Assigned_Dependents_06 - Step 05: Verify 'Please Specify' is not displayed");
		verifyFalse(myInfoPage.isFieldDisplayedByID(driver, "dependent_relationship"));

		log.info("Assigned_Dependents_06 - Step 06: Enter new value to 'Date of Birth' textbox with value: " + Employee.Dependents.DATE_OF_BIRTH_1);
		myInfoPage.enterToTextBoxByID(driver, "dependent_dateOfBirth", Employee.Dependents.DATE_OF_BIRTH_1);

		log.info("Assigned_Dependents_06 - Step 07: Click 'Save' button at 'Assigned Dependents' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveDependent");

		log.info("Assigned_Dependents_06 - Step 08: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Assigned_Dependents_06 - Step 09: Click 'Add' button at 'Assigned Dependents' form");
		myInfoPage.clickToButtonByID(driver, "btnAddDependent");

		log.info("Assigned_Dependents_06 - Step 10: Enter new value to 'Name' textbox with value: " + Employee.Dependents.NAME_2);
		myInfoPage.enterToTextBoxByID(driver, "dependent_name", Employee.Dependents.NAME_2);

		log.info("Assigned_Dependents_06 - Step 11: Select new value to 'Relationship' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "dependent_relationshipType", "Other");

		log.info("Assigned_Dependents_06 - Step 12: Verify 'Please Specify' is displayed");
		verifyTrue(myInfoPage.isFieldDisplayedByID(driver, "dependent_relationship"));

		log.info("Assigned_Dependents_06 - Step 13: Enter new value to 'Please Specify' textbox with value: " + Employee.Dependents.RELATIONSHIP_2);
		myInfoPage.enterToTextBoxByID(driver, "dependent_relationship", Employee.Dependents.RELATIONSHIP_2);

		log.info("Assigned_Dependents_06 - Step 14: Enter new value to 'Date of Birth' textbox with value: " + Employee.Dependents.DATE_OF_BIRTH_2);
		myInfoPage.enterToTextBoxByID(driver, "dependent_dateOfBirth", Employee.Dependents.DATE_OF_BIRTH_2);

		log.info("Assigned_Dependents_06 - Step 15: Click 'Save' button at 'Assigned Dependents' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveDependent");

		log.info("Assigned_Dependents_06 - Step 16: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		myInfoPage.sleepInSecond(2);

		log.info("Assigned_Dependents_06 - Step 17: Verify Dependents are displayed at 'Assigned Dependents' table");
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Name", "1"), Employee.Dependents.NAME_2);
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Relationship", "1"), Employee.Dependents.RELATIONSHIP_2);
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Date of Birth", "1"), Employee.Dependents.DATE_OF_BIRTH_2);
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Name", "2"), Employee.Dependents.NAME_1);
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Relationship", "2"), Employee.Dependents.RELATIONSHIP_1.toLowerCase());
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Date of Birth", "2"), Employee.Dependents.DATE_OF_BIRTH_1);
		myInfoPage.sleepInSecond(2);
	}

	@Test
	public void Employee_07_Edit_View_Job() {
		log.info("Edit_View_Job_07 - Step 01: Login with Admin role: " + Employee.Role.ADMIN_USERNAME + ", " + Employee.Role.ADMIN_PASSWORD);
		loginPage = myInfoPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, Employee.Role.ADMIN_USERNAME, Employee.Role.ADMIN_PASSWORD);

		log.info("Edit_View_Job_07 - Step 02: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Edit_View_Job_07 - Step 03: Enter valid info to 'Employee Name' textbox with value: " + Employee.PersonalDetails.FULLNAME);
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		employeeListPage.enterToTextBoxByID(driver, "empsearch_employee_name_empName", Employee.PersonalDetails.FULLNAME);
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Edit_View_Job_07 - Step 04: Click 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Edit_View_Job_07 - Step 05: Click to 'Employee ID' link");
		employeeListPage.clickToLinkByText(driver, employeeID);
		myInfoPage = PageGenerator.getMyInfoPage(driver);

		log.info("Edit_View_Job_07 - Step 06: Open 'Job' form at sidebar");
		myInfoPage.openTabAtSideBarByName("Job");

		log.info("Edit_View_Job_07 - Step 07: Click to 'Edit' button at 'Job' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Edit_View_Job_07 - Step 08: Select new value to 'Job Title' dropdown: " + Employee.Job.JOB_TITLE);
		myInfoPage.selectItemInDropdownByID(driver, "job_job_title", Employee.Job.JOB_TITLE);

		log.info("Edit_View_Job_07 - Step 09: Select new value to 'Employment Status' dropdown: " + Employee.Job.EMPLOYMENT_STATUS);
		myInfoPage.selectItemInDropdownByID(driver, "job_emp_status", Employee.Job.EMPLOYMENT_STATUS);

		log.info("Edit_View_Job_07 - Step 10: Select new value to 'Job Category' dropdown: " + Employee.Job.JOB_CATEGORY);
		myInfoPage.selectItemInDropdownByID(driver, "job_eeo_category", Employee.Job.JOB_CATEGORY);

		log.info("Edit_View_Job_07 - Step 11: Enter new value to 'Joined Date' dropdown: " + Employee.Job.JOINED_DATE);
		myInfoPage.enterToTextBoxByID(driver, "job_joined_date", Employee.Job.JOINED_DATE);

		log.info("Edit_View_Job_07 - Step 12: Select new value to 'Sub Unit' dropdown: " + Employee.Job.SUB_UNIT);
		myInfoPage.selectItemInDropdownByID(driver, "job_sub_unit", Employee.Job.SUB_UNIT);

		log.info("Edit_View_Job_07 - Step 13: Select new value to 'Location' dropdown: " + Employee.Job.LOCATION);
		myInfoPage.selectItemInDropdownByID(driver, "job_location", Employee.Job.LOCATION);

		log.info("Edit_View_Job_07 - Step 14: Select new value to 'Start Date' date picker: " + Employee.Job.START_DATE);
		myInfoPage.enterToTextBoxByID(driver, "job_contract_start_date", Employee.Job.START_DATE);

		log.info("Edit_View_Job_07 - Step 15: Enter new value to 'End Date' date picker: " + Employee.Job.END_DATE);
		myInfoPage.enterToTextBoxByID(driver, "job_contract_end_date", Employee.Job.END_DATE);

		log.info("Edit_View_Job_07 - Step 16: Upload file to 'Contract Details'");
		myInfoPage.uploadFileByID(driver, "file", contractFilePath);

		log.info("Edit_View_Job_07 - Step 17: Click 'Save' button at 'Job' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Edit_View_Job_07 - Step 18: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Updated"));

		log.info("Edit_View_Job_07 - Step 19: Login with Employee role: " + Employee.AddEmployee.USERNAME + ", " + Employee.AddEmployee.PASSWORD);
		loginPage = employeeListPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, Employee.AddEmployee.USERNAME, Employee.AddEmployee.PASSWORD);

		log.info("Edit_View_Job_07 - Step 20: Open 'My Info' page");
		dashboardPage.openMenuPage(driver, "My Info");
		myInfoPage = PageGenerator.getMyInfoPage(driver);

		log.info("Edit_View_Job_07 - Step 21: Open 'Job' form at sidebar");
		myInfoPage.openTabAtSideBarByName("Job");

		log.info("Edit_View_Job_07 - Step 22: Verify all fields at 'Job' form are read-only");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_job_title"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_emp_status"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_eeo_category"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_joined_date"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_sub_unit"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_location"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_contract_start_date"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_contract_end_date"));

		log.info("Edit_View_Job_07 - Step 23: Verify 'Job Title' dropdown is updated successfully");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "job_job_title"), Employee.Job.JOB_TITLE);

		log.info("Edit_View_Job_07 - Step 24: Verify 'Employment Status' dropdown is updated successfully");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "job_emp_status"), Employee.Job.EMPLOYMENT_STATUS);

		log.info("Edit_View_Job_07 - Step 25: Verify 'Job Category' dropdown is updated successfully");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "job_eeo_category"), Employee.Job.JOB_CATEGORY);

		log.info("Edit_View_Job_07 - Step 26: Verify 'Joined Date' is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "job_joined_date"), Employee.Job.JOINED_DATE);

		log.info("Edit_View_Job_07 - Step 27: Verify 'Sub Unit' dropdown is updated successfully");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "job_sub_unit"), Employee.Job.SUB_UNIT);

		log.info("Edit_View_Job_07 - Step 28: Verify 'Location' dropdown is updated successfully");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "job_location"), Employee.Job.LOCATION);

		log.info("Edit_View_Job_07 - Step 29: Verify 'Start Date' is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "job_contract_start_date"), Employee.Job.START_DATE);

		log.info("Edit_View_Job_07 - Step 30: Verify 'End Date' is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "job_contract_end_date"), Employee.Job.END_DATE);

		log.info("Edit_View_Job_07 - Step 31: Verify 'Contract Details' is updated successfully");
		verifyEquals(myInfoPage.getContractFileName(), "Labour Agreement.docx");
	}

	public void Employee_08_Edit_View_Salary() {
		log.info("Edit_View_Salary_08 - Step 01: Login with Admin role: " + Employee.Role.ADMIN_USERNAME + ", " + Employee.Role.ADMIN_PASSWORD);
		loginPage = myInfoPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, Employee.Role.ADMIN_USERNAME, Employee.Role.ADMIN_PASSWORD);
		
		log.info("Edit_View_Salary_08 - Step 02: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Edit_View_Salary_08 - Step 03: Enter valid info to 'Employee Name' textbox with value: " + Employee.PersonalDetails.FULLNAME);
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		employeeListPage.enterToTextBoxByID(driver, "empsearch_employee_name_empName", Employee.PersonalDetails.FULLNAME);
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Edit_View_Salary_08 - Step 04: Click 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Edit_View_Salary_08 - Step 05: Click to 'Employee ID' link");
		employeeListPage.clickToLinkByText(driver, employeeID);
		myInfoPage = PageGenerator.getMyInfoPage(driver);

		log.info("Edit_View_Salary_08 - Step 06: Open 'Salary' form at sidebar");
		myInfoPage.openTabAtSideBarByName("Salary");
		
		log.info("Edit_View_Salary_08 - Step 07: Click 'Add' button at 'Add Salary Component' form" );
		myInfoPage.clickToButtonByID(driver, "addSalary");
		
		log.info("Edit_View_Salary_08 - Step 08: Select new value to 'Pay Grade' dropdown: " + Employee.Salary.PAY_GRADE);
		myInfoPage.selectItemInDropdownByID(driver, "salary_sal_grd_code", Employee.Salary.PAY_GRADE);
		
		log.info("Edit_View_Salary_08 - Step 09: Enter valid info to 'Salary Component' textbox with value: " + Employee.Salary.SALARY_COMPONENT);
		myInfoPage.enterToTextBoxByID(driver, "salary_salary_component", Employee.Salary.SALARY_COMPONENT);
		
		log.info("Edit_View_Salary_08 - Step 10: Select new value to 'Pay Frequency' dropdown: " + Employee.Salary.PAY_FREQUENCY);
		myInfoPage.selectItemInDropdownByID(driver, "salary_payperiod_code", Employee.Salary.PAY_FREQUENCY);
		
		log.info("Edit_View_Salary_08 - Step 11: Enter valid info to 'Currency' textbox with value: " + Employee.Salary.CURRENCY);
		myInfoPage.selectItemInDropdownByID(driver, "salary_currency_id", Employee.Salary.CURRENCY);
		
		log.info("Edit_View_Salary_08 - Step 12: Enter valid info to 'Amount' textbox with value: " + Employee.Salary.AMOUNT);
		myInfoPage.enterToTextBoxByID(driver, "salary_basic_salary", Employee.Salary.AMOUNT);
		
		log.info("Edit_View_Salary_08 - Step 13: Enter valid info to 'Comment' textbox with value: " + Employee.Salary.COMMENTS);
		myInfoPage.enterToTextAreaByID(driver, "salary_comments", Employee.Salary.COMMENTS);
		
		log.info("Edit_View_Salary_08 - Step 14: Check to 'Add Direct Deposit Details' checkbox");
		myInfoPage.clickToCheckboxByLabel(driver, "Add Direct Deposit Details");
		
		log.info("Edit_View_Salary_08 - Step 15: Enter valid info to 'Account Number' textbox with value: " + Employee.Salary.ACCOUNT_NUMBER);
		myInfoPage.enterToTextBoxByID(driver, "directdeposit_account", Employee.Salary.ACCOUNT_NUMBER);
		
		log.info("Edit_View_Salary_08 - Step 16: Select new value to 'Account Type' dropdown: " + Employee.Salary.ACCOUNT_TYPE);
		myInfoPage.selectItemInDropdownByID(driver, "directdeposit_account_type", Employee.Salary.ACCOUNT_TYPE);
		
		log.info("Edit_View_Salary_08 - Step 17: Enter valid info to 'Routing Number' textbox with value:" + Employee.Salary.ROUTING_NUMBER);
		myInfoPage.enterToTextBoxByID(driver, "directdeposit_routing_num", Employee.Salary.ROUTING_NUMBER);
		
		log.info("Edit_View_Salary_08 - Step 18: Enter valid info to 'Amount' textbox with value:" + Employee.Salary.AMOUNT);
		myInfoPage.enterToTextBoxByID(driver, "directdeposit_amount", Employee.Salary.AMOUNT);
		
		log.info("Edit_View_Salary_08 - Step 19: Click 'Save' button");
		myInfoPage.clickToButtonByID(driver, "btnSalarySave");
		
		log.info("Edit_View_Salary_08 - Step 20: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Edit_View_Salary_08 - Step 21: Login with Employee role: " + Employee.AddEmployee.USERNAME + ", " + Employee.AddEmployee.PASSWORD);
		loginPage = myInfoPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, Employee.AddEmployee.USERNAME, Employee.AddEmployee.PASSWORD);
		
		log.info("Edit_View_Salary_08 - Step 22: Open 'My Info' page");
		dashboardPage.openMenuPage(driver, "My Info");
		myInfoPage = PageGenerator.getMyInfoPage(driver);

		log.info("Edit_View_Salary_08 - Step 23: Open 'Salary' form at sidebar");
		myInfoPage.openTabAtSideBarByName("Salary");
		
		log.info("Edit_View_Salary_08 - Step 24: Verify Salary components are displayed at 'Assigned Salary Components' table");
		
		log.info("Edit_View_Salary_08 - Step 25: Verify Direct Deposit Details are not displayed at 'Assigned Salary Components' table");
		
		log.info("Edit_View_Salary_08 - Step 26: Check to 'Add Direct Deposit Details' checkbox");
		
		log.info("Edit_View_Salary_08 - Step 27: Verify Direct Deposit Details are displayed at 'Assigned Salary Components' table");
	}
	
	@Test
	public void Employee_09_Edit_View_Tax() {

	}
	
	@Test
	public void Employee_10_Qualifications() {

	}
	
	@Test
	public void Employee_11_Search_Employee() {

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
}
