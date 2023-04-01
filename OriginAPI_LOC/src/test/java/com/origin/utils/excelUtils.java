package com.origin.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtils {

	static String projectpath;
	static XSSFWorkbook wb;
	static XSSFWorkbook wb2;
	static XSSFWorkbook wb3;
	static XSSFWorkbook wb4;
	static XSSFWorkbook wb5;
	static XSSFWorkbook wb6;
	static XSSFWorkbook wb7;
	static XSSFWorkbook wb8;
	static XSSFWorkbook wb9;
	static XSSFWorkbook wb10;
	static XSSFWorkbook wb11;
	static XSSFWorkbook wb12;
	static XSSFWorkbook wb13;
	static XSSFWorkbook wb14;
	static XSSFWorkbook wb15;
	static XSSFWorkbook wb16;
	static XSSFWorkbook wb17;
	static XSSFWorkbook wb18;
	static XSSFWorkbook wb19;
	static XSSFWorkbook wb20;
	static XSSFWorkbook wb21;
	static XSSFWorkbook wb22;
	static XSSFWorkbook wb23;
	static XSSFWorkbook wb24;
	static XSSFWorkbook wb25;



	static XSSFSheet leadCreationSheet;
	static XSSFSheet loanCreationSheet;
	static XSSFSheet loanDocSheet;
	static XSSFSheet apiSheet;
	static XSSFSheet statusChangeSheet;
	static XSSFSheet compositeDisbSheet;
	static XSSFSheet wireoutSheet;
	static XSSFSheet repaymentSheet;
	static XSSFSheet compDisbResponseSheet;
	static XSSFSheet leadResponseSheet;
	static XSSFSheet loanResponseSheet;
	static XSSFSheet loanDocResponseSheet;	
	static XSSFSheet statusChangeResponseSheet;	
	static XSSFSheet wireoutResponseSheet;
	static XSSFSheet repaymentResponseSheet;
	static XSSFSheet repaymentScheduleSheet;
	static XSSFSheet repaymentScheduleResponseSheet;
	static XSSFSheet patchSheet;
	static XSSFSheet patchResponseSheet;
	static XSSFSheet creditLimitSheet;
	static XSSFSheet creditLimitResponseSheet;	
	static XSSFSheet compositeDrawdownSheet;
	static XSSFSheet compositeDrawdownResponseSheet;
	static XSSFSheet drawDownSheet;
	static XSSFSheet drawDownResponseSheet;



	public static void initializeExcel() throws IOException {

		String projectpath = System.getProperty("user.dir");
		File src = new File (projectpath+"/Test Data/API Data.xlsx");
		FileInputStream fis = new FileInputStream(src);
		File src2 = new File (projectpath+"/Test Data/Lead Data.xlsx");
		FileInputStream fis2 = new FileInputStream(src2);
		File src3 = new File (projectpath+"/Test Data/Loan Data.xlsx"); 
		FileInputStream fis3 = new FileInputStream(src3);
		File src4 = new File (projectpath+"/Test Data/Loan Doc Data.xlsx");
		FileInputStream fis4 = new FileInputStream(src4);
		File src5 = new File (projectpath+"/Test Data/Status Change Data.xlsx");
		FileInputStream fis5 = new FileInputStream(src5);
		File src6 = new File (projectpath+"/Test Data/Composite Disbursement.xlsx");
		FileInputStream fis6 = new FileInputStream(src6);
		File src7 = new File (projectpath+"/Test Data/Wireout Notification.xlsx");
		FileInputStream fis7 = new FileInputStream(src7);
		File src8 = new File (projectpath+"/Test Data/Repayment.xlsx");
		FileInputStream fis8 = new FileInputStream(src8);
		File src9 = new File (projectpath+"/Test Data/Composite Disbursement Response.xlsx");
		FileInputStream fis9 = new FileInputStream(src9);
		File src10 = new File (projectpath+"/Test Data/Lead Response.xlsx");
		FileInputStream fis10 = new FileInputStream(src10);
		File src11 = new File (projectpath+"/Test Data/Loan Response.xlsx");
		FileInputStream fis11 = new FileInputStream(src11);
		File src12 = new File (projectpath+"/Test Data/Loan Doc Response.xlsx");
		FileInputStream fis12 = new FileInputStream(src12);
		File src13 = new File (projectpath+"/Test Data/Status Change Response.xlsx");
		FileInputStream fis13 = new FileInputStream(src13);
		File src14 = new File (projectpath+"/Test Data/Wireout Notification Response.xlsx");
		FileInputStream fis14 = new FileInputStream(src14);
		File src15 = new File (projectpath+"/Test Data/Repayment Response.xlsx");
		FileInputStream fis15 = new FileInputStream(src15);
		File src16 = new File (projectpath+"/Test Data/Repayment Schedule.xlsx");
		FileInputStream fis16 = new FileInputStream(src16);
		File src17 = new File (projectpath+"/Test Data/Repayment Schedule Response.xlsx");
		FileInputStream fis17 = new FileInputStream(src17);
		File src18 = new File (projectpath+"/Test Data/Patch Data.xlsx");
		FileInputStream fis18 = new FileInputStream(src18);
		File src19 = new File (projectpath+"/Test Data/Patch Response.xlsx");
		FileInputStream fis19 = new FileInputStream(src19);
		File src20 = new File (projectpath+"/Test Data/Set Credit Limit Data.xlsx");
		FileInputStream fis20 = new FileInputStream(src20);
		File src21 = new File (projectpath+"/Test Data/Set Credit Limit Response.xlsx");
		FileInputStream fis21 = new FileInputStream(src21);
		File src22 = new File (projectpath+"/Test Data/Composite Drawdown Data.xlsx");
		FileInputStream fis22 = new FileInputStream(src22);
		File src23 = new File (projectpath+"/Test Data/Composite Drawdown Response.xlsx");
		FileInputStream fis23 = new FileInputStream(src23);
		File src24 = new File (projectpath+"/Test Data/Drawdown Request Data.xlsx");
		FileInputStream fis24 = new FileInputStream(src24);
		File src25 = new File (projectpath+"/Test Data/Drawdown Request Response.xlsx");
		FileInputStream fis25 = new FileInputStream(src25);
		
		wb = new XSSFWorkbook(fis);
		wb2 = new XSSFWorkbook(fis2);
		wb3 = new XSSFWorkbook(fis3);
		wb4 = new XSSFWorkbook(fis4);
		wb5 = new XSSFWorkbook(fis5);
		wb6 = new XSSFWorkbook(fis6);
		wb7 = new XSSFWorkbook(fis7);
		wb8 = new XSSFWorkbook(fis8);
		wb9 = new XSSFWorkbook(fis9);
		wb10 = new XSSFWorkbook(fis10);
		wb11 = new XSSFWorkbook(fis11);
		wb12 = new XSSFWorkbook(fis12);
		wb13 = new XSSFWorkbook(fis13);
		wb14 = new XSSFWorkbook(fis14);
		wb15 = new XSSFWorkbook(fis15);
		wb16 = new XSSFWorkbook(fis16);
		wb17 = new XSSFWorkbook(fis17);
		wb18 = new XSSFWorkbook(fis18);
		wb19 = new XSSFWorkbook(fis19);
		wb20 = new XSSFWorkbook(fis20);
		wb21 = new XSSFWorkbook(fis21);
		wb22 = new XSSFWorkbook(fis22);
		wb23 = new XSSFWorkbook(fis23);
		wb24 = new XSSFWorkbook(fis24);
		wb25 = new XSSFWorkbook(fis25);




		
		apiSheet = wb.getSheet("Headers");
		leadCreationSheet = wb2.getSheet("Lead");
		loanCreationSheet = wb3.getSheet("Loan");
		loanDocSheet = wb4.getSheet("Loan Doc");
		statusChangeSheet = wb5.getSheet("Status");
		compositeDisbSheet = wb6.getSheet("Composite Disbursement");
		wireoutSheet = wb7.getSheet("Wireout Noti");
		repaymentSheet = wb8.getSheet("Repayment");
		compDisbResponseSheet = wb9.getSheet("Response");
		leadResponseSheet = wb10.getSheet("Response");
		loanResponseSheet = wb11.getSheet("Response");
		loanDocResponseSheet = wb12.getSheet("Response");
		statusChangeResponseSheet = wb13.getSheet("Response");
		wireoutResponseSheet = wb14.getSheet("Response");
		repaymentResponseSheet = wb15.getSheet("Response");
		repaymentScheduleSheet = wb16.getSheet("Repayment Sch");
		repaymentScheduleResponseSheet = wb17.getSheet("Response");
		patchSheet = wb18.getSheet("Patch");
		patchResponseSheet = wb19.getSheet("Response");
		creditLimitSheet = wb20.getSheet("SetCreditLimit");
		creditLimitResponseSheet = wb21.getSheet("Response");
		compositeDrawdownSheet = wb22.getSheet("CompositeDrwDwn");
		compositeDrawdownResponseSheet = wb23.getSheet("Response");
		drawDownSheet = wb24.getSheet("DrwDwnReq");
		drawDownResponseSheet = wb25.getSheet("Response");



		XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
	}

	public static XSSFSheet getLeadCreationSheet() throws IOException {
		if (leadCreationSheet == null) {
			initializeExcel();
		}
		return leadCreationSheet;
	}
	public static XSSFSheet getLoanCreationSheet() throws IOException {
		if (loanCreationSheet == null) {
			initializeExcel();
		}
		return loanCreationSheet;
	}
	public static XSSFSheet getLoanDocSheet() throws IOException {
		if (loanDocSheet == null) {
			initializeExcel();
		}
		return loanDocSheet;
	}
	public static XSSFSheet getAPISheet() throws IOException {
		if (apiSheet == null) {
			initializeExcel();
		}
		return apiSheet;
	}
	public static XSSFSheet getStatusChangeSheet() throws IOException {
		if (statusChangeSheet == null) {
			initializeExcel();
		}
		return statusChangeSheet;
	}
	public static XSSFSheet getCompDisbSheet() throws IOException {
		if (compositeDisbSheet == null) {
			initializeExcel();
		}
		return compositeDisbSheet;
	}
	public static XSSFSheet getWireoutSheet() throws IOException {
		if (wireoutSheet == null) {
			initializeExcel();
		}
		return wireoutSheet;
	}
	public static XSSFSheet getRepaymentSheet() throws IOException {
		if (repaymentSheet == null) {
			initializeExcel();
		}
		return repaymentSheet;
	}
	public static XSSFSheet getCompDisbResponseSheet() throws IOException {
		if (compDisbResponseSheet == null) {
			initializeExcel();
		}
		return compDisbResponseSheet;
	}
	public static XSSFSheet getLeadResponseSheet() throws IOException {
		if (leadResponseSheet == null) {
			initializeExcel();
		}
		return leadResponseSheet;
	}
	public static XSSFSheet getLoanResponseSheet() throws IOException {
		if (loanResponseSheet == null) {
			initializeExcel();
		}
		return loanResponseSheet;
	}
	public static XSSFSheet getLoanDocResponseSheet() throws IOException {
		if (loanDocResponseSheet == null) {
			initializeExcel();
		}
		return loanDocResponseSheet;
	}
	public static XSSFSheet getStatusChngResponseSheet() throws IOException {
		if (statusChangeResponseSheet == null) {
			initializeExcel();
		}
		return statusChangeResponseSheet;
	}
	public static XSSFSheet getWireoutResponseSheet() throws IOException {
		if (wireoutResponseSheet == null) {
			initializeExcel();
		}
		return wireoutResponseSheet;
	}
	public static XSSFSheet getRepaymentResponseSheet() throws IOException {
		if (repaymentResponseSheet == null) {
			initializeExcel();
		}
		return repaymentResponseSheet;
	}
	public static XSSFSheet getRepaymentScheduleSheet() throws IOException {
		if (repaymentScheduleSheet == null) {
			initializeExcel();
		}
		return repaymentScheduleSheet;
	}
	public static XSSFSheet getRepaymentScheduleResponseSheet() throws IOException {
		if (repaymentScheduleResponseSheet == null) {
			initializeExcel();
		}
		return repaymentScheduleResponseSheet;
	}
	public static XSSFSheet getPatchSheet() throws IOException {
		if (patchSheet == null) {
			initializeExcel();
		}
		return patchSheet;
	}
	public static XSSFSheet getPatchResponseSheet() throws IOException {
		if (patchResponseSheet == null) {
			initializeExcel();
		}
		return patchResponseSheet;
	}
	public static XSSFSheet getCreditLimitSheet() throws IOException {
		if (creditLimitSheet == null) {
			initializeExcel();
		}
		return creditLimitSheet;
	}
	public static XSSFSheet getCreditLimitResponseSheet() throws IOException {
		if (creditLimitResponseSheet == null) {
			initializeExcel();
		}
		return creditLimitResponseSheet;
	}
	public static XSSFSheet getCompositeDrwdwnSheet() throws IOException {
		if (compositeDrawdownSheet == null) {
			initializeExcel();
		}
		return compositeDrawdownSheet;
	}
	public static XSSFSheet getCompositeDrwdwnResponseSheet() throws IOException {
		if (compositeDrawdownResponseSheet == null) {
			initializeExcel();
		}
		return compositeDrawdownResponseSheet;
	}
	public static XSSFSheet getDrawDownSheet() throws IOException {
		if (drawDownSheet == null) {
			initializeExcel();
		}
		return drawDownSheet;
	}
	public static XSSFSheet getDrawDownResponseSheet() throws IOException {
		if (drawDownResponseSheet == null) {
			initializeExcel();
		}
		return drawDownResponseSheet;
	}
	


}
