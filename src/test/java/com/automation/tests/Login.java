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

public class Login extends Base{
	
	public Login() {
		super();
	}
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver = InitializeBrowserURL(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccount();
		homepage.selectLoginOption();
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyLoginWithValidCredentials() {
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailAddress(prop.getProperty("ValidEmail"));
		loginpage.enterPassword(prop.getProperty("ValidPassword"));
		loginpage.clickOnLoginButton();
//		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("ValidEmail"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("ValidPassword"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		AccountPage accountpage = new AccountPage(driver);
		Assert.assertTrue(accountpage.getDisplayStatusOfAccountInformation(), "Edit your account information is not displayed");	
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailAddress(Utilities.generateEmailwithTimeStamp());
		loginpage.enterPassword(dataProp.getProperty("InvalidPassword"));
		loginpage.clickOnLoginButton();
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailwithTimeStamp());
//		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("InvalidPassword"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String warningMessage =loginpage.retrieveEmailPasswordNotWarningMessageText();
		String expectedwarningMessage = dataProp.getProperty("emailPasswordNotMatching");
		Assert.assertTrue(warningMessage.contains(expectedwarningMessage), "Expected Warning message is not displayed");

	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailandValidPassword() {
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailAddress(Utilities.generateEmailwithTimeStamp());
		loginpage.enterPassword(prop.getProperty("ValidPassword"));
		loginpage.clickOnLoginButton();
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailwithTimeStamp());
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("ValidPassword"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String warningMessage = loginpage.retrieveEmailPasswordNotWarningMessageText();
		String expectedwarningMessage = dataProp.getProperty("emailPasswordNotMatching");
		Assert.assertTrue(warningMessage.contains(expectedwarningMessage), "Expected Warning message is not displayed");	
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailandInvalidPassword() {
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailAddress(prop.getProperty("ValidEmail"));
		loginpage.enterPassword(dataProp.getProperty("InvalidPassword"));
		loginpage.clickOnLoginButton();
//		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("ValidEmail"));
//		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("InvalidPassword"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String warningMessage = loginpage.retrieveEmailPasswordNotWarningMessageText();
		String expectedwarningMessage = dataProp.getProperty("emailPasswordNotMatching");
		Assert.assertTrue(warningMessage.contains(expectedwarningMessage), "Expected Warning message is not displayed");	
	}
	
	@Test(priority=5)
	public void verifyloginwithoutCredentials() {
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.clickOnLoginButton();
//		driver.findElement(By.id("input-email")).sendKeys("");
//		driver.findElement(By.id("input-password")).sendKeys("");
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String warningMessage = loginpage.retrieveEmailPasswordNotWarningMessageText();
		String expectedwarningMessage = dataProp.getProperty("emailPasswordNotMatching");
		Assert.assertTrue(warningMessage.contains(expectedwarningMessage), "Expected Warning message is not displayed");	
	}
}
