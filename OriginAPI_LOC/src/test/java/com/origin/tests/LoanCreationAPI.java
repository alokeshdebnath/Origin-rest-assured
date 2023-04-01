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

public class LoanCreationAPI {
	String responseLoanIDValue="";
	XSSFSheet processSheet = null;
	XSSFSheet loanResponseSheet = null;
	XSSFSheet apiSheet = null;

	HashMap <String,String> responseMap = new HashMap<>();

	public HashMap<String, String> getResponseMap() {
		return responseMap;
	}
 
	@Test
	public void createLoan(HashMap<String, String> requestMap,XSSFSheet apiSheet,XSSFSheet leadResponseSheet,XSSFSheet processSheet ) throws IOException {

		System.out.println("-----------------------------------------------inside create loan-----------------------------------------------------------------------");
		processSheet = excelUtils.getLoanCreationSheet();
		apiSheet = excelUtils.getAPISheet();
		loanResponseSheet = excelUtils.getLoanResponseSheet();


		RestAssured.baseURI=""+apiSheet.getRow(1).getCell(0).getStringCellValue()+"";
		DataFormatter formatter = new DataFormatter();
		int colNum = processSheet.getRow(1).getLastCellNum();
		int rowCount = processSheet.getPhysicalNumberOfRows();
		System.out.println("Loan sheet Row count ----- "+rowCount);
		System.out.println("Loan sheet Column count ------ "+colNum);



		//loan request
		StringJoiner sj = new StringJoiner(",","[{", "}]");

		for(int j=1;j<processSheet.getRow(1).getLastCellNum();j++) {

			if(formatter.formatCellValue(processSheet.getRow(1).getCell(j)).equals("placeholder")){
				if(requestMap.containsKey(processSheet.getRow(0).getCell(j).getStringCellValue())){
					sj.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+requestMap.get(processSheet.getRow(0).getCell(j).getStringCellValue())+"\"" );
				}
				else {
					System.err.println("Response id missing");
					return;
				}			
			}
			else {
				String value = formatter.formatCellValue(processSheet.getRow(1).getCell(j));
				if (value != null && !value.isEmpty()) {
					sj.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+value+"\"" );
				}
			}
		}
		
		
		String payload =  sj.toString();


		System.out.println("----------------------------------------Loan Creation-----------------------------------------------------");		
		System.out.println(payload);

		String method = ""+apiSheet.getRow(1).getCell(4).getStringCellValue()+"";                                    // POST/PUT/GET method type
		switch(method) {
		case "POST": 
			
			//loan response
			Response response = given()
			.contentType("application/json")
			.header(""+apiSheet.getRow(0).getCell(1).getStringCellValue()+"",""+apiSheet.getRow(1).getCell(1).getStringCellValue()+"")            //header - authorization
			.body(payload).log().all()
			.when()
			.post(""+apiSheet.getRow(2).getCell(2).getStringCellValue()+"")                         // URI Extension
			.then()
			.extract()
			.response();
			System.out.println("----------------------------------------------Loan response ------------------------------------------------------------");
//			System.out.println(response.jsonPath().get("message").toString());
			System.out.println("Response: " + response.asPrettyString());
			int statusCode = response.getStatusCode();
			System.out.println("Status code : - " +statusCode);


			for(int j=0;j<loanResponseSheet.getRow(0).getLastCellNum();j++) {
				responseMap.put(loanResponseSheet.getRow(0).getCell(j).getStringCellValue(), response.jsonPath().getString(formatter.formatCellValue(loanResponseSheet.getRow(1).getCell(j))));
			}
			
			
			String actualMsg = responseMap.get(loanResponseSheet.getRow(1).getCell(1).getStringCellValue());
			String expectedMsg = loanResponseSheet.getRow(2).getCell(1).getStringCellValue();

			if(expectedMsg.equals(actualMsg)) {
				System.out.println("PASS - Loan details added succesfully!!");
			}
			else
				System.err.println("FAIL - Loan details not added!!!");
			System.out.println("LOAN RESPONSE MAP ========== "+responseMap.toString());


			responseLoanIDValue = response.jsonPath().getString("data[0].loan_id");

			System.out.println("---------------------------------------end of create loan------------------------------------------------------");
		}

	}

	public String getResLoanIDValue() {
		return responseLoanIDValue;
	}


}
