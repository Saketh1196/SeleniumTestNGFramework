package com.atuomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailAddressField;

	@FindBy(id = "input-telephone")
	private WebElement TelephoneField;

	@FindBy(id = "input-password")
	private WebElement PasswordField;

	@FindBy(id = "input-confirm")
	private WebElement ConfirmField;

	@FindBy(name = "agree")
	private WebElement AgreeField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement ContinueField;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement SubscribeField;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement PrivacyPolicyWarning;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailAddressWarning;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;

	public RegisterPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String FirstName) {
		firstNameField.sendKeys(FirstName);
	}

	public void enterLastName(String LastName) {
		lastNameField.sendKeys(LastName);
	}

	public void enterEmailAddress(String EmailAddress) {
		emailAddressField.sendKeys(EmailAddress);
	}

	public void enterTelephone(String Telephone) {
		TelephoneField.sendKeys(Telephone);
	}

	public void enterPassword(String Password) {
		PasswordField.sendKeys(Password);
	}

	public void enterConfirmPassword(String ConfirmPassword) {
		ConfirmField.sendKeys(ConfirmPassword);
	}

	public void enterAgreeOption() {
		AgreeField.click();
	}

	public void enterContinueOption() {
		ContinueField.click();
	}

	public void enterSubscribeOption() {
		SubscribeField.click();
	}

	public String retrieveDuplicateEmailAddressWarning() {
		String duplicateEmailWarningText = duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningText;
	}

	public String retrievePrivacyPolicyWarning() {
		String privacyPolicyWarning = PrivacyPolicyWarning.getText();
		return privacyPolicyWarning;
	}

	public String retrieveFirstNameWarning() {
		String FirstNameWarning = firstNameWarning.getText();
		return FirstNameWarning;
	}

	public String retrieveLastNameWarning() {
		String LastNameWarning = lastNameWarning.getText();
		return LastNameWarning;
	}

	public String retrieveEmailWarning() {
		String EmailAddressWarning = emailAddressWarning.getText();
		return EmailAddressWarning;
	}

	public String retrieveTelephoneWarning() {
		String TelephoneNumberWarning = telephoneWarning.getText();
		return TelephoneNumberWarning;
	}

	public String retrievePasswordWarning() {
		String PasswordWarning = passwordWarning.getText();
		return PasswordWarning;
	}
}
