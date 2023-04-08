package tests;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import pages.OriginPages;
import utils.excelUtils;
import variables.configProperties;

//Senjuti Bhadra 23/11/22
public class LoginPage {
	public static WebDriver driver = null;
	String parentWindow = "";

//set up browser
//		@Parameter("browserNames")
//		@BeforeClass
		public void setUp() {
		configProperties.initializePropertyFile();
		 if(configProperties.property.getProperty("BrowserType").equalsIgnoreCase("chrome"))
		  {
//			 System.setProperty("webdriver.chrome.driver", "D:/eclipse_workspace/Origin_UI/drivers/chromedriver/chromedriver.exe");
			 System.setProperty("webdriver.chrome.driver", "drivers/chromedriver/chromedriver.exe");

			 ChromeOptions chromeOptions = new ChromeOptions();
			 chromeOptions.addArguments("--remote-allow-origins=*");
			 driver = new ChromeDriver(chromeOptions);
		  }
		 
	 	}
		
		public static WebDriver getDriver() {
			return driver;
		}
//Origin dev env login
		@Test
		public void Login() {
			setUp();
			driver.get(configProperties.property.getProperty("OriginDevUrl"));
			driver.manage().window().maximize();
			OriginPages.input(driver, "Email").sendKeys(configProperties.property.getProperty("UserId"));
			OriginPages.input(driver, "Password").sendKeys(configProperties.property.getProperty("Password"));
			OriginPages.signIn(driver).click();
	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}

}
	 

