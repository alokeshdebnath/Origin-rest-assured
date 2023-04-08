package tests;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.OriginPages;
import tests.LeadCreation;
import utils.excelUtils;

public class LoanCreation extends CommonFunctions{
	public static WebDriver driver = null;
	String parentWindow = "";
	XSSFSheet pMatLoanCreationSheet = null;
	DataFormatter formatter = new DataFormatter();

	@BeforeClass
	public void initializeDriver() {
		driver = LoginPage.getDriver();
	}
	
	@Test 
	public void PMatLoanCreation () throws IOException {
		XSSFSheet pMatLoanCreationSheet = excelUtils.getPMatLoanCreationSheet();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    	
		OriginPages.editableFields(driver, "Partner Loan ID").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(1).getCell(1)));
		OriginPages.editableFields(driver, "Loan Application Date").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(2).getCell(1)));
		OriginPages.editableFields(driver, "Sanction Amount").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(3).getCell(1)));
		OriginPages.editableFields(driver, "Subvention Fees").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(4).getCell(1)));
		OriginPages.editableFields(driver, "Upfront Interest").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(5).getCell(1)));
		OriginPages.editableFields(driver, "Conv Fees").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(6).getCell(1)));
		OriginPages.editableFields(driver, "Application Fees").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(7).getCell(1)));
		OriginPages.editableFields(driver, "Insurance amount").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(8).getCell(1)));
		OriginPages.editableFields(driver, "Net Disbursement Amount").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(9).getCell(1)));
		OriginPages.editableFields(driver, "Loan Interest Rate").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(10).getCell(1)));
		OriginPages.editableFields(driver, "Repayment Type").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(11).getCell(1)));
		OriginPages.editableFields(driver, "Tenure Type").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(12).getCell(1)));
		OriginPages.editableFields(driver, "Tenure").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(13).getCell(1)));
		OriginPages.editableFields(driver, "First Installment Date").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(14).getCell(1)));
		OriginPages.editableFields(driver, "EMI count").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(15).getCell(1)));
		OriginPages.editableFields(driver, "Mandate ref number").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(16).getCell(1)));

		OriginPages.h2HeaderClick(driver, "FINANCIAL DETAILS").click();
		OriginPages.editableFields(driver, "UMRN").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(17).getCell(1)));
		OriginPages.editableFieldsInput(driver, "string_vl_borro_bank_name").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(18).getCell(1)));
		OriginPages.editableFieldsInput(driver, "alphanum_vl_borro_bank_acc_num").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(19).getCell(1)));
		OriginPages.editableFieldsInput(driver, "ifsc_vl_borro_bank_ifsc").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(20).getCell(1)));
		OriginPages.editableFields(driver, "Borrower Bank Account Holder name").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(21).getCell(1)));
		OriginPages.editableFields(driver, "Borrower Bank Account Type").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(22).getCell(1)));
		OriginPages.editableFields(driver, "bene_bank_name").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(23).getCell(1)));
		OriginPages.editableFields(driver, "bene_bank_acc_num").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(24).getCell(1)));
		OriginPages.editableFields(driver, "bene_bank_ifsc").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(25).getCell(1)));
		OriginPages.editableFields(driver, "bene_bank_account_holder_name").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(26).getCell(1)));
		OriginPages.editableFields(driver, "bene_bank_account_type").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(27).getCell(1)));



		OriginPages.h2HeaderClick(driver, "BUREAU DETAILS").click();
		OriginPages.editableFields(driver, "bounces_in_one_month").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(28).getCell(1)));
		
		OriginPages.h2HeaderClick(driver, "BUSINESS DETAILS").click();
		OriginPages.editableFields(driver, "GST Number").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(29).getCell(1)));
		OriginPages.editableFields(driver, "Other business reg no").sendKeys(formatter.formatCellValue(pMatLoanCreationSheet.getRow(30).getCell(1)));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		OriginPages.submit(driver).click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	driver.navigate().refresh();

	}

}
