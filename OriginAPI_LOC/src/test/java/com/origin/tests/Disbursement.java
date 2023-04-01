package com.origin.tests;

import java.time.Duration;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pages.OriginPages;
//
//import tests.LoginPage;
//import utils.excelUtils;

//Senjuti Bhadra 16/03/23

public class Disbursement extends CommonFunctions {
	public static WebDriver driver = null;
	String parentWindow = "";
	String txn_id = "";
//	XSSFSheet finsaalLeadCreationSheet = null;
//	XSSFSheet pMatLeadCreationSheet = null;
//	XSSFWorkbook wb = null;
//	XSSFWorkbook wb2 = null;
//	XSSFSheet pMatLoanCreationSheet = null;
	JavascriptExecutor js = (JavascriptExecutor)driver;
	DataFormatter formatter = new DataFormatter();
	WebDriverWait wait = null;


//	@BeforeClass
	public void initializeDriver() {
		driver = LoginPage.getDriver();
	}

	@Test
	public void disbursement(String responseLoanIDValue) {
		initializeDriver();
		clickOperations(driver);
		//click disbursement option
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(OriginPages.subMenu(driver, "Disbursement"))); 
		wait.until(ExpectedConditions.elementToBeClickable(OriginPages.subMenu(driver,"Disbursement")));
		OriginPages.subMenu(driver,"Disbursement").click();
		selectCompanyAndProduct("companies-option-26", "product-option-58",driver);
		boolean loanID = OriginPages.loanTable(driver, responseLoanIDValue).isDisplayed();
	      if (loanID) {
	         System.out.println("Loan ID is present");
	         OriginPages.loanTableCheckbox(driver, responseLoanIDValue).click();
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
	
	
	
	
/*	
	//select company and product
	public void selectCompanyAndProduct(String companyName,String productName) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		OriginPages.selectCompanyDropDownArrow(driver).click();
		js.executeScript("arguments[0].scrollIntoView(true);", OriginPages.selectCompany(driver, companyName));
		js.executeScript("arguments[0].click();", OriginPages.selectCompany(driver, companyName));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String createLeadHeader = OriginPages.h6Header(driver).getText();
		if(createLeadHeader != null && createLeadHeader.equalsIgnoreCase("Create lead")) {
			OriginPages.bookloanProductDropDownArrow(driver).click();

		} 
		else if(createLeadHeader != null && createLeadHeader.equalsIgnoreCase("Leads")) {
			OriginPages.leadProductDropDownArrow(driver).click();
		}
		js.executeScript("arguments[0].scrollIntoView(true);", OriginPages.selectProduct(driver, productName ));
		js.executeScript("arguments[0].click();", OriginPages.selectProduct(driver, productName ));

	}

	//click Lead and Search		
	public void clickLeadAndSearch() {
		OriginPages.lead(driver).click();
		selectCompanyAndProduct("companies-option-26", "product-option-34");
		OriginPages.fromDate(driver).sendKeys("01/01/2023");
		OriginPages.toDate(driver).sendKeys("01/11/2023");
		OriginPages.searchButton(driver).click();
	}
	//method for clearing field value
	public void fieldClear(WebElement ele) {
		ele.sendKeys(Keys.CONTROL+"a");
		ele.sendKeys(Keys.DELETE);
	}

	@Test
	public void PMatLeadCreation() throws IOException {

		String expectedbookloanHeading = "Create lead";
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		JavascriptExecutor js = (JavascriptExecutor)driver;

		XSSFSheet pMatLeadCreationSheet = excelUtils.getPMatLeadCreationSheet();
		clickLOS();
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

		//select finsall company and product
		selectCompanyAndProduct("companies-option-26", "product-option-54");
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
		
//verify Lead Queue buttons
//	@Test
	public void TC02_LeadQueueButtonVerification() {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		clickLOS();
		clickLeadAndSearch();
		parentWindow = driver.getWindowHandle();

//Edit Lead button
		String expectedEditLeaddHeading = "Edit lead";
		OriginPages.leadQueueButton(driver,"Edit lead").click();
		Set<String> editLeadTab = driver.getWindowHandles();
		for(String child:editLeadTab) {
			if(!parentWindow.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
				String editLeadHeader = OriginPages.h6headercheck(driver,"Edit lead").getText();
		    	if(expectedEditLeaddHeading.equals(editLeadHeader))
			        System.out.println("The expected heading - "+expectedEditLeaddHeading+" is same as actual heading --- "+editLeadHeader);
		    	else
			        System.out.println("The expected heading - "+ expectedEditLeaddHeading +" doesn't match the actual heading --- "+editLeadHeader);
		    	driver.close();
		    	System.out.println("Edit Lead button verified");

			}
		}
		driver.switchTo().window(parentWindow);
//Lead Details button
		String expectedLeadDetailsHeading = "Lead details";
		OriginPages.leadQueueButton(driver,"lead details").click();;
		Set<String> leadDetailsTab = driver.getWindowHandles();
		for(String child:leadDetailsTab) {
			if(!parentWindow.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
				String LeadDetailsHeader = OriginPages.h2headercheck(driver,"Lead details").getText();
		    	if(expectedLeadDetailsHeading.equals(LeadDetailsHeader))
			        System.out.println("The expected heading - "+expectedLeadDetailsHeading+" is same as actual heading --- "+LeadDetailsHeader);
		    	else
			        System.out.println("The expected heading - "+ expectedLeadDetailsHeading +" doesn't match the actual heading --- "+LeadDetailsHeader);
		    	driver.close();
		    	System.out.println("Lead deatils button verified");

			}
		}
		driver.switchTo().window(parentWindow);
//Documents button
		OriginPages.leadQueueButton(driver,"open documents").click();;
		Set<String> documentTab = driver.getWindowHandles();
		for(String child:documentTab) {
			if(!parentWindow.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
				boolean t=OriginPages.buttonText(driver, "pre_approval").isDisplayed();
		    	if(t)
		          	System.out.println("Documents page is displayed");
		    	else
		          	System.out.println("Documents page is not displayed");
		    	driver.close();
		    	System.out.println("Documents button verified");

			}
		}
		driver.switchTo().window(parentWindow);

//Action button
	String expectedAddInfoHeading = "Additional info";
	wait.until(ExpectedConditions.visibilityOf(OriginPages.leadQueueButton(driver, "fill loan")));
	OriginPages.actionButton(driver).click();
	Set<String> addInfoTab = driver.getWindowHandles();
	for(String child:addInfoTab) {
		if(!parentWindow.equalsIgnoreCase(child)) {
			driver.switchTo().window(child);
			String addInfoHeader = OriginPages.h6headercheck(driver,"Additional info").getText();
	    	if(expectedAddInfoHeading.equals(addInfoHeader))
		        System.out.println("The expected heading - "+expectedAddInfoHeading+" is same as actual heading --- "+addInfoHeader);
	    	else
		        System.out.println("The expected heading - "+ expectedAddInfoHeading +" doesn't match the actual heading --- "+addInfoHeader);
	    	driver.close();
	    	System.out.println("Additional Info button verified");

		}
	}
	driver.switchTo().window(parentWindow);
//Logs button
	String expectedLogsHeading = "Lead details";
	OriginPages.leadQueueButton(driver,"fill loan").click();;
	String logHeader = OriginPages.h2headercheck(driver,"Logs").getText();
	if(expectedLogsHeading.equals(logHeader))
	        System.out.println("The expected heading - "+expectedLogsHeading+" is same as actual heading --- "+logHeader);
	else
	        System.out.println("The expected heading - "+ expectedLogsHeading +" doesn't match the actual heading --- "+logHeader);
//	js.executeScript("arguments[0].scrollIntoView(true);", OriginPages.closeButton(driver));
	OriginPages.buttonText(driver, "Close").click();
	System.out.println("Logs button verified");


} 	 

	
//Lead edit verification
//			@Test
			public void TC03_EditLeadVerification() throws IOException {
				clickLOS();
				clickLeadAndSearch();
				parentWindow = driver.getWindowHandle();
				XSSFSheet pMatLeadCreationSheet = excelUtils.getPMatLeadCreationSheet();		
				JavascriptExecutor js = (JavascriptExecutor)driver;
				OriginPages.leadQueueButton(driver,"Edit lead").click();
				Set<String> editLeadTab = driver.getWindowHandles();
				for(String child:editLeadTab) {
					if(!parentWindow.equalsIgnoreCase(child)) {
						driver.switchTo().window(child);
						fieldClear(OriginPages.editableFields(driver, "Partner Loan App Id"));
						OriginPages.editableFields(driver, "Partner Loan App Id").sendKeys(pMatLeadCreationSheet.getRow(1).getCell(2).getStringCellValue());
						fieldClear(OriginPages.editableFields(driver, "Partner Borrower Id"));
						OriginPages.editableFields(driver, "Partner Borrower Id").sendKeys(pMatLeadCreationSheet.getRow(2).getCell(2).getStringCellValue());
						OriginPages.h2HeaderClick(driver, "Personal Details").click();
						fieldClear(OriginPages.editableFields(driver, "First Name"));
						OriginPages.editableFields(driver, "First Name").sendKeys(pMatLeadCreationSheet.getRow(3).getCell(2).getStringCellValue());
						fieldClear(OriginPages.editableFields(driver, "Last Name"));
						OriginPages.editableFields(driver, "Last Name").sendKeys(pMatLeadCreationSheet.getRow(4).getCell(2).getStringCellValue());
						fieldClear(OriginPages.editableFields(driver, "Type Of Addr."));
						OriginPages.editableFields(driver, "Type Of Addr.").sendKeys(pMatLeadCreationSheet.getRow(5).getCell(2).getStringCellValue());
						fieldClear(OriginPages.editableFields(driver, "Resident Addr. Line 1"));
						OriginPages.editableFields(driver, "Resident Addr. Line 1").sendKeys(pMatLeadCreationSheet.getRow(6).getCell(2).getStringCellValue());
						OriginPages.dropDownButton(driver, "2").click();
				    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						List<WebElement> statenames = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[1]/ul[1]/li")); 
						System.out.println("Number of states : "+statenames.size());
						for(WebElement stnames : statenames) {
							if(stnames.getText().equals("Bihar")) {
								stnames.click();
								break;
							}
						}
						js.executeScript("arguments[0].click();", OriginPages.cityNameTextbox(driver));
						OriginPages.cityNameTextbox(driver).sendKeys(pMatLeadCreationSheet.getRow(8).getCell(2).getStringCellValue());
				    	js.executeScript("arguments[0].scrollIntoView(true);", OriginPages.editCityName(driver));
						js.executeScript("arguments[0].click();", OriginPages.editCityName(driver));
						fieldClear(OriginPages.editableFields(driver, "Pincode"));
						OriginPages.editableFields(driver, "Pincode").sendKeys(pMatLeadCreationSheet.getRow(9).getCell(2).getStringCellValue());
						fieldClear(OriginPages.editableFields(driver, "Date Of Birth"));
						OriginPages.editableFields(driver, "Date Of Birth").sendKeys(pMatLeadCreationSheet.getRow(12).getCell(2).getStringCellValue());
						fieldClear(OriginPages.editableFields(driver, "Gender"));
						OriginPages.editableFields(driver, "Gender").sendKeys(pMatLeadCreationSheet.getRow(13).getCell(2).getStringCellValue());

						OriginPages.h2HeaderClick(driver, "Contact Details").click();
						double contactN = pMatLeadCreationSheet.getRow(10).getCell(2).getNumericCellValue();
						int cont = (int) contactN;
						String c = Integer.toString(cont);
						fieldClear(OriginPages.editableFields(driver, "Contact No."));
						OriginPages.editableFields(driver, "Contact No.").sendKeys(c);

						OriginPages.h2HeaderClick(driver, "KYC Details").click();
						fieldClear(OriginPages.editableFields(driver, "Pan No."));
						OriginPages.editableFields(driver, "Pan No.").sendKeys(pMatLeadCreationSheet.getRow(11).getCell(2).getStringCellValue());
						OriginPages.h2HeaderClick(driver, "Financial Details").click();
						fieldClear(OriginPages.editableFields(driver, "Bank Name"));
						OriginPages.editableFields(driver, "Bank Name").sendKeys(pMatLeadCreationSheet.getRow(14).getCell(2).getStringCellValue());
						fieldClear(OriginPages.editableFields(driver, "Bank Acc. No."));
						OriginPages.editableFields(driver, "Bank Acc. No.").sendKeys(pMatLeadCreationSheet.getRow(15).getCell(2).getStringCellValue());
						fieldClear(OriginPages.editableFields(driver, "Bank IFSC Code"));
						OriginPages.editableFields(driver, "Bank IFSC Code").sendKeys(pMatLeadCreationSheet.getRow(16).getCell(2).getStringCellValue());

						OriginPages.submit(driver).click();
//				    	driver.close();
				    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

					}
				}
			} 
			
*/
	 
}
