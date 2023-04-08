package tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.OriginPages;

import utils.excelUtils;

//Senjuti Bhadra 23/11/22
public class StatusChange extends CommonFunctions {
	public static WebDriver driver = null;
	String parentWindow = "";
	private static String loanID;
	JavascriptExecutor js = (JavascriptExecutor)driver;
	DataFormatter formatter = new DataFormatter();

	@BeforeClass
	public void initializeDriver() {
		driver = LoginPage.getDriver();
	}

	@Test
	public void statusChangeKYCDataAppr() throws IOException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

//click LOS -> Loans
		clickLOS(driver);
		wait.until(ExpectedConditions.visibilityOf(OriginPages.aText(driver,"Loans"))); 
		wait.until(ExpectedConditions.elementToBeClickable(OriginPages.aText(driver,"Loans")));
		OriginPages.aText(driver, "Loans").click();
		parentWindow = driver.getWindowHandle();
//select company and product
		selectCompanyAndProduct("companies-option-26", "product-option-0","PMatInFort",driver);
		selectDateToday(driver);
		clickSearch(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(OriginPages.threeDots(driver, "long-button")));
		OriginPages.threeDots(driver, "long-button").click();
		OriginPages.threeDotsMenu(driver, "Loan Profile Details").click();
		Set<String> detailsTab = driver.getWindowHandles();
		for(String child:detailsTab) {
			if(!parentWindow.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
				wait.until(ExpectedConditions.elementToBeClickable(OriginPages.h2HeaderClick(driver, "LOAN SUMMARY")));
				OriginPages.h2HeaderClick(driver, "LOAN SUMMARY").click();
				WebElement temp = OriginPages.spanText(driver, "Loan id");
				loanID=temp.getAttribute("innerHTML");

				System.out.println("Loan ID = "+loanID);


				Select statusChangeKyc = new Select(OriginPages.select(driver, "age"));
				statusChangeKyc.selectByVisibleText(" KYC data approved ");

				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		driver.navigate().refresh();
	}

	@Test
	public void statusChangeCreditApproved() throws IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			wait.until(ExpectedConditions.visibilityOf(OriginPages.aText(driver,"Loans"))); 
			wait.until(ExpectedConditions.elementToBeClickable(OriginPages.aText(driver,"Loans")));
			OriginPages.aText(driver, "Loans").click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			parentWindow = driver.getWindowHandle();


		} catch(StaleElementReferenceException e){
			clickLOS(driver);
			wait.until(ExpectedConditions.visibilityOf(OriginPages.aText(driver,"Loans"))); 
			wait.until(ExpectedConditions.elementToBeClickable(OriginPages.aText(driver,"Loans")));
			OriginPages.aText(driver, "Loans").click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			parentWindow = driver.getWindowHandle();
			System.out.println("LOS closed------"+e.getMessage());
		}


		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		selectCompanyAndProduct("companies-option-26", "product-option-0","PMatInFort",driver);
		selectDateToday(driver);
		OriginPages.input(driver, "Search by").sendKeys(loanID);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		clickSearch(driver);
		wait.until(ExpectedConditions.elementToBeClickable(OriginPages.threeDots(driver, "long-button")));
		OriginPages.threeDots(driver, "long-button").click();
		wait.until(ExpectedConditions.elementToBeClickable(OriginPages.threeDotsMenu(driver, "Loan Profile Details")));
		OriginPages.threeDotsMenu(driver, "Loan Profile Details").click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


		Set<String> detailsTab1 = driver.getWindowHandles();
		for(String child:detailsTab1) {
			if(!parentWindow.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
				Select statusChangeCredit = new Select(OriginPages.select(driver, "age"));
				statusChangeCredit.selectByVisibleText(" Credit approved ");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		driver.navigate().refresh();
	}

	@Test
	public void statusChangeDisbursementApproved() throws IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		try {
			wait.until(ExpectedConditions.visibilityOf(OriginPages.aText(driver,"Loans"))); 
			wait.until(ExpectedConditions.elementToBeClickable(OriginPages.aText(driver,"Loans")));
			OriginPages.aText(driver, "Loans").click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			parentWindow = driver.getWindowHandle();


		} catch(StaleElementReferenceException e){
			clickLOS(driver);
			wait.until(ExpectedConditions.visibilityOf(OriginPages.aText(driver,"Loans"))); 
			wait.until(ExpectedConditions.elementToBeClickable(OriginPages.aText(driver,"Loans")));
			OriginPages.aText(driver, "Loans").click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			parentWindow = driver.getWindowHandle();
			System.out.println("LOS closed-----"+e.getMessage());
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		selectCompanyAndProduct("companies-option-26", "product-option-0","PMatInFort",driver);
		selectDateToday(driver);
		fieldClear(OriginPages.input(driver, "Search by"), driver);
		OriginPages.input(driver, "Search by").sendKeys(loanID);
		clickSearch(driver);
		wait.until(ExpectedConditions.elementToBeClickable(OriginPages.threeDots(driver, "long-button")));
		OriginPages.threeDots(driver, "long-button").click();
		wait.until(ExpectedConditions.elementToBeClickable(OriginPages.threeDotsMenu(driver, "Loan Profile Details")));
		OriginPages.threeDotsMenu(driver, "Loan Profile Details").click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Set<String> detailsTab2 = driver.getWindowHandles();
		for(String child:detailsTab2) {
			if(!parentWindow.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
				Select statusChangeDisburse = new Select(OriginPages.select(driver, "age"));
				statusChangeDisburse.selectByVisibleText(" Disbursal approved ");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		driver.navigate().refresh();


		try {
			wait.until(ExpectedConditions.visibilityOf(OriginPages.aText(driver,"Loans"))); 
			wait.until(ExpectedConditions.elementToBeClickable(OriginPages.aText(driver,"Loans")));
			OriginPages.aText(driver, "Loans").click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			parentWindow = driver.getWindowHandle();


		} catch(StaleElementReferenceException e){
			clickLOS(driver);
			wait.until(ExpectedConditions.visibilityOf(OriginPages.aText(driver,"Loans"))); 
			wait.until(ExpectedConditions.elementToBeClickable(OriginPages.aText(driver,"Loans")));
			OriginPages.aText(driver, "Loans").click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			parentWindow = driver.getWindowHandle();
			System.out.println("LOS closed -----+"+e.getMessage());
		}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
			selectCompanyAndProduct("companies-option-26", "product-option-0","PMatInFort",driver);
			selectDateToday(driver);
			fieldClear(OriginPages.input(driver, "Search by"), driver);
			OriginPages.input(driver, "Search by").sendKeys(loanID);
			clickSearch(driver);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


			String status = OriginPages.loanTable(driver, "Disbursement Approved").getText();
			if(status.equalsIgnoreCase("Disbursement Approved")) {
				System.out.println("Loan status is in Disbursement Approved as expected!");
			}
			else {
				System.err.println("Loan Status mismatch, not in Disbursement Approved status!!!");
			}
			driver.navigate().refresh();

		}
	
	public String getLoanID() {
		return loanID;
	}


}
