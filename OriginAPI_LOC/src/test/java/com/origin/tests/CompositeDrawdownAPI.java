package com.origin.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.StringJoiner;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.origin.reports.ExtentReportListener;
import com.origin.utils.excelUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@Listeners(ExtentReportListener.class)
public class CompositeDrawdownAPI extends ExtentReportListener{

	XSSFSheet apiSheet = null;
	XSSFSheet compositeDisbSheet = null;
	XSSFSheet compDrwdwnResponseSheet = null;
	HashMap <String,String> responseMap = new HashMap<>();


	public HashMap<String, String> getResponseMap() {
		return responseMap;
	}
	@Test
	public void compositeDrawdown(HashMap<String, String> requestMap,XSSFSheet apiSheet,XSSFSheet loanResponseSheet,XSSFSheet processSheet) throws IOException {

		System.out.println("----------------------------------------inside composite drawdown ----------------------------------------------");
		apiSheet = excelUtils.getAPISheet();
		processSheet = excelUtils.getCompositeDrwdwnSheet();
		compDrwdwnResponseSheet = excelUtils.getCompositeDrwdwnResponseSheet();
		
		int colNum = processSheet.getRow(1).getLastCellNum();
		int rowCount = processSheet.getPhysicalNumberOfRows();
		System.out.println("comp drawdown sheet Row count ----- "+rowCount);
		System.out.println("comp drawdown sheet Column count ------ "+colNum);


		DataFormatter formatter = new DataFormatter();

		RestAssured.baseURI=""+apiSheet.getRow(1).getCell(0).getStringCellValue()+"";
		//composite drawdown request
		StringJoiner sj = new StringJoiner(",","{", ",");
		// loop till last cell minus 2 excluding no_of_emi and repayment_days which is numeric format
		for(int j=1;j<processSheet.getRow(0).getLastCellNum()-2;j++) {

			if(formatter.formatCellValue(processSheet.getRow(1).getCell(j)).equals("placeholder")){
				if(requestMap.containsKey(processSheet.getRow(0).getCell(j).getStringCellValue())){
					sj.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+requestMap.get(processSheet.getRow(0).getCell(j).getStringCellValue())+"\"" );
				}
				else {
					System.err.println(processSheet.getRow(0).getCell(j).getStringCellValue() + " is missing");
					return;
				}			
			}
			else {
				sj.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+formatter.formatCellValue(processSheet.getRow(1).getCell(j))+"\"" );
			}
		}
		StringJoiner sj1 = new StringJoiner(",","", "}");

		// loop for last two columns - no_of_emi and repayment_days which is numeric format 
		for (int k = processSheet.getRow(0).getLastCellNum()-2;k<processSheet.getRow(0).getLastCellNum();k++){

			if(formatter.formatCellValue(processSheet.getRow(1).getCell(k)).equals("placeholder")){
				if(requestMap.containsKey(processSheet.getRow(0).getCell(k).getStringCellValue())){
					sj1.add("\""+processSheet.getRow(0).getCell(k).getStringCellValue()+"\": "+requestMap.get(processSheet.getRow(0).getCell(k).getStringCellValue())+"" );
				}
				else {
					System.err.println(processSheet.getRow(0).getCell(k).getStringCellValue() + " is missing");
					return;
				}			
			}
			else {
				sj1.add("\""+processSheet.getRow(0).getCell(k).getStringCellValue()+"\": "+formatter.formatCellValue(processSheet.getRow(1).getCell(k))+"" );
			}
			
		}
		String payload =sj.toString()+sj1.toString();



		String method = ""+apiSheet.getRow(6).getCell(4).getStringCellValue()+"";    // POST/PUT/GET method type
		switch(method) {
		case "POST": 
			
			//comp drawdown response
			Response response = given()
			.contentType("application/json")
			.header(""+apiSheet.getRow(0).getCell(1).getStringCellValue()+"",""+apiSheet.getRow(6).getCell(1).getStringCellValue()+"")           //header - authorization
			.body(payload).log().all()
			.when()
			.post(""+apiSheet.getRow(6).getCell(2).getStringCellValue()+"")                       // URI Extension
			.then()
			.extract()
			.response();
			System.out.println("----------------------------------------composite drawdown response ------------------------------------------------");
			System.out.println("Response: " + response.asPrettyString());
			int statusCode = response.getStatusCode();
			System.out.println("Status code : - " +statusCode);


			for(int j=0;j<compDrwdwnResponseSheet.getRow(0).getLastCellNum();j++) {
				responseMap.put(compDrwdwnResponseSheet.getRow(0).getCell(j).getStringCellValue(), response.jsonPath().getString(formatter.formatCellValue(compDrwdwnResponseSheet.getRow(1).getCell(j))));
			}

			String actualMsg = responseMap.get(compDrwdwnResponseSheet.getRow(0).getCell(9).getStringCellValue());
			String expectedMsg = compDrwdwnResponseSheet.getRow(2).getCell(9).getStringCellValue();

			if(expectedMsg.equals(actualMsg)) {
				System.out.println("PASS - Successfully saved for disbursement!");
			}
			else
				System.err.println("FAIL - Not saved for disbursement!!");
			System.out.println(responseMap.toString());

			System.out.println("---------------------------------------------end of composite drawdown--------------------------------------------------------");
			break;
		}
	}

}



