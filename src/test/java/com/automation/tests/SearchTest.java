package com.automation.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.atuomation.pages.HomePage;
import com.atuomation.pages.SearchPage;
import com.automation.base.Base;

public class SearchTest extends Base{
	
	public SearchTest() {
		super();
	}
	WebDriver driver;
	SearchPage  searchpage;
	HomePage homepage;
	
	@BeforeMethod
	public void setup() {
		
		driver = InitializeBrowserURL(prop.getProperty("browser"));
		homepage = new HomePage(driver);
		searchpage = new SearchPage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	
	@Test(priority=1)
	public void verifySearchwithValidProduct() {
		
		homepage.enterProductInSearchBox(dataProp.getProperty("ValidProduct"));
		homepage.clickonSearchButton();
		Assert.assertTrue(searchpage.displayStatusOfProduct(),"Valid Product HP is not displayed");
	}
	
	@Test(priority=2)
	public void verifySearchwithInvalidProduct() {
		
		homepage.enterProductInSearchBox(dataProp.getProperty("InvalidProduct"));
		homepage.clickonSearchButton();
		String actualSearchInput = searchpage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchInput,dataProp.getProperty("CriteriaWarning"),"No product message in search results is not displayed");
	}
	
	@Test(priority=3)
	public void verifySearchwithoutProduct() {
		
		homepage.clickonSearchButton();
		String actualSearchInput = searchpage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchInput,dataProp.getProperty("CriteriaWarning"),"No product message in search results is not displayed");
	}

}
