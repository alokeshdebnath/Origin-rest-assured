package com.origin.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.StringJoiner;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.Test;

import com.origin.utils.excelUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SetCreditLimitAPI {
	XSSFSheet loanDocSheet = null;
	XSSFSheet apiSheet = null;
	XSSFSheet creditLimitResponseSheet = null;
	public HashMap<String, String> headers = new HashMap<String,String>();


	HashMap <String,String> responseMap = new HashMap<>();

	public HashMap<String, String> getResponseMap() {
		return responseMap;
	}


	@Test
	public void setCreditLimit(HashMap<String, String> requestMap,XSSFSheet apiSheet,XSSFSheet loanResponseSheet,XSSFSheet processSheet) throws IOException {
		System.out.println("-----------------Inside Set credit limit -------------------------------------------------");
		apiSheet = excelUtils.getAPISheet();
		processSheet = excelUtils.getCreditLimitSheet();
		creditLimitResponseSheet = excelUtils.getCreditLimitResponseSheet();

		RestAssured.baseURI=""+apiSheet.getRow(4).getCell(0).getStringCellValue()+"";
		DataFormatter formatter = new DataFormatter();


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
			}
			else {
				sj.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+formatter.formatCellValue(processSheet.getRow(1).getCell(j))+"\"" );
			}
		}

		String payload =  sj.toString();

		System.out.println("----------------------------Set Credit Limit payload----------------------------------------------");		
		System.out.println("Payload   === "+payload);

		String method = ""+apiSheet.getRow(1).getCell(4).getStringCellValue()+"";                 // POST/PUT/GET method type
		switch(method) {
		case "POST": 
			
//			headers.post(""+apiSheet.getRow(0).getCell(1).getStringCellValue()+"",""+apiSheet.getRow(4).getCell(1).getStringCellValue()+"")  ;   //header - authorization

			
			//set credit limit response
			headers.put(""+apiSheet.getRow(0).getCell(1).getStringCellValue()+"",""+apiSheet.getRow(4).getCell(1).getStringCellValue()+"");
			headers.put(""+apiSheet.getRow(0).getCell(3).getStringCellValue()+"",""+apiSheet.getRow(4).getCell(3).getStringCellValue()+"");
			Response response = given()
			.contentType("application/json")
			.headers(headers)     //header - authorization
//			.header(""+apiSheet.getRow(0).getCell(3).getStringCellValue()+"",""+apiSheet.getRow(4).getCell(3).getStringCellValue()+"")	   //header - company_code
			.body(payload).log().all()
			.when()
			.post(""+apiSheet.getRow(4).getCell(2).getStringCellValue()+"")      // URI Extension
			.then()
			.extract()
			.response();
			System.out.println("--------------------------Set credit limit response ------------------------------------------------");
			System.out.println("Response: " + response.asPrettyString());
			int statusCode = response.getStatusCode();
			System.out.println("Status code : - " +statusCode);

	

			for(int j=0;j<creditLimitResponseSheet.getRow(0).getLastCellNum();j++) {
				responseMap.put(creditLimitResponseSheet.getRow(0).getCell(j).getStringCellValue(), response.jsonPath().getString(formatter.formatCellValue(creditLimitResponseSheet.getRow(1).getCell(j))));
			}
			String actualMsg = responseMap.get(creditLimitResponseSheet.getRow(0).getCell(1).getStringCellValue());
			String expectedMsg = creditLimitResponseSheet.getRow(2).getCell(1).getStringCellValue();
			System.out.println("Actual msg = "+actualMsg);
			System.out.println("Expected msg = "+expectedMsg);

			if(expectedMsg.equals(actualMsg)) {
				System.out.println("PASS - credit limit set successfully");
			}
			else
				System.err.println("FAIL - credit limit  NOT set !!!");
	



			System.out.println("---------------------------end of Set credit limit---------------------------------------------------");
		}
	}
}
