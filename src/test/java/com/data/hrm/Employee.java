package com.data.hrm;

import utilities.DataUtil;

public class Employee {
	static DataUtil fakeData = DataUtil.getData();

	public static Employee getEmployee() {
		return new Employee();
	}

	public class Role {
		public static final String ADMIN_USERNAME = "Admin";
		public static final String ADMIN_PASSWORD = "admin123";
	}

	public static class AddEmployee {
		public static final String FIRSTNAME = fakeData.getFirstName();
		public static final String LASTNAME = fakeData.getLastName();
		public static final String FULLNAME = FIRSTNAME + " " + LASTNAME;
		public static final String USERNAME = fakeData.getUserName();
		public static final String PASSWORD = fakeData.getPassword();
		public static final String STATUS_VALUE = "Enabled";

	}

	public static class PersonalDetails {
		public static final String FIRSTNAME = fakeData.getFirstName();
		public static final String LASTNAME = fakeData.getLastName();
		public static final String FULLNAME = FIRSTNAME + " " + LASTNAME;
		public static final String GENDER = "Male";
		public static final String MARITAL_STATUS = "Single";
		public static final String NATIONALITY = "Japanese";
	}

	public static class ContactDetails {
		public static final String ADDRESS_1 = fakeData.getStreetPrefix();
		public static final String ADDRESS_2 = fakeData.getStreetSuffix();
		public static final String CITY = fakeData.getCity();
		public static final String STATE = fakeData.getState();
		public static final String ZIPCODE = fakeData.getZipCode();
		public static final String COUNTRY = fakeData.getCountry();
		public static final String HOME_TELEPHONE = fakeData.getPhoneNumber();
		public static final String MOBILE = fakeData.getPhoneNumber();
		public static final String WORK_TELEPHONE = fakeData.getPhoneNumber();
		public static final String WORK_EMAIL = fakeData.getEmailAddress();
		public static final String OTHER_EMAIL = fakeData.getEmailAddress();
	}

	public static class EmergencyContacts {
		public static final String NAME_1 = fakeData.getFullName();
		public static final String RELATIONSHIP_1 = "Cousin";
		public static final String HOME_TELEPHONE_1 = fakeData.getPhoneNumber();
		public static final String MOBILE_1 = fakeData.getPhoneNumber();
		public static final String WORK_TELEPHONE_1 = fakeData.getPhoneNumber();
		
		public static final String NAME_2 = fakeData.getFullName();
		public static final String RELATIONSHIP_2 = "Sister";
		public static final String HOME_TELEPHONE_2 = fakeData.getPhoneNumber();
		public static final String MOBILE_2 = fakeData.getPhoneNumber();
		public static final String WORK_TELEPHONE_2 = fakeData.getPhoneNumber();
	}
	
	public static class Dependents {
		public static final String NAME_1 = fakeData.getFullName();
		public static final String RELATIONSHIP_1 = "Child";
		public static final String DATE_OF_BIRTH_1 = "2000-04-18";
		
		public static final String NAME_2 = fakeData.getFullName();
		public static final String RELATIONSHIP_2 = "Wife";
		public static final String DATE_OF_BIRTH_2 = "1985-10-26";
	}
	

	public static class Job {
		public static final String JOB_TITLE = "QA Engineer";
		public static final String EMPLOYMENT_STATUS = "Full-Time Probation";
		public static final String JOB_CATEGORY = "Professionals";
		public static final String JOINED_DATE = "2021-10-07";
		public static final String SUB_UNIT = "Engineering";
		public static final String LOCATION = "HQ - CA, USA";
		public static final String START_DATE = "2021-10-07";
		public static final String END_DATE = "2021-12-07";
	}
	
	public static class Salary {
		public static final String PAY_GRADE = "Grade 5";
		public static final String SALARY_COMPONENT = "Basic Salary";
		public static final String PAY_FREQUENCY = "Monthly";
		public static final String CURRENCY = "United States Dollar";
		public static final String AMOUNT = "11000";
		public static final String COMMENTS = "Salary for December 2021";
		public static final String ACCOUNT_NUMBER = fakeData.getAccountNumber();
		public static final String ACCOUNT_TYPE = "Savings";
		public static final String ROUTING_NUMBER = "12245255";
	}
}
