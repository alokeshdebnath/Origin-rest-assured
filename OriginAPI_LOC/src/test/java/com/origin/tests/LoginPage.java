package com.origin.tests;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import pages.OriginPages;
//import utils.excelUtils;
import variables.configProperties;

	//Senjuti Bhadra 23/11/22
public class LoginPage {
	public static WebDriver driver=null;
	String parentWindow = "";

//set up browser
//		@Parameter("browserNames")
//		@BeforeClass
		public void setUp() {
		configProperties.initializePropertyFile();
		 if(configProperties.property.getProperty("BrowserType").equalsIgnoreCase("chrome"))
		  {
			 System.setProperty("webdriver.chrome.driver", "D:/eclipse_workspace/OriginAPI_LOC/OriginAPI_LOC/drivers/chromedriver/chromedriver.exe");
			 driver =  new ChromeDriver(); 
		  } else {
			  System.out.println("Failed to initialize driver");
		  }
		 
	 	}
		
		public static WebDriver getDriver() {
			return driver;
		}
//Origin dev env login
		@Test
		public void login() {
			setUp();
			configProperties.initializePropertyFile();
			driver.get(configProperties.property.getProperty("OriginDevUrl"));
//			driver.get("https://dev-origin.arthmate.com/login");
			driver.manage().window().maximize();
			OriginPages.loginID(driver).sendKeys(configProperties.property.getProperty("UserId"));
			OriginPages.loginPassword(driver).sendKeys(configProperties.property.getProperty("Password"));
			OriginPages.signIn(driver).click();
	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}

}
	 

