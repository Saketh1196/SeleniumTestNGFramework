package com.automation.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.atuomation.pages.AccountSuccessPage;
import com.atuomation.pages.HomePage;
import com.atuomation.pages.RegisterPage;
import com.automation.base.Base;
import com.automation.utils.Utilities;

public class RegisterTest extends Base{
	
	public RegisterTest() {
		super();
	}
	WebDriver driver;
	RegisterPage registerpage; 
	
	@BeforeMethod
	public void setup() {
		
		driver = InitializeBrowserURL(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccount();
		homepage.selectRegisterOption();
		registerpage= new RegisterPage(driver);
	}
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyRegisteringandAccountWithMandatoryFields() {
		
		
		registerpage.enterFirstName(dataProp.getProperty("FirstName"));
		registerpage.enterLastName(dataProp.getProperty("LastName"));
		registerpage.enterEmailAddress(Utilities.generateEmailwithTimeStamp());
		registerpage.enterTelephone(dataProp.getProperty("TelephoneNumber"));
		registerpage.enterPassword(prop.getProperty("ValidPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("ValidPassword"));
		registerpage.enterAgreeOption();
		registerpage.enterContinueOption();
		
		AccountSuccessPage accountsuccesspage = new AccountSuccessPage(driver);
		String ActualSuccessHeading = accountsuccesspage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(ActualSuccessHeading,dataProp.getProperty("AccountSuccesfullyCreated"), "Account Success page is not displayed");
	}
	
	@Test(priority=2)
	public void verifyRegisteringandAccountByProvidingAllFields() {
		
		
		registerpage.enterFirstName(dataProp.getProperty("FirstName"));
		registerpage.enterLastName(dataProp.getProperty("LastName"));
		registerpage.enterEmailAddress(Utilities.generateEmailwithTimeStamp());
		registerpage.enterTelephone(dataProp.getProperty("TelephoneNumber"));
		registerpage.enterPassword(prop.getProperty("ValidPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("ValidPassword"));
		registerpage.enterSubscribeOption();
		registerpage.enterAgreeOption();
		registerpage.enterContinueOption();
		
		AccountSuccessPage accountsuccesspage = new AccountSuccessPage(driver);
		String ActualSuccessHeading = accountsuccesspage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(ActualSuccessHeading,dataProp.getProperty("AccountSuccesfullyCreated"), "Account Success page is not displayed");
	}
	
	@Test(priority=3)
	public void verifyRegisteringAccountwithExistingEmail() {
		
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("FirstName"));
		registerpage.enterLastName(dataProp.getProperty("LastName"));
		registerpage.enterEmailAddress(prop.getProperty("ValidEmail"));
		registerpage.enterTelephone(dataProp.getProperty("TelephoneNumber"));
		registerpage.enterPassword(prop.getProperty("ValidPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("ValidPassword"));
		registerpage.enterSubscribeOption();
		registerpage.enterAgreeOption();
		registerpage.enterContinueOption();
		String ActualWarning = registerpage.retrieveDuplicateEmailAddressWarning();
		Assert.assertTrue(ActualWarning.contains(dataProp.getProperty("DuplicateEmailWarning")), "Warning message is not duplicated");
	}
	
	@Test(priority=4)
	public void verifyRegisteringAccountwithoutFillingAnyDetails() {
		
		
		registerpage.enterContinueOption();
		
		String actualPrivacypolicy = registerpage.retrievePrivacyPolicyWarning();
		Assert.assertTrue(actualPrivacypolicy.contains(dataProp.getProperty("PrivacyPolicyWarning")),"Privacy Policy message is not displayed");
		
		String  FirstNameWarning = registerpage.retrieveFirstNameWarning();
		Assert.assertTrue(FirstNameWarning.contains(dataProp.getProperty("FirstNameWarning")),"First name not displayed");
		
		String  LastNameWarning = registerpage.retrieveLastNameWarning();
		Assert.assertTrue(LastNameWarning.contains(dataProp.getProperty("LastNameWarning")),"last name not displayed");
		
		String  EmailAddressWarning = registerpage.retrieveEmailWarning();
		Assert.assertTrue(EmailAddressWarning.contains(dataProp.getProperty("EmailWarning")),"Email address warning not displayed");
		
		String  TelephoneWarning = registerpage.retrieveTelephoneWarning();
		Assert.assertTrue(TelephoneWarning.contains(dataProp.getProperty("TelephoneWarning")),"Telephone warning not displayed");
		
		String  PasswordWarning = registerpage.retrievePasswordWarning();
		Assert.assertTrue(PasswordWarning.contains(dataProp.getProperty("PasswordWarning")),"Password warning not displayed");
	}
	

}
