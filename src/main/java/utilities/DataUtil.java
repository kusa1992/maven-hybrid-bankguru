package utilities;

import com.github.javafaker.Faker;

public class DataUtil {
	private Faker faker;

	public static DataUtil getData() {
		return new DataUtil();
	}

	public DataUtil() {
		faker = new Faker();
	}

	public String getFirstName() {
		return faker.name().firstName();
	}

	public String getLastName() {
		return faker.name().lastName();
	}

	public String getFullName() {
		return faker.name().fullName();
	}

	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}

	public String getUserName() {
		return faker.name().username();
	}

	public String getPassword() {
		return faker.internet().password();
	}

	public String getStreetPrefix() {
		return faker.address().streetName();
	}

	public String getStreetSuffix() {
		return faker.address().streetName();
	}

	public String getCity() {
		return faker.address().city();
	}

	public String getState() {
		return faker.address().state();
	}
	
	public String getZipCode() {
		return faker.address().zipCode();
	}
	
	public String getCountry() {
		return faker.address().country();
	}
	
	public String getPhoneNumber() {
		return faker.finance().creditCard();
	}
	
	public String getAccountNumber() {
		return faker.finance().creditCard();
	}

}
