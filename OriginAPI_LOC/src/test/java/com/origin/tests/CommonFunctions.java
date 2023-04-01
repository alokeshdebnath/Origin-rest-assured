package com.origin.tests;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import pages.OriginPages;

public class CommonFunctions {
	public static WebDriver driver = null;
	WebDriverWait wait = null;
	@BeforeClass
	public void initializeDriver() {
		driver = LoginPage.getDriver();
	}


	//select company and product
	public void selectCompanyAndProduct(String companyName,String productName,WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		OriginPages.selectCompanyDropDownArrow(driver).click();
		js.executeScript("arguments[0].scrollIntoView(true);", OriginPages.selectCompany(driver, companyName));
		js.executeScript("arguments[0].click();", OriginPages.selectCompany(driver, companyName));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		OriginPages.selectProductDropDownArrow(driver).click();
		js.executeScript("arguments[0].scrollIntoView(true);", OriginPages.selectProduct(driver, productName ));
		js.executeScript("arguments[0].click();", OriginPages.selectProduct(driver, productName ));
		js.executeScript("arguments[0].click();",OriginPages.buttonText(driver, "Search"));

	}

	//method for clearing field value
	public void fieldClear(WebElement ele) {
		ele.sendKeys(Keys.CONTROL+"a");
		ele.sendKeys(Keys.DELETE);
	}
	
	//click Operations
		public void clickOperations(WebDriver driver) {
			 wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			//		JavascriptExecutor js = (JavascriptExecutor)driver;
			OriginPages.Menu(driver, "Operations");
			wait.until(ExpectedConditions.visibilityOf(OriginPages.Menu(driver, "Operations"))); 
			wait.until(ExpectedConditions.elementToBeClickable(OriginPages.Menu(driver, "Operations")));
			OriginPages.Menu(driver, "Operations").click(); 
		}


}
