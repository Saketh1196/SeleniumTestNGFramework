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

public class Register extends Base{
	
	public Register() {
		super();
	}
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver = InitializeBrowserURL(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccount();
		homepage.selectRegisterOption();;
	}
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyRegisteringandAccountWithMandatoryFields() {
		
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("FirstName"));
		registerpage.enterLastName(dataProp.getProperty("LastName"));
		registerpage.enterEmailAddress(Utilities.generateEmailwithTimeStamp());
		registerpage.enterTelephone(dataProp.getProperty("TelephoneNumber"));
		registerpage.enterPassword(prop.getProperty("ValidPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("ValidPassword"));
		registerpage.enterAgreeOption();
		registerpage.enterContinueOption();
//		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("FirstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("LastName"));
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailwithTimeStamp());
//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("TelephoneNumber"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("ValidPassword"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("ValidPassword"));
//		driver.findElement(By.name("agree")).click();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		AccountSuccessPage accountsuccesspage = new AccountSuccessPage(driver);
		String ActualSuccessHeading = accountsuccesspage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(ActualSuccessHeading,dataProp.getProperty("AccountSuccesfullyCreated"), "Account Success page is not displayed");
	}
	
	@Test(priority=2)
	public void verifyRegisteringandAccountByProvidingAllFields() {
		
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("FirstName"));
		registerpage.enterLastName(dataProp.getProperty("LastName"));
		registerpage.enterEmailAddress(Utilities.generateEmailwithTimeStamp());
		registerpage.enterTelephone(dataProp.getProperty("TelephoneNumber"));
		registerpage.enterPassword(prop.getProperty("ValidPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("ValidPassword"));
		registerpage.enterSubscribeOption();
		registerpage.enterAgreeOption();
		registerpage.enterContinueOption();
//		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("FirstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("LastName"));
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailwithTimeStamp());
//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("TelephoneNumber"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("ValidPassword"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("ValidPassword"));
//		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
//		driver.findElement(By.name("agree")).click();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
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
//		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("FirstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("LastName"));
//		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("ValidEmail"));
//		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("TelephoneNumber"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("ValidPassword"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("ValidPassword"));
//		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
//		driver.findElement(By.name("agree")).click();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String ActualWarning = registerpage.retrieveDuplicateEmailAddressWarning();
		Assert.assertTrue(ActualWarning.contains(dataProp.getProperty("DuplicateEmailWarning")), "Warning message is not duplicated");
	}
	
	@Test(priority=4)
	public void verifyRegisteringAccountwithoutFillingAnyDetails() {
		
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterContinueOption();
//		driver.findElement(By.id("input-firstname")).sendKeys("Test");
//		driver.findElement(By.id("input-lastname")).sendKeys("User");
//		driver.findElement(By.id("input-email")).sendKeys("abcdef06@gmail.com");
//		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
//		driver.findElement(By.id("input-password")).sendKeys("12345");
//		driver.findElement(By.id("input-confirm")).sendKeys("12345");
//		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
//		driver.findElement(By.name("agree")).click();
//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
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
