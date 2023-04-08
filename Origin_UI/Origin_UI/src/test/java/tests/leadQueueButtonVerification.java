//package tests;
//
//import java.time.Duration;
//import java.util.Set;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import pages.OriginPages;
//
//public class leadQueueButtonVerification extends CommonFunctions{
//	public static WebDriver driver = null;
//	String parentWindow = "";
//	@BeforeClass
//	public void initializeDriver() {
//		driver = LoginPage.getDriver();
//	}
// 
//	
//	//verify Lead Queue buttons
//		@Test
//		public void TC02_LeadQueueButtonVerification() {
////			JavascriptExecutor js = (JavascriptExecutor)driver;
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//			clickLOS(driver);
//			clickLeadAndSearch(driver);
//			parentWindow = driver.getWindowHandle();
//
//	//Edit Lead button
//			String expectedEditLeaddHeading = "Edit lead";
//			OriginPages.leadQueueButton(driver,"Edit lead").click();
//			Set<String> editLeadTab = driver.getWindowHandles();
//			for(String child:editLeadTab) {
//				if(!parentWindow.equalsIgnoreCase(child)) {
//					driver.switchTo().window(child);
//					String editLeadHeader = OriginPages.h6headercheck(driver,"Edit lead").getText();
//			    	if(expectedEditLeaddHeading.equals(editLeadHeader))
//				        System.out.println("The expected heading - "+expectedEditLeaddHeading+" is same as actual heading --- "+editLeadHeader);
//			    	else
//				        System.out.println("The expected heading - "+ expectedEditLeaddHeading +" doesn't match the actual heading --- "+editLeadHeader);
//			    	driver.close();
//			    	System.out.println("Edit Lead button verified");
//
//				}
//			}
//			driver.switchTo().window(parentWindow);
//	//Lead Details button
//			String expectedLeadDetailsHeading = "Lead details";
//			OriginPages.leadQueueButton(driver,"lead details").click();;
//			Set<String> leadDetailsTab = driver.getWindowHandles();
//			for(String child:leadDetailsTab) {
//				if(!parentWindow.equalsIgnoreCase(child)) {
//					driver.switchTo().window(child);
//					String LeadDetailsHeader = OriginPages.h2headercheck(driver,"Lead details").getText();
//			    	if(expectedLeadDetailsHeading.equals(LeadDetailsHeader))
//				        System.out.println("The expected heading - "+expectedLeadDetailsHeading+" is same as actual heading --- "+LeadDetailsHeader);
//			    	else
//				        System.out.println("The expected heading - "+ expectedLeadDetailsHeading +" doesn't match the actual heading --- "+LeadDetailsHeader);
//			    	driver.close();
//			    	System.out.println("Lead deatils button verified");
//
//				}
//			}
//			driver.switchTo().window(parentWindow);
//	//Documents button
//			OriginPages.leadQueueButton(driver,"open documents").click();;
//			Set<String> documentTab = driver.getWindowHandles();
//			for(String child:documentTab) {
//				if(!parentWindow.equalsIgnoreCase(child)) {
//					driver.switchTo().window(child);
//					boolean t=OriginPages.buttonText(driver, "pre_approval").isDisplayed();
//			    	if(t)
//			          	System.out.println("Documents page is displayed");
//			    	else
//			          	System.out.println("Documents page is not displayed");
//			    	driver.close();
//			    	System.out.println("Documents button verified");
//
//				}
//			}
//			driver.switchTo().window(parentWindow);
//
//	//Action button
//		String expectedAddInfoHeading = "Additional info";
//		wait.until(ExpectedConditions.visibilityOf(OriginPages.leadQueueButton(driver, "fill loan")));
//		OriginPages.actionButton(driver).click();
//		Set<String> addInfoTab = driver.getWindowHandles();
//		for(String child:addInfoTab) {
//			if(!parentWindow.equalsIgnoreCase(child)) {
//				driver.switchTo().window(child);
//				String addInfoHeader = OriginPages.h6headercheck(driver,"Additional info").getText();
//		    	if(expectedAddInfoHeading.equals(addInfoHeader))
//			        System.out.println("The expected heading - "+expectedAddInfoHeading+" is same as actual heading --- "+addInfoHeader);
//		    	else
//			        System.out.println("The expected heading - "+ expectedAddInfoHeading +" doesn't match the actual heading --- "+addInfoHeader);
//		    	driver.close();
//		    	System.out.println("Additional Info button verified");
//
//			}
//		}
//		driver.switchTo().window(parentWindow);
//	//Logs button
//		String expectedLogsHeading = "Lead details";
//		OriginPages.leadQueueButton(driver,"fill loan").click();;
//		String logHeader = OriginPages.h2headercheck(driver,"Logs").getText();
//		if(expectedLogsHeading.equals(logHeader))
//		        System.out.println("The expected heading - "+expectedLogsHeading+" is same as actual heading --- "+logHeader);
//		else
//		        System.out.println("The expected heading - "+ expectedLogsHeading +" doesn't match the actual heading --- "+logHeader);
////		js.executeScript("arguments[0].scrollIntoView(true);", OriginPages.closeButton(driver));
//		OriginPages.buttonText(driver, "Close").click();
//		System.out.println("Logs button verified");
//
//
//	} 	 
//
//		
//	//Lead edit verification
//				@Test
//				public void TC03_EditLeadVerification() throws IOException {
//					clickLOS(driver);
//					clickLeadAndSearch(driver);
//					parentWindow = driver.getWindowHandle();
//					XSSFSheet pMatLeadCreationSheet = excelUtils.getPMatLeadCreationSheet();		
//					JavascriptExecutor js = (JavascriptExecutor)driver;
//					OriginPages.leadQueueButton(driver,"Edit lead").click();
//					Set<String> editLeadTab = driver.getWindowHandles();
//					for(String child:editLeadTab) {
//						if(!parentWindow.equalsIgnoreCase(child)) {
//							driver.switchTo().window(child);
//							fieldClear(OriginPages.editableFields(driver, "Partner Loan App Id"),driver);
//							OriginPages.editableFields(driver, "Partner Loan App Id").sendKeys(pMatLeadCreationSheet.getRow(1).getCell(2).getStringCellValue());
//							fieldClear(OriginPages.editableFields(driver, "Partner Borrower Id"),driver);
//							OriginPages.editableFields(driver, "Partner Borrower Id").sendKeys(pMatLeadCreationSheet.getRow(2).getCell(2).getStringCellValue());
//							OriginPages.h2HeaderClick(driver, "Personal Details").click();
//							fieldClear(OriginPages.editableFields(driver, "First Name"),driver);
//							OriginPages.editableFields(driver, "First Name").sendKeys(pMatLeadCreationSheet.getRow(3).getCell(2).getStringCellValue());
//							fieldClear(OriginPages.editableFields(driver, "Last Name"),driver);
//							OriginPages.editableFields(driver, "Last Name").sendKeys(pMatLeadCreationSheet.getRow(4).getCell(2).getStringCellValue());
//							fieldClear(OriginPages.editableFields(driver, "Type Of Addr."),driver);
//							OriginPages.editableFields(driver, "Type Of Addr.").sendKeys(pMatLeadCreationSheet.getRow(5).getCell(2).getStringCellValue());
//							fieldClear(OriginPages.editableFields(driver, "Resident Addr. Line 1"),driver);
//							OriginPages.editableFields(driver, "Resident Addr. Line 1").sendKeys(pMatLeadCreationSheet.getRow(6).getCell(2).getStringCellValue());
//							OriginPages.dropDownButton(driver, "2").click();
//					    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//							List<WebElement> statenames = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[1]/ul[1]/li")); 
//							System.out.println("Number of states : "+statenames.size());
//							for(WebElement stnames : statenames) {
//								if(stnames.getText().equals("Bihar")) {
//									stnames.click();
//									break;
//								}
//							}
//							js.executeScript("arguments[0].click();", OriginPages.cityNameTextbox(driver));
//							OriginPages.cityNameTextbox(driver).sendKeys(pMatLeadCreationSheet.getRow(8).getCell(2).getStringCellValue());
//					    	js.executeScript("arguments[0].scrollIntoView(true);", OriginPages.editCityName(driver));
//							js.executeScript("arguments[0].click();", OriginPages.editCityName(driver));
//							fieldClear(OriginPages.editableFields(driver, "Pincode"),driver);
//							OriginPages.editableFields(driver, "Pincode").sendKeys(pMatLeadCreationSheet.getRow(9).getCell(2).getStringCellValue());
//							fieldClear(OriginPages.editableFields(driver, "Date Of Birth"),driver);
//							OriginPages.editableFields(driver, "Date Of Birth").sendKeys(pMatLeadCreationSheet.getRow(12).getCell(2).getStringCellValue());
//							fieldClear(OriginPages.editableFields(driver, "Gender"),driver);
//							OriginPages.editableFields(driver, "Gender").sendKeys(pMatLeadCreationSheet.getRow(13).getCell(2).getStringCellValue());
//
//							OriginPages.h2HeaderClick(driver, "Contact Details").click();
//							double contactN = pMatLeadCreationSheet.getRow(10).getCell(2).getNumericCellValue();
//							int cont = (int) contactN;
//							String c = Integer.toString(cont);
//							fieldClear(OriginPages.editableFields(driver, "Contact No."),driver);
//							OriginPages.editableFields(driver, "Contact No.").sendKeys(c);
//
//							OriginPages.h2HeaderClick(driver, "KYC Details").click();
//							fieldClear(OriginPages.editableFields(driver, "Pan No."),driver);
//							OriginPages.editableFields(driver, "Pan No.").sendKeys(pMatLeadCreationSheet.getRow(11).getCell(2).getStringCellValue());
//							OriginPages.h2HeaderClick(driver, "Financial Details").click();
//							fieldClear(OriginPages.editableFields(driver, "Bank Name"),driver);
//							OriginPages.editableFields(driver, "Bank Name").sendKeys(pMatLeadCreationSheet.getRow(14).getCell(2).getStringCellValue());
//							fieldClear(OriginPages.editableFields(driver, "Bank Acc. No."),driver);
//							OriginPages.editableFields(driver, "Bank Acc. No.").sendKeys(pMatLeadCreationSheet.getRow(15).getCell(2).getStringCellValue());
//							fieldClear(OriginPages.editableFields(driver, "Bank IFSC Code"),driver);
//							OriginPages.editableFields(driver, "Bank IFSC Code").sendKeys(pMatLeadCreationSheet.getRow(16).getCell(2).getStringCellValue());
//
//							OriginPages.submit(driver).click();
////					    	driver.close();
//					    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//
//						}
//					}
//				} 
//	
//
//
//
//}
