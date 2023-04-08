package tests;

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


	//click LOS
	public void clickLOS(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		OriginPages.LOS(driver);

		wait.until(ExpectedConditions.visibilityOf(OriginPages.menu(driver, "LOS"))); 
		wait.until(ExpectedConditions.elementToBeClickable(OriginPages.menu(driver, "LOS")));

		OriginPages.menu(driver, "LOS").click(); 
	}
	//select company and product 
	public void selectCompanyAndProduct(String companyName,String productNumber,String productName,WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		OriginPages.selectCompanyDropDownArrow(driver).click();
		js.executeScript("arguments[0].scrollIntoView(true);", OriginPages.selectCompany(driver, companyName));
		js.executeScript("arguments[0].click();", OriginPages.selectCompany(driver, companyName));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		OriginPages.inputID(driver,"product" ).sendKeys(productName);
		js.executeScript("arguments[0].scrollIntoView(true);", OriginPages.selectProduct(driver, productNumber ));
		js.executeScript("arguments[0].click();", OriginPages.selectProduct(driver, productNumber ));

	}
	//select date range
	public void selectDateToday(WebDriver driver) {
		OriginPages.inputID(driver, "mui-2").sendKeys("Today");
		OriginPages.liID(driver, "mui-2-option-0").click();
	}
	public void selectDateYesterday(WebDriver driver) {
		OriginPages.inputID(driver, "mui-2").sendKeys("Yesterday");
		OriginPages.liID(driver, "mui-2-option-0").click();
	}
	public void selectDateLast7Days(WebDriver driver) {
		OriginPages.inputID(driver, "mui-2").sendKeys("Last 7 days");
		OriginPages.liID(driver, "mui-2-option-0").click();
	}
	public void selectDateThisMonth(WebDriver driver) {
		OriginPages.inputID(driver, "mui-2").sendKeys("This month");
		OriginPages.liID(driver, "mui-2-option-0").click();
	}
	public void selectDateCustom(String fromDate,String toDate,WebDriver driver) {
		OriginPages.inputID(driver, "mui-2").sendKeys("Custom");
		OriginPages.liID(driver, "mui-2-option-0").click();
		OriginPages.fromDate(driver).sendKeys(fromDate);
		OriginPages.toDate(driver).sendKeys(toDate);
	}

	//click Search button
	public void clickSearch(WebDriver driver) {
		OriginPages.searchButton(driver).click();

	}

	//method for clearing field value
	public void fieldClear(WebElement ele,WebDriver driver) {
		ele.sendKeys(Keys.CONTROL+"a");
		ele.sendKeys(Keys.DELETE);
	}
	//click Operations
	public void clickOperations(WebDriver driver) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",OriginPages.menu(driver, "Operations"));
		wait.until(ExpectedConditions.visibilityOf(OriginPages.menu(driver, "Operations"))); 
		wait.until(ExpectedConditions.elementToBeClickable(OriginPages.menu(driver, "Operations")));
		OriginPages.menu(driver, "Operations").click();
	}

}
