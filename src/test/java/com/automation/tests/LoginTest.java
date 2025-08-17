package com.automation.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.atuomation.pages.AccountPage;
import com.atuomation.pages.HomePage;
import com.atuomation.pages.LoginPage;
import com.automation.base.Base;
import com.automation.utils.Utilities;

public class LoginTest extends Base{
	
	public LoginTest() {
		super();
	}
	
	WebDriver driver;
	LoginPage loginpage;
	
	@BeforeMethod
	public void setup() {
		
		driver = InitializeBrowserURL(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccount();
		homepage.selectLoginOption();
		loginpage = new LoginPage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyLoginWithValidCredentials() {
		
		loginpage.emailAddress(prop.getProperty("ValidEmail"));
		loginpage.enterPassword(prop.getProperty("ValidPassword"));
		loginpage.clickOnLoginButton();
		
		AccountPage accountpage = new AccountPage(driver);
		Assert.assertTrue(accountpage.getDisplayStatusOfAccountInformation(), "Edit your account information is not displayed");	
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		
		loginpage.emailAddress(Utilities.generateEmailwithTimeStamp());
		loginpage.enterPassword(dataProp.getProperty("InvalidPassword"));
		loginpage.clickOnLoginButton();
		
		String warningMessage =loginpage.retrieveEmailPasswordNotWarningMessageText();
		String expectedwarningMessage = dataProp.getProperty("emailPasswordNotMatching");
		Assert.assertTrue(warningMessage.contains(expectedwarningMessage), "Expected Warning message is not displayed");

	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailandValidPassword() {
		
		loginpage.emailAddress(Utilities.generateEmailwithTimeStamp());
		loginpage.enterPassword(prop.getProperty("ValidPassword"));
		loginpage.clickOnLoginButton();
		
		String warningMessage = loginpage.retrieveEmailPasswordNotWarningMessageText();
		String expectedwarningMessage = dataProp.getProperty("emailPasswordNotMatching");
		Assert.assertTrue(warningMessage.contains(expectedwarningMessage), "Expected Warning message is not displayed");	
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailandInvalidPassword() {
		
		loginpage.emailAddress(prop.getProperty("ValidEmail"));
		loginpage.enterPassword(dataProp.getProperty("InvalidPassword"));
		loginpage.clickOnLoginButton();
		String warningMessage = loginpage.retrieveEmailPasswordNotWarningMessageText();
		String expectedwarningMessage = dataProp.getProperty("emailPasswordNotMatching");
		Assert.assertTrue(warningMessage.contains(expectedwarningMessage), "Expected Warning message is not displayed");	
	}
	
	@Test(priority=5)
	public void verifyloginwithoutCredentials() {
		
		loginpage.clickOnLoginButton();
		
		String warningMessage = loginpage.retrieveEmailPasswordNotWarningMessageText();
		String expectedwarningMessage = dataProp.getProperty("emailPasswordNotMatching");
		Assert.assertTrue(warningMessage.contains(expectedwarningMessage), "Expected Warning message is not displayed");	
	}
}
