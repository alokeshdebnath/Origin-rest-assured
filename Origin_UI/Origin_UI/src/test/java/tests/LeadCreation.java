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

import tests.LoginPage;
import utils.excelUtils;

//Senjuti Bhadra 23/11/22
public class LeadCreation extends CommonFunctions {
	public static WebDriver driver = null;
	String parentWindow = "";
	XSSFSheet finsaalLeadCreationSheet = null;
	XSSFSheet pMatLeadCreationSheet = null;
	XSSFWorkbook wb = null;
	XSSFWorkbook wb2 = null;
	XSSFSheet pMatLoanCreationSheet = null;
	JavascriptExecutor js = (JavascriptExecutor)driver;
	DataFormatter formatter = new DataFormatter();

	@BeforeClass
	public void initializeDriver() {
		driver = LoginPage.getDriver();
	}

	@Test
	public void PMatLeadCreation() throws IOException {

		String expectedbookloanHeading = "Create lead";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		JavascriptExecutor js = (JavascriptExecutor)driver;

		XSSFSheet pMatLeadCreationSheet = excelUtils.getPMatLeadCreationSheet();
		clickLOS(driver);
		//click book loan
		wait.until(ExpectedConditions.visibilityOf(OriginPages.lead(driver))); 
		wait.until(ExpectedConditions.elementToBeClickable(OriginPages.lead(driver)));
		OriginPages.bookLoan(driver).click(); 
		//verifying book loan page header
		String bookLoanHeader = OriginPages.h6headercheck(driver,"Create lead").getText();
		if(expectedbookloanHeading.equals(bookLoanHeader))
			System.out.println("The expected heading is same as actual heading --- "+bookLoanHeader);
		else
			System.out.println("The expected heading doesn't match the actual heading --- "+bookLoanHeader);

		//select company and product
		selectCompanyAndProduct("companies-option-26", "product-option-0","PMatInFort",driver);
		//fill out lead form  
		OriginPages.editableFields(driver, "Partner Loan App Id").sendKeys(pMatLeadCreationSheet.getRow(1).getCell(1).getStringCellValue());
		OriginPages.editableFields(driver, "Partner Borrower Id").sendKeys(pMatLeadCreationSheet.getRow(2).getCell(1).getStringCellValue());
		OriginPages.editableFields(driver, "First Name").sendKeys(pMatLeadCreationSheet.getRow(3).getCell(1).getStringCellValue());
		OriginPages.editableFields(driver, "Last Name").sendKeys(pMatLeadCreationSheet.getRow(4).getCell(1).getStringCellValue());
		OriginPages.editableFields(driver, "Type Of Addr.").sendKeys(pMatLeadCreationSheet.getRow(5).getCell(1).getStringCellValue());
		OriginPages.editableFields(driver, "Resident Addr. Line 1").sendKeys(pMatLeadCreationSheet.getRow(6).getCell(1).getStringCellValue());

		OriginPages.stateNameDropDown(driver).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		List<WebElement> statenames = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[1]/ul[1]/li")); 
		System.out.println("Number of states : "+statenames.size());
		for(WebElement stnames : statenames) {
			if(stnames.getText().equals("Maharashtra")) {
				stnames.click();
				break;
			}
		}

		js.executeScript("arguments[0].click();", OriginPages.cityNameTextbox(driver));
		OriginPages.cityNameTextbox(driver).sendKeys(pMatLeadCreationSheet.getRow(8).getCell(1).getStringCellValue());
		js.executeScript("arguments[0].scrollIntoView(true);", OriginPages.cityName(driver));
		js.executeScript("arguments[0].click();", OriginPages.cityName(driver));

		OriginPages.editableFields(driver, "Pincode").sendKeys(pMatLeadCreationSheet.getRow(9).getCell(2).getStringCellValue()); 
		double contact = pMatLeadCreationSheet.getRow(10).getCell(1).getNumericCellValue();
		int cn = (int) contact;
		String c = Integer.toString(cn);
		OriginPages.contactNo(driver).sendKeys(c);
		OriginPages.editableFields(driver, "Pan No.").sendKeys(pMatLeadCreationSheet.getRow(11).getCell(1).getStringCellValue());
		OriginPages.editableFields(driver, "Date Of Birth").sendKeys(pMatLeadCreationSheet.getRow(12).getCell(1).getStringCellValue());
		OriginPages.editableFields(driver, "Gender").sendKeys(pMatLeadCreationSheet.getRow(13).getCell(1).getStringCellValue());
		OriginPages.editableFields(driver, "Bank Name").sendKeys(pMatLeadCreationSheet.getRow(14).getCell(1).getStringCellValue());
		OriginPages.editableFields(driver, "Bank Acc. No.").sendKeys(pMatLeadCreationSheet.getRow(15).getCell(1).getStringCellValue());
		OriginPages.editableFields(driver, "Bank IFSC Code").sendKeys(pMatLeadCreationSheet.getRow(16).getCell(1).getStringCellValue());

		//click submit lead			
		OriginPages.submit(driver).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}  
	 
}
