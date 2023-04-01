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
public class RepaymentAPI extends ExtentReportListener{  

	XSSFSheet apiSheet = null;
	XSSFSheet repaymentSheet = null;
	XSSFSheet repaymentResponseSheet = null;

	HashMap <String,String> responseMap = new HashMap<>();

	public HashMap<String, String> getResponseMap() {
		return responseMap;
	}

	@Test
	public void repayment(HashMap<String, String> requestMap,HashMap<String, String> requestMap2,XSSFSheet apiSheet,XSSFSheet loanResponseSheet,XSSFSheet processSheet) throws IOException {

		System.out.println("----------------------------------------inside repayment ----------------------------------------------");
		apiSheet = excelUtils.getAPISheet();
		processSheet = excelUtils.getRepaymentSheet();
		repaymentResponseSheet = excelUtils.getRepaymentResponseSheet();


		DataFormatter formatter = new DataFormatter();

		RestAssured.baseURI=""+apiSheet.getRow(10).getCell(0).getStringCellValue()+"";  //base URI

		// repayment request
		StringJoiner sj = new StringJoiner(",","[{", "}]");
		for(int j=1;j<processSheet.getRow(0).getLastCellNum();j++) {

			if(formatter.formatCellValue(processSheet.getRow(1).getCell(j)).equals("placeholder")){
				if(requestMap.containsKey(processSheet.getRow(0).getCell(j).getStringCellValue())){
					sj.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+requestMap.get(processSheet.getRow(0).getCell(j).getStringCellValue())+"\"" );
				}
				else {
					System.err.println(processSheet.getRow(0).getCell(j).getStringCellValue() + " is missing");
					return;
				}			
			} else if(formatter.formatCellValue(processSheet.getRow(1).getCell(j)).equals("placeholder2")){
				if(requestMap2.containsKey(processSheet.getRow(0).getCell(j).getStringCellValue())){
					sj.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+requestMap2.get(processSheet.getRow(0).getCell(j).getStringCellValue())+"\"" );
				}
				else {
					System.err.println(processSheet.getRow(0).getCell(j).getStringCellValue() + " is missing");
					return;
				}			
			} else {
				sj.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+formatter.formatCellValue(processSheet.getRow(1).getCell(j))+"\"" );
			}
		}
		String payload =sj.toString();

		String method = ""+apiSheet.getRow(10).getCell(4).getStringCellValue()+"";    // POST/PUT/GET method type
		switch(method) {
		case "POST": 
			
			//repayment response
			Response response = given()
			.contentType("application/json")
			.header(""+apiSheet.getRow(0).getCell(1).getStringCellValue()+"",""+apiSheet.getRow(10).getCell(1).getStringCellValue()+"")        //header - authorization
			.body(payload).log().all()
			.when()
			.post(""+apiSheet.getRow(10).getCell(2).getStringCellValue()+"")                    // URI Extension
			.then()
			.extract()
			.response();
			System.out.println("----------------------------------------repayment response ------------------------------------------------");
			System.out.println("Response: " + response.asPrettyString());

			for(int j=0;j<repaymentResponseSheet.getRow(0).getLastCellNum();j++) {
				responseMap.put(repaymentResponseSheet.getRow(0).getCell(j).getStringCellValue(), response.jsonPath().getString(formatter.formatCellValue(repaymentResponseSheet.getRow(1).getCell(j))));
			}
			System.out.println(responseMap.toString());

			String actualMsg = responseMap.get(repaymentResponseSheet.getRow(0).getCell(1).getStringCellValue());
			String expectedMsg = repaymentResponseSheet.getRow(2).getCell(1).getStringCellValue();

			if(expectedMsg.equals(actualMsg)) {
				System.out.println("PASS - Successfully inserted 1 records in loan  transaction ledger!");
			}
			else
				System.err.println("FAIL - Could not insert record in loan  transaction ledger!!");


			System.out.println("---------------------------------------------end of repayment--------------------------------------------------------");
			break;
		}
	}

}



