package pageObjects.sauceLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.sauceLab.InventoryPageUI;

public class InventoryPageObject extends BasePage {
	WebDriver driver;

	public InventoryPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInSortDropDown(String itemText) {
		waitForElementClickable(driver, InventoryPageUI.SORT_DROPDOWN);
		selectDropdownByText(driver, InventoryPageUI.SORT_DROPDOWN, itemText);
	}

	public boolean isProductNameSortAscending() {
		List<WebElement> productNameElements = getElements(driver, InventoryPageUI.PRODUCT_NAME_TEXT);

		List<String> productNameText = new ArrayList<String>();

		for (WebElement productNameElement : productNameElements) {
			productNameText.add(productNameElement.getText());
		}

		System.out.println("Before sort arc:-------------");
		for (String product : productNameText) {
			System.out.println(product);
		}

		List<String> productNameTextClone = new ArrayList<String>();
		for (String product : productNameText) {
			productNameTextClone.add(product);
		}
		Collections.sort(productNameTextClone);

		System.out.println("After sort arc:-------------");
		for (String product : productNameTextClone) {
			System.out.println(product);
		}

		return productNameText.equals(productNameTextClone);
	}

	public boolean isProductNameSortDescending() {
		List<WebElement> productNameElements = getElements(driver, InventoryPageUI.PRODUCT_NAME_TEXT);

		List<String> productNameText = new ArrayList<String>();

		for (WebElement productNameElement : productNameElements) {
			productNameText.add(productNameElement.getText());
		}

		System.out.println("Before sort desc:-------------");
		for (String product : productNameText) {
			System.out.println(product);
		}

		List<String> productNameTextClone = new ArrayList<String>();
		for (String product : productNameText) {
			productNameTextClone.add(product);
		}
		Collections.sort(productNameTextClone);
		Collections.reverse(productNameTextClone);

		System.out.println("After sort desc:-------------");
		for (String product : productNameTextClone) {
			System.out.println(product);
		}

		return productNameText.equals(productNameTextClone);
	}

	public boolean isProductNamePriceAscending() {
		List<WebElement> productPriceElements = getElements(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);

		List<Float> productPriceText = new ArrayList<Float>();

		for (WebElement productPriceElement : productPriceElements) {
			productPriceText.add(Float.parseFloat(productPriceElement.getText().replace("$", "")));
		}

		System.out.println("Before sort arc:-------------");
		for (Float productPrice : productPriceText) {
			System.out.println(productPrice);
		}

		List<Float> productPriceTextClone = new ArrayList<Float>();
		for (Float product : productPriceText) {
			productPriceTextClone.add(product);
		}
		Collections.sort(productPriceTextClone);

		System.out.println("After sort arc:-------------");
		for (Float productPrice : productPriceTextClone) {
			System.out.println(productPrice);
		}

		return productPriceText.equals(productPriceTextClone);
	}

	public boolean isProductNamePriceDescending() {
		List<WebElement> productPriceElements = getElements(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);

		List<Float> productPriceText = new ArrayList<Float>();

		for (WebElement productPriceElement : productPriceElements) {
			productPriceText.add(Float.parseFloat(productPriceElement.getText().replace("$", "")));
		}

		System.out.println("Before sort desc:-------------");
		for (Float productPrice : productPriceText) {
			System.out.println(productPrice);
		}

		List<Float> productPriceTextClone = new ArrayList<Float>();
		for (Float product : productPriceText) {
			productPriceTextClone.add(product);
		}
		Collections.sort(productPriceTextClone);
		Collections.reverse(productPriceTextClone);

		System.out.println("After sort desc:-------------");
		for (Float productPrice : productPriceTextClone) {
			System.out.println(productPrice);
		}

		return productPriceText.equals(productPriceTextClone);
	}

}
