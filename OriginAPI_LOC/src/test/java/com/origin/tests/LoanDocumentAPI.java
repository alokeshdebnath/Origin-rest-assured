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

public class LoanDocumentAPI {
	XSSFSheet loanDocSheet = null;
	XSSFSheet apiSheet = null;
	XSSFSheet loanDocResponseSheet = null;


	HashMap <String,String> responseMap = new HashMap<>();

	public HashMap<String, String> getResponseMap() {
		return responseMap;
	}


	@Test 
	public void createLoanDocument(HashMap<String, String> requestMap,XSSFSheet apiSheet,XSSFSheet loanResponseSheet,XSSFSheet processSheet) throws IOException {
		System.out.println("-----------------inside create loan document-------------------------------------------------");
		apiSheet = excelUtils.getAPISheet();
		processSheet = excelUtils.getLoanDocSheet();
		loanDocResponseSheet = excelUtils.getLoanDocResponseSheet();

		RestAssured.baseURI=""+apiSheet.getRow(3).getCell(0).getStringCellValue()+"";
		DataFormatter formatter = new DataFormatter();


		StringJoiner sj = new StringJoiner(",","{", "}"); 
		for(int j=1;j<processSheet.getRow(0).getLastCellNum();j++) {

//			if(processSheet.getRow(1).getCell(j).getCellType()== CellType.BLANK){
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

		//loan doc request
	/*	StringJoiner sj2 = new StringJoiner(",","{", "}");
		for(int j=1;j<processSheet.getRow(0).getLastCellNum();j++) {

			if(formatter.formatCellValue(processSheet.getRow(2).getCell(j)).equals("placeholder")){
				if(requestMap.containsKey(processSheet.getRow(0).getCell(j).getStringCellValue())){
					sj2.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+requestMap.get(processSheet.getRow(0).getCell(j).getStringCellValue())+"\"" );
				}
				else {
					System.err.println(processSheet.getRow(0).getCell(j).getStringCellValue() + " is missing");
					return;
				}			
			}
			else {
				sj2.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+formatter.formatCellValue(processSheet.getRow(2).getCell(j))+"\"" );
			}
		}

		String payload2 =  sj2.toString(); */


		System.out.println("----------------------------Loan  Document  Creation----------------------------------------------");		
		System.out.println("Payload 1  === "+payload);
//		System.out.println("===================================================================================================================");
//		System.out.println("Payload 2  === "+payload2);


		String method = ""+apiSheet.getRow(1).getCell(4).getStringCellValue()+"";                    // POST/PUT/GET method type
		switch(method) {
		case "POST": 
			
			//loan doc response
			Response response = given()
			.contentType("application/json")
			.header(""+apiSheet.getRow(0).getCell(1).getStringCellValue()+"",""+apiSheet.getRow(3).getCell(1).getStringCellValue()+"")     //header - authorization
			.body(payload).log().all()
			.when()
			.post(""+apiSheet.getRow(3).getCell(2).getStringCellValue()+"")      // URI Extension
			.then()
			.extract()
			.response();
			System.out.println("--------------------------Loan document response ------------------------------------------------");
			System.out.println("Response: " + response.asPrettyString());
			int statusCode = response.getStatusCode();
			System.out.println("Status code : - " +statusCode);

		/*	Response response2 = given()
					.contentType("application/json")
					.header(""+apiSheet.getRow(0).getCell(1).getStringCellValue()+"",""+apiSheet.getRow(9).getCell(1).getStringCellValue()+"")     //header - authorization
					.body(payload2).log().all()
					.when()
					.post(""+apiSheet.getRow(3).getCell(2).getStringCellValue()+"")      // URI Extension
					.then()
					.extract()
					.response();
			System.out.println("--------------------------Loan document response ------------------------------------------------");
			System.out.println("Response: " + response2.asPrettyString()); */


			for(int j=0;j<loanDocResponseSheet.getRow(0).getLastCellNum();j++) {
				responseMap.put(loanDocResponseSheet.getRow(0).getCell(j).getStringCellValue(), response.jsonPath().getString(formatter.formatCellValue(loanDocResponseSheet.getRow(1).getCell(j))));
			}
			String actualMsg = responseMap.get(loanDocResponseSheet.getRow(0).getCell(1).getStringCellValue());
			String expectedMsg = loanDocResponseSheet.getRow(2).getCell(1).getStringCellValue();
			System.out.println("Actual msg = "+actualMsg);
			System.out.println("Expected msg = "+expectedMsg);

			if(expectedMsg.equals(actualMsg)) {
				System.out.println("PASS - Loan Document uploaded succesfully!!");
			}
			else
				System.err.println("FAIL - Loan Document could not be added!!!");
	



			System.out.println("---------------------------end of loan document---------------------------------------------------");
		}
	}
}
