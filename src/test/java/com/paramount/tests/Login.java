package com.paramount.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.paramount.base.Base;
import com.paramount.pages.HomePage;
import com.paramount.pages.LoginPage;
import com.paramount.Utilities.Utilities;

public class Login extends Base {
	public WebDriver driver;
	
    @BeforeMethod
	public void setup() throws IOException
	{
    	loadPropertiesFile();
    	driver= initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
    	HomePage homePage= new HomePage(driver);
    	homePage.clickOnMyAccount();
    	homePage.selectLoginOption();    		
		
	} 
   
	
	
    @Test(priority=1)
	public void verifyLoginWithValidCredentials() throws InterruptedException
	{
    	
		System.out.println("Test Case 1");
		LoginPage loginPage= new LoginPage(driver);
		loginPage.performLogin("sachintestselenium1591@gmail.com", "12345");	
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Edit your account information']")).isDisplayed());
        
	}
    
    @Test(priority=2)
    public void verifyLoginWithInvalidCredentials() throws InterruptedException
	{
    	
		System.out.println("Test Case 2");
		LoginPage loginPage= new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimestamp());
		loginPage.enterPassword(prop.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();			
		
		Thread.sleep(10000);
		String actualText =driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertEquals(actualText, prop.getProperty("emailPasswordNoMatchWarning"));
	}
    
    @Test(priority=3)
    public void verifyLoginWithInvalidUsername() throws InterruptedException
	{
    	
		System.out.println("Test Case 3");
		
		LoginPage loginPage= new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimestamp());
		loginPage.enterPassword(prop.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();		
		
		String actualText =driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertEquals(actualText, prop.getProperty("emailPasswordNoMatchWarning"));
		
	}
    
    @Test(priority=4)
    public void verifyLoginWithInvalidPassword() throws InterruptedException
	{
    	
		System.out.println("Test Case 4");
		
		LoginPage loginPage= new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimestamp());
		loginPage.enterPassword(prop.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();			
		
		String actualText =driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertEquals(actualText, prop.getProperty("emailPasswordNoMatchWarning"));
		
	}
    
    
    
    @AfterMethod
	public void tearDown() {
		driver.quit();
	}
   
    
}
