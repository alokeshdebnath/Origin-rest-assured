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
public class CompositeDisbursementAPI extends ExtentReportListener{  

	XSSFSheet apiSheet = null;
	XSSFSheet compositeDisbSheet = null;
	XSSFSheet compDisbResponseSheet = null;
	HashMap <String,String> responseMap = new HashMap<>();


	public HashMap<String, String> getResponseMap() {
		return responseMap;
	}
	@Test
	public void compositeDisbursement(HashMap<String, String> requestMap,XSSFSheet apiSheet,XSSFSheet loanResponseSheet,XSSFSheet processSheet) throws IOException {

		System.out.println("----------------------------------------inside composite disbursement ----------------------------------------------");
		apiSheet = excelUtils.getAPISheet();
		processSheet = excelUtils.getCompDisbSheet();
		compDisbResponseSheet = excelUtils.getCompDisbResponseSheet();


		DataFormatter formatter = new DataFormatter();

		RestAssured.baseURI=""+apiSheet.getRow(1).getCell(0).getStringCellValue()+"";
		//composite disbursment request
		StringJoiner sj = new StringJoiner(",","{", "}");
		for(int j=1;j<processSheet.getRow(0).getLastCellNum();j++) {

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
		String payload =sj.toString();



		String method = ""+apiSheet.getRow(5).getCell(3).getStringCellValue()+"";    // POST/PUT/GET method type
		switch(method) {
		case "POST": 
			
			//comp disb response
			Response response = given()
			.contentType("application/json")
			.header(""+apiSheet.getRow(0).getCell(1).getStringCellValue()+"",""+apiSheet.getRow(11).getCell(1).getStringCellValue()+"")           //header - authorization
			.body(payload).log().all()
			.when()
			.post(""+apiSheet.getRow(5).getCell(2).getStringCellValue()+"")                       // URI Extension
			.then()
			.extract()
			.response();
			System.out.println("----------------------------------------composite disbursement response ------------------------------------------------");
			System.out.println("Response: " + response.asPrettyString());


			for(int j=0;j<compDisbResponseSheet.getRow(0).getLastCellNum();j++) {
				responseMap.put(compDisbResponseSheet.getRow(0).getCell(j).getStringCellValue(), response.jsonPath().getString(formatter.formatCellValue(compDisbResponseSheet.getRow(1).getCell(j))));
			}
			String actualMsg = responseMap.get(compDisbResponseSheet.getRow(0).getCell(6).getStringCellValue());
			String expectedMsg = compDisbResponseSheet.getRow(2).getCell(6).getStringCellValue();

			if(expectedMsg.equals(actualMsg)) {
				System.out.println("PASS - Successfully saved for disbursement!");
			}
			else
				System.err.println("FAIL - Not saved for disbursement!!");
			System.out.println(responseMap.toString());


			System.out.println("---------------------------------------------end of composite disbursement--------------------------------------------------------");
			break;
		}
	}

}



