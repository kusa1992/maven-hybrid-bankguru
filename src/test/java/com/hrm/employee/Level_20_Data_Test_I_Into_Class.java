package com.hrm.employee;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.hrm.AddEmployeePO;
import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.EmployeeListPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.PageGenerator;
import utilities.DataUtil;
import pageObjects.hrm.MyInfoPO;

public class Level_20_Data_Test_I_Into_Class extends BaseTest {
	WebDriver driver;
	String adminUserName, adminPassword, empFirstName, empLastName, empUserName, empPassword, employeeID, statusValue;
	String empFullName, editEmpFirstName, editEmpLastName, editEmpGender, editEmpMaritalStatus, editEmpNationality, editEmpFullName;
	String addStreet1, addStreet2, city, state, zipPostalCode, country, homeTelephone, mobile, workTelephone, workEmail, otherEmail;
	String emgName1, emgRelationship1, emgHomeTelephone1, emgMobile1, emgWorkTelephone1, dateOfBirth1;
	String emgName2, emgRelationship2, emgHomeTelephone2, emgMobile2, emgWorkTelephone2, dateOfBirth2;
	String avatarFilePath = GlobalConstants.UPLOAD_FOLDER_PATH + "Avatar.png";
	String contractFilePath = GlobalConstants.UPLOAD_FOLDER_PATH + "Labour Agreement.docx";
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);
		fakeData = DataUtil.getData();
		
		statusValue = "Enabled";
		adminUserName = "Admin";
		adminPassword = "admin123";
		
		empFirstName = fakeData.getFirstName();
		empLastName = fakeData.getLastName();
		empFullName = empFirstName + " " + empLastName;
		empUserName = fakeData.getUserName();
		empPassword = fakeData.getPassword();
	
		editEmpFirstName = fakeData.getFirstName();
		editEmpLastName = fakeData.getLastName();
		editEmpFullName = editEmpFirstName + " " + editEmpLastName;
		editEmpGender = "Male";
		editEmpMaritalStatus = "Single";
		editEmpNationality = "German";

		addStreet1 = fakeData.getStreetPrefix(); 
		addStreet2 = fakeData.getStreetSuffix(); 
		city = fakeData.getCity(); 
		state = fakeData.getState();
		zipPostalCode = fakeData.getZipCode(); 
		country = "Japan";
		homeTelephone = fakeData.getPhoneNumber();
		mobile = fakeData.getPhoneNumber(); 
		workTelephone = fakeData.getPhoneNumber();
		workEmail = fakeData.getEmailAddress();
		otherEmail = fakeData.getEmailAddress();
		
		emgName1 = fakeData.getFirstName();
		emgRelationship1 = "Cousin";
		emgHomeTelephone1 = fakeData.getPhoneNumber(); 
		emgMobile1 = fakeData.getPhoneNumber(); 
		emgWorkTelephone1 = fakeData.getPhoneNumber(); 
		dateOfBirth1 = "1992-02-10";
		
		emgName2 = fakeData.getFirstName();
		emgRelationship2 = "Friend";
		emgHomeTelephone2 = fakeData.getPhoneNumber(); 
		emgMobile2 = fakeData.getPhoneNumber(); 
		emgWorkTelephone2 = fakeData.getPhoneNumber(); 
		dateOfBirth2 = "1970-10-14";
		
		log.info("Pre-Condition - Step 02: Login with Admin role");
		dashboardPage = loginPage.loginToSystem(driver, adminUserName, adminPassword);
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
		addEmployeePage.enterToTextBoxByID(driver, "firstName", empFirstName);
		
		log.info("Add_New_01 - Step 04: Enter valid info to 'Last Name' textbox");
		addEmployeePage.enterToTextBoxByID(driver, "lastName", empLastName);
		
		log.info("Add_New_01 - Step 05: Get value of 'Employee ID'");
		employeeID = addEmployeePage.getTextboxValueByID(driver, "employeeId");
		
		log.info("Add_New_01 - Step 06: Click to 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");
		
		log.info("Add_New_01 - Step 07: Enter valid info to 'User Name' textbox");
		addEmployeePage.enterToTextBoxByID(driver, "user_name", empUserName);
		
		log.info("Add_New_01 - Step 08: Enter valid info to 'Password' textbox");
		addEmployeePage.enterToTextBoxByID(driver, "user_password", empPassword);
		
		log.info("Add_New_01 - Step 09: Enter valid info to 'Confirm Password' textbox");
		addEmployeePage.enterToTextBoxByID(driver, "re_password", empPassword);
		
		log.info("Add_New_01 - Step 10: Select '" + statusValue + "' value in 'Status' dropdown");
		addEmployeePage.selectItemInDropdownByID(driver, "status", statusValue);
		
		log.info("Add_New_01 - Step 11: Click to 'Save' button");
		addEmployeePage.clickToButtonByID(driver, "btnSave");
		myInfoPage = PageGenerator.getMyInfoPage(driver);
		
		log.info("Add_New_01 - Step 12: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		
		log.info("Add_New_01 - Step 13: Enter valid info to 'Employee Name' textbox");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		employeeListPage.enterToTextBoxByID(driver, "empsearch_employee_name_empName", empFullName);
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		
		log.info("Add_New_01 - Step 14: Click 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		
		log.info("Add_New_01 - Step 15: Verify Employee Information is displayed at 'Result Table'");
		verifyEquals(employeeListPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"), employeeID);
		verifyEquals(employeeListPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1"), empFirstName);
		verifyEquals(employeeListPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), empLastName);	
	}

	@Test
	public void Employee_02_Upload_Avatar() {
		log.info("Upload_Avatar_02 - Step 01: Login with Employee role");
		loginPage = employeeListPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, empUserName, empPassword);
		
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
		myInfoPage.enterToTextBoxByID(driver, "personal_txtEmpFirstName", editEmpFirstName);
		
		log.info("Personal_Details_03 - Step 10: Enter new value to 'Last Name' textbox");
		myInfoPage.enterToTextBoxByID(driver, "personal_txtEmpLastName", editEmpLastName);
		
		log.info("Personal_Details_03 - Step 11: Select new value to 'Gender' radio button");
		myInfoPage.clickToRadioByLabel(driver, editEmpGender);
		
		log.info("Personal_Details_03 - Step 13: Select new value to 'Marital Status' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "personal_cmbMarital", editEmpMaritalStatus);
		
		log.info("Personal_Details_03 - Step 12: Select new value to 'Nationality' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "personal_cmbNation", editEmpNationality);
		
		log.info("Personal_Details_03 - Step 14: Click to 'Save' button at 'Personal Dertails' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");
		
		log.info("Personal_Details_03 - Step 15: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Personal_Details_03 - Step 16: Verify 'First Name' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpFirstName"), editEmpFirstName);
		
		log.info("Personal_Details_03 - Step 17: Verify 'Last Name' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpLastName"), editEmpLastName);
		
		log.info("Personal_Details_03 - Step 18: Verify 'Gender' textbox is updated successfully");
		verifyTrue(myInfoPage.isRadioSelectedByLabel(driver, editEmpGender));
		
		log.info("Personal_Details_03 - Step 19: Verify 'Marital Status' textbox is updated successfully");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "personal_cmbMarital"), editEmpMaritalStatus);
		
		log.info("Personal_Details_03 - Step 20: Verify 'Nationality' textbox is updated successfully");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "personal_cmbNation"), editEmpNationality);
		
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
		
		log.info("Contact_Details_04 - Step 04: Enter new value to 'Address Street 1' textbox");
		myInfoPage.enterToTextBoxByID(driver, "contact_street1", addStreet1);
		
		log.info("Contact_Details_04 - Step 05: Enter new value to 'Address Street 2' textbox");
		myInfoPage.enterToTextBoxByID(driver, "contact_street2", addStreet2);
		
		log.info("Contact_Details_04 - Step 06: Enter new value to 'City' textbox");
		myInfoPage.enterToTextBoxByID(driver, "contact_city", city);
		
		log.info("Contact_Details_04 - Step 07: Enter new value to 'State/Province' textbox");
		myInfoPage.enterToTextBoxByID(driver, "contact_province", state);
		
		log.info("Contact_Details_04 - Step 09: Enter new value to 'Zip/Postal Code' textbox");
		myInfoPage.enterToTextBoxByID(driver, "contact_emp_zipcode", zipPostalCode);
		
		log.info("Contact_Details_04 - Step 10: Select new value to 'Country' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "contact_country", country);
		
		log.info("Contact_Details_04 - Step 11: Enter new value to 'Home Telephone' textbox");
		myInfoPage.enterToTextBoxByID(driver, "contact_emp_hm_telephone", homeTelephone);
		
		log.info("Contact_Details_04 - Step 12: Enter new value to 'Mobile' textbox");
		myInfoPage.enterToTextBoxByID(driver, "contact_emp_mobile", mobile);
		
		log.info("Contact_Details_04 - Step 13: Enter new value to 'Work Telephone' textbox");
		myInfoPage.enterToTextBoxByID(driver, "contact_emp_work_telephone", workTelephone);
		
		log.info("Contact_Details_04 - Step 14: Enter new value to 'Work Email' textbox");
		myInfoPage.enterToTextBoxByID(driver, "contact_emp_work_email", workEmail);
		
		log.info("Contact_Details_04 - Step 15: Enter new value to 'Other Email' textbox");
		myInfoPage.enterToTextBoxByID(driver, "contact_emp_oth_email", otherEmail);
		
		log.info("Contact_Details_04 - Step 16: Click to 'Save' button at 'Contact Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");
		
		log.info("Contact_Details_04 - Step 17: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Contact_Details_04 - Step 18: Verify 'Address Street 1' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_street1"), addStreet1);
		
		log.info("Contact_Details_04 - Step 19: Verify 'Address Street 1' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_street2"), addStreet2);
		
		log.info("Contact_Details_04 - Step 20: Verify 'City' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_city"), city);
		
		log.info("Contact_Details_04 - Step 21: Verify 'State/Province' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_province"), state);
		
		log.info("Contact_Details_04 - Step 22: Verify 'Zip/Postal Code' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_zipcode"), zipPostalCode);
		
		log.info("Contact_Details_04 - Step 22: Verify 'Country' dropdown is updated successfully");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "contact_country"), country);
		
		log.info("Contact_Details_04 - Step 23: Verify 'Home Telephone' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_hm_telephone"), homeTelephone);
		
		log.info("Contact_Details_04 - Step 24: Verify 'Mobile' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_mobile"), mobile);
		
		log.info("Contact_Details_04 - Step 25: Verify 'Work Telephone' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_work_telephone"), workTelephone);
		
		log.info("Contact_Details_04 - Step 26: Verify 'Work Email' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_work_email"), workEmail);
		
		log.info("Contact_Details_04 - Step 27: Verify 'Other Email' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_oth_email"), otherEmail);
	}
	
	@Test
	public void Employee_05_Emergency_Contacts() {
		log.info("Emergency_Contacts_05 - Step 01: Open 'Emergency_Contacts' form at sidebar");
		myInfoPage.openTabAtSideBarByName("Emergency Contacts");
		
		log.info("Emergency_Contacts_05 - Step 02: Click 'Add' button at 'Assigned Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnAddContact");
		
		log.info("Emergency_Contacts_05 - Step 03: Enter new value to 'Name' textbox");
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_name", emgName1);
		
		log.info("Emergency_Contacts_05 - Step 04: Enter new value to 'Relationship' textbox");
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_relationship", emgRelationship1);
		
		log.info("Emergency_Contacts_05 - Step 05: Enter new value to 'Home Telephone' textbox");
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_homePhone", emgHomeTelephone1);
		
		log.info("Emergency_Contacts_05 - Step 06: Enter new value to 'Mobile' textbox");
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_mobilePhone", emgMobile1);
		
		log.info("Emergency_Contacts_05 - Step 07: Enter new value to 'Work Telephone' textbox");
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_workPhone", emgWorkTelephone1);
		
		log.info("Emergency_Contacts_05 - Step 08: Click 'Save' button at 'Assigned Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveEContact");
		
		log.info("Emergency_Contacts_05 - Step 09: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Emergency_Contacts_05 - Step 10: Click 'Add' button at 'Assigned Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnAddContact");
		
		log.info("Emergency_Contacts_05 - Step 03: Enter new value to 'Name' textbox");
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_name", emgName2);
		
		log.info("Emergency_Contacts_05 - Step 04: Enter new value to 'Relationship' textbox");
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_relationship", emgRelationship2);
		
		log.info("Emergency_Contacts_05 - Step 05: Enter new value to 'Home Telephone' textbox");
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_homePhone", emgHomeTelephone2);
		
		log.info("Emergency_Contacts_05 - Step 06: Enter new value to 'Mobile' textbox");
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_mobilePhone", emgMobile2);
		
		log.info("Emergency_Contacts_05 - Step 07: Enter new value to 'Work Telephone' textbox");
		myInfoPage.enterToTextBoxByID(driver, "emgcontacts_workPhone", emgWorkTelephone2);
		
		log.info("Emergency_Contacts_05 - Step 08: Click 'Save' button at 'Assigned Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveEContact");
		
		log.info("Emergency_Contacts_05 - Step 09: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Emergency_Contacts_05 - Step 10: Verify Emergency Contacts are displayed at 'Emergency Contacts' table");
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Name", "1"), emgName2);
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Relationship", "1"), emgRelationship2);
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Name", "2"), emgName1);
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Home Telephone", "2"), emgHomeTelephone1);
	}
	
	@Test
	public void Employee_06_Assigned_Dependents() {
		log.info("Assigned_Dependents_06 - Step 01: Open 'Assigned_Dependents' form at sidebar");
		myInfoPage.openTabAtSideBarByName("Dependents");
		
		log.info("Assigned_Dependents_06 - Step 02: Click 'Add' button at 'Assigned Dependents' form");
		myInfoPage.clickToButtonByID(driver, "btnAddDependent");
		
		log.info("Assigned_Dependents_06 - Step 03: Enter new value to 'Name' textbox");
		myInfoPage.enterToTextBoxByID(driver, "dependent_name", emgName1);
		
		log.info("Assigned_Dependents_06 - Step 04: Select new value to 'Relationship' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "dependent_relationshipType", "Child");
		
		log.info("Assigned_Dependents_06 - Step 05: Verify 'Please Specify' is not displayed");
		verifyFalse(myInfoPage.isFieldDisplayedByID(driver, "dependent_relationship"));
		
		log.info("Assigned_Dependents_06 - Step 06: Enter new value to 'Date of Birth' textbox");
		myInfoPage.enterToTextBoxByID(driver, "dependent_dateOfBirth", dateOfBirth1);
		
		log.info("Assigned_Dependents_06 - Step 07: Click 'Save' button at 'Assigned Dependents' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveDependent");
		
		log.info("Assigned_Dependents_06 - Step 08: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Assigned_Dependents_06 - Step 09: Click 'Add' button at 'Assigned Dependents' form");
		myInfoPage.clickToButtonByID(driver, "btnAddDependent");
		
		log.info("Assigned_Dependents_06 - Step 10: Enter new value to 'Name' textbox");
		myInfoPage.enterToTextBoxByID(driver, "dependent_name", emgName2);
		
		log.info("Assigned_Dependents_06 - Step 11: Select new value to 'Relationship' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "dependent_relationshipType", "Other");
		
		log.info("Assigned_Dependents_06 - Step 12: Verify 'Please Specify' is displayed");
		verifyTrue(myInfoPage.isFieldDisplayedByID(driver, "dependent_relationship"));
		
		log.info("Assigned_Dependents_06 - Step 13: Enter new value to 'Please Specify' textbox");
		myInfoPage.enterToTextBoxByID(driver, "dependent_relationship", emgRelationship2);
		
		log.info("Assigned_Dependents_06 - Step 14: Enter new value to 'Date of Birth' textbox");
		myInfoPage.enterToTextBoxByID(driver, "dependent_dateOfBirth", dateOfBirth2);
		
		log.info("Assigned_Dependents_06 - Step 15: Click 'Save' button at 'Assigned Dependents' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveDependent");
		
		log.info("Assigned_Dependents_06 - Step 16: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Assigned_Dependents_06 - Step 17: Verify Dependents are displayed at 'Assigned Dependents' table");
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Name", "1"), emgName2);
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Relationship", "1"), emgRelationship2);
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Date of Birth", "1"), dateOfBirth2);
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Name", "2"), emgName1);
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Relationship", "2"), "child");
		verifyEquals(myInfoPage.getValueDisplayedInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Date of Birth", "2"), dateOfBirth1);
	}
	
	
	public void Employee_07_Edit_View_Job() {
		log.info("Edit_View_Job_07 - Step 01: Login with Admin role");
		loginPage = myInfoPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, adminUserName, adminPassword);
		
		log.info("Edit_View_Job_07 - Step 02: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		
		log.info("Edit_View_Job_07 - Step 03: Enter valid info to 'Employee Name' textbox");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		employeeListPage.enterToTextBoxByID(driver, "empsearch_employee_name_empName", editEmpFullName);
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		
		log.info("Edit_View_Job_07 - Step 04: Click 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		
		log.info("Edit_View_Job_07 - Step 05: Click to 'Employee ID' link");
		
		log.info("Edit_View_Job_07 - Step 06: Open 'Job' form at sidebar");
		myInfoPage.openTabAtSideBarByName("Job");
		
		log.info("Edit_View_Job_07 - Step 07: Click to 'Edit' button at 'Job' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");
		
		log.info("Edit_View_Job_07 - Step 08: Select new value to 'Job Title' dropdown");
		
		log.info("Edit_View_Job_07 - Step 09: Select new value to 'Employment Status' dropdown");
		
		log.info("Edit_View_Job_07 - Step 10: Select new value to 'Job Category' dropdown");
		
		log.info("Edit_View_Job_07 - Step 11: Enter new value to 'Joined Date' dropdown");
		
		log.info("Edit_View_Job_07 - Step 12: Select new value to 'Sub Unit' dropdown");
		
		log.info("Edit_View_Job_07 - Step 13: Select new value to 'Start Date' textbox");
		
		log.info("Edit_View_Job_07 - Step 14: Enter new value to 'End Date' textbox");
		
		log.info("Edit_View_Job_07 - Step 15: Upload file to 'Contract Details'");
		
		log.info("Edit_View_Job_07 - Step 16: Click 'Save' button at 'Job' form");
		
		log.info("Edit_View_Job_07 - Step 17: Verify Success message is displayed");
		
		log.info("Edit_View_Job_07 - Step 18: Login with Employee role");
		loginPage = employeeListPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, empUserName, empPassword);
		
		log.info("Edit_View_Job_07 - Step 19: Open 'My Info' page");
		dashboardPage.openMenuPage(driver, "My Info");
		myInfoPage = PageGenerator.getMyInfoPage(driver);
		
		log.info("Edit_View_Job_07 - Step 20: Open 'Job' form at sidebar");
		myInfoPage.openTabAtSideBarByName("Job");
		
		log.info("Edit_View_Job_07 - Step 21: Verify all fields at 'Job' form are read-only");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_job_title"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_emp_status"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_eeo_category"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_joined_date"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_sub_unit"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_location"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_contract_start_date"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_contract_end_date"));

		log.info("Edit_View_Job_07 - Step 22: Verify 'Job Title' dropdown is updated successfully");
		
		log.info("Edit_View_Job_07 - Step 23: Verify 'Employment Status' dropdown is updated successfully");
		
		log.info("Edit_View_Job_07 - Step 24: Verify 'Job Category' dropdown is updated successfully");
		
		log.info("Edit_View_Job_07 - Step 25: Verify 'Joined Date' dropdown is updated successfully");
		
		log.info("Edit_View_Job_07 - Step 26: Verify 'Sub Unit' dropdown is updated successfully");
		
		log.info("Edit_View_Job_07 - Step 27: Verify 'Start Date' dropdown is updated successfully");
		
		log.info("Edit_View_Job_07 - Step 28: Verify 'End Date' dropdown is updated successfully");

		log.info("Edit_View_Job_07 - Step 28: Verify 'Contract Details' is updated successfully");
	}
	
	@Test
	public void Employee_08_Edit_View_Salary() {

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
	DataUtil fakeData;
}
