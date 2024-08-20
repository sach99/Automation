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
import com.paramount.pages.RegisterPage;

public class Register extends Base {
	WebDriver driver;
	@BeforeMethod
	public void setup() throws InterruptedException, IOException
	{
		loadPropertiesFile();
    	driver= initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
    	HomePage homePage= new HomePage(driver);
    	homePage.clickOnMyAccount();
    	homePage.selectRegisterOption();    		
		Thread.sleep(4000);
		
	}
	
	@Test
	public void verifyRegisteringAccountWithMandatoryFields() throws InterruptedException
	{
		
	    
		RegisterPage registerPage= new RegisterPage(driver);
		registerPage.registerWithMandatoryFields(prop.getProperty("firstName"), prop.getProperty("lastName"), "", "1234567890", "12345");
		Thread.sleep(3000);
		//String actualText = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		
		
		
	}
	
	@Test
	public void verifyRegisteringAccountWithAllFields()
	{
			
		
		RegisterPage registerPage= new RegisterPage(driver);
		registerPage.registerWithAllFields(prop.getProperty("firstName"), prop.getProperty("lastName"), "", "1234567890", "12345");
		
		String actualText = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		
		Assert.assertEquals(actualText, "Your Account Has Been Created!","Text is not matching");
		
	}
	
	@Test
	public void verifyRegisteringAccountWithDuplicateEmail()
	{
		
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("sachin");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("selenium");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("sachintestselenium1591@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("1234567890");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@name='newsletter']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualText = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		
		Assert.assertEquals(actualText, "Warning: E-Mail Address is already registered!","Text is not matching");
		
	}
	
	@AfterMethod
	public void teaDown()
	{
		driver.quit();
	}
	
	
	
}
