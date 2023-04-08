package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import pages.OriginPages;

//Senjuti Bhadra 16/03/23

public class Disbursement extends CommonFunctions {
	public static WebDriver driver = null;
	String parentWindow = "";
	String txn_id = "";
	JavascriptExecutor js = (JavascriptExecutor)driver;
	DataFormatter formatter = new DataFormatter();
	WebDriverWait wait = null;
	private StatusChange statusChange;

	@BeforeClass
	public void initializeDriver() {
		driver = LoginPage.getDriver();
	}
	@Test
	public void disbursement () {
		initializeDriver();
		clickOperations(driver);
		statusChange = new StatusChange();
//click disbursement option
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(OriginPages.subMenu(driver, "Disbursement"))); 
		wait.until(ExpectedConditions.elementToBeClickable(OriginPages.subMenu(driver,"Disbursement")));
		OriginPages.subMenu(driver,"Disbursement").click();
		selectCompanyAndProduct("companies-option-26", "product-option-0","PMatInFort",driver);
		OriginPages.inputID(driver, "combo-box-demo").sendKeys("No");
		OriginPages.liID(driver, "combo-box-demo-option-0").click();
		OriginPages.buttonText(driver, "Search").click();
		String loanId = statusChange.getLoanID();

		System.out.println("Loan id============="+loanId);
		boolean loanID = OriginPages.loanTable(driver,loanId).isDisplayed();
		if (loanID) {
			System.out.println("Loan ID is present");
			OriginPages.loanTableCheckbox(driver, loanId).click();
			OriginPages.buttonText(driver, "Disburse").click();;
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			String disbursementDetails = OriginPages.textArea(driver,"Success requests").getText();
			String[] arrSplit = disbursementDetails.split(" - ");
			for (int i=0; i < arrSplit.length; i++)
			{
				System.out.println(arrSplit[i]);
			}
			txn_id = arrSplit[1].trim();
			System.out.println("txn_id is "+txn_id);

		} else {
			System.err.println("Txn ID is not present");
		}
		driver.close();


	}
	public String getTxnID() {
		return txn_id;
	}



}
