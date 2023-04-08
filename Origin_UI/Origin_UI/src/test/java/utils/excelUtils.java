package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtils { 
	
		static String projectpath;
		static XSSFWorkbook wb;
		static XSSFSheet finsaalLeadCreationSheet;
		static XSSFSheet pMatLeadCreationSheet = null;
		static XSSFSheet pMatLoanCreationSheet = null;
		static XSSFWorkbook wb2 = null;
	

	public static void initializeExcel() throws IOException {
		String projectpath = System.getProperty("user.dir");
		File src = new File (projectpath+"/TestData/Lead Creation.xlsx");
		FileInputStream fis = new FileInputStream(src);
		File src2 = new File (projectpath+"/TestData/Loan Creation.xlsx");
		FileInputStream fis2 = new FileInputStream(src2);
		wb2 = new XSSFWorkbook(fis2);
		wb = new XSSFWorkbook(fis);
		finsaalLeadCreationSheet = wb.getSheet("Finsall Resources");
		pMatLeadCreationSheet = wb.getSheet("PMat");
		pMatLoanCreationSheet = wb2.getSheet("PMat-PMatInFort");

		XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
		XSSFFormulaEvaluator.evaluateAllFormulaCells(wb2);

	}
	
	public static XSSFSheet getPMatLeadCreationSheet() throws IOException {
		if (pMatLeadCreationSheet == null) {
			initializeExcel();
		}
		return pMatLeadCreationSheet;
	}

	public static XSSFSheet getPMatLoanCreationSheet() throws IOException {
		if (pMatLoanCreationSheet == null) {
			initializeExcel();
		}
		return pMatLoanCreationSheet;
	}
	
}
