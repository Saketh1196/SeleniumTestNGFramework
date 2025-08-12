package com.automation.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.atuomation.pages.HomePage;
import com.atuomation.pages.SearchPage;
import com.automation.base.Base;

public class Search extends Base{
	
	public Search() {
		super();
	}
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver = InitializeBrowserURL(prop.getProperty("browser"));
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	
	@Test(priority=1)
	public void verifySearchwithValidProduct() {
		
		HomePage homepage = new HomePage(driver);
		homepage.enterProductInSearchBox(dataProp.getProperty("ValidProduct"));
		homepage.clickonSearchButton();
//		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("ValidProduct"));
//		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		SearchPage searchpage = new SearchPage(driver);
		Assert.assertTrue(searchpage.displayStatusOfProduct(),"Valid Product HP is not displayed");
	}
	
	@Test(priority=2)
	public void verifySearchwithInvalidProduct() {
		
		HomePage homepage = new HomePage(driver);
		homepage.enterProductInSearchBox(dataProp.getProperty("InvalidProduct"));
		homepage.clickonSearchButton();
//		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("InvalidProduct"));
//		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		SearchPage searchpage = new SearchPage(driver);
		String actualSearchInput = searchpage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchInput,dataProp.getProperty("CriteriaWarning"),"No product message in search results is not displayed");
	}
	
	@Test(priority=3)
	public void verifySearchwithoutProduct() {
		
		HomePage homepage = new HomePage(driver);
		homepage.clickonSearchButton();
//		driver.findElement(By.name("search")).sendKeys("");
//		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		SearchPage searchpage = new SearchPage(driver);
		String actualSearchInput = searchpage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchInput,dataProp.getProperty("CriteriaWarning"),"No product message in search results is not displayed");
	}

}
