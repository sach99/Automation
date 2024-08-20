package com.paramount.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	WebDriver driver;
	public Properties prop;	
		public void loadPropertiesFile() throws IOException
		{
			prop = new Properties();
			File propFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\paramount\\config\\config.properties");
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		}
		public WebDriver initializeBrowserAndOpenApplication(String browserName)
		{
			
			
			if(browserName.equals("chrome"))
			{
				driver= new ChromeDriver();
			}
			if(browserName.equals("firefox"))
			{
				driver= new FirefoxDriver();
			}
			if(browserName.equals("edge"))
			{
				driver= new EdgeDriver();
			}
			
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(prop.getProperty("url"));		
			driver.manage().window().maximize();
			return driver;
		}
}
