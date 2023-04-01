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
public class StatusChangeAPI extends ExtentReportListener{  


	XSSFSheet apiSheet = null;
	XSSFSheet statusChangeSheet = null;
	XSSFSheet statusChangeResponseSheet = null;
	HashMap <String,String> kyc_responseMap = new HashMap<>();
	HashMap <String,String> credit_responseMap = new HashMap<>();
//	HashMap <String,String> disbursal_responseMap = new HashMap<>();

	 
	public HashMap<String, String> getKycResponseMap() {
		return kyc_responseMap;
	}
	public HashMap<String, String> getCreditResponseMap() {
		return credit_responseMap;
	}
//	public HashMap<String, String> getDisbursalResponseMap() {
//		return disbursal_responseMap;
//	}

	@Test
	public void statusChange(HashMap<String, String> requestMap,XSSFSheet apiSheet,XSSFSheet loanResponseSheet,XSSFSheet processSheet,String responseLoanIDValue) throws IOException {

		System.out.println("----------------------------------------inside status change ----------------------------------------------");
		apiSheet = excelUtils.getAPISheet();
		processSheet = excelUtils.getStatusChangeSheet();
		statusChangeResponseSheet = excelUtils.getStatusChngResponseSheet();
		DataFormatter formatter = new DataFormatter();
		RestAssured.baseURI=""+apiSheet.getRow(1).getCell(0).getStringCellValue()+"";                 //base URI

		//kyc_approved payload
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
		String kyc_payload =  sj.toString();
		
		
		//credit_approved payload
		StringJoiner sj2 = new StringJoiner(",","{", "}");
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
		String credit_payload =  sj2.toString();
		
/*		//disbursal approved payload
		StringJoiner sj3 = new StringJoiner(",","{", "}");
		for(int j=1;j<processSheet.getRow(0).getLastCellNum();j++) {

			if(formatter.formatCellValue(processSheet.getRow(3).getCell(j)).equals("placeholder")){
				if(requestMap.containsKey(processSheet.getRow(0).getCell(j).getStringCellValue())){
					sj3.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+requestMap.get(processSheet.getRow(0).getCell(j).getStringCellValue())+"\"" );
				}
				else {
					System.err.println(processSheet.getRow(0).getCell(j).getStringCellValue() + " is missing");
					return;
				}			
			}
			else {
				sj3.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+formatter.formatCellValue(processSheet.getRow(3).getCell(j))+"\"" );
			}
		}
		String disbursal_payload =  sj3.toString(); */


		String method = ""+apiSheet.getRow(5).getCell(4).getStringCellValue()+"";    // POST/PUT/GET method type
		switch(method) {
		case "PUT":
			//kyc_approved response
			Response response = given()
			.contentType("application/json")
			.header(""+apiSheet.getRow(0).getCell(1).getStringCellValue()+"",""+apiSheet.getRow(1).getCell(1).getStringCellValue()+"")        //header - authorization
			.body(kyc_payload).log().all()
			.when()
			.put(""+apiSheet.getRow(5).getCell(2).getStringCellValue()+""+responseLoanIDValue)                    // URI Extension
			.then()
			.extract()
			.response();
			System.out.println("----------------------------------------KYC Data approved status change response ------------------------------------------------");
			System.out.println("Response: " + response.asPrettyString());
			System.out.println(response.jsonPath().get("message").toString());
			
			for(int j=0;j<statusChangeResponseSheet.getRow(0).getLastCellNum();j++) {
				kyc_responseMap.put(statusChangeResponseSheet.getRow(0).getCell(j).getStringCellValue(), response.jsonPath().getString(formatter.formatCellValue(statusChangeResponseSheet.getRow(1).getCell(j))));
			}
			String kyc_actualMsg = kyc_responseMap.get(statusChangeResponseSheet.getRow(1).getCell(0).getStringCellValue());
			String kyc_expectedMsg = statusChangeResponseSheet.getRow(2).getCell(0).getStringCellValue();

			
			if(kyc_expectedMsg.equals(kyc_actualMsg)) {
				System.out.println("PASS - Loan status updated to kyc_data_approved successfully!!");
			}
			else
				System.err.println("FAIL - Loan status NOT updated to kyc_data_approved!!!");

			// credit_approved response
			Response response2 = given()
					.contentType("application/json")
					.header(""+apiSheet.getRow(0).getCell(1).getStringCellValue()+"",""+apiSheet.getRow(1).getCell(1).getStringCellValue()+"")        //header - authorization
					.body(credit_payload).log().all()
					.when()
					.put(""+apiSheet.getRow(5).getCell(2).getStringCellValue()+""+responseLoanIDValue)                    // URI Extension
					.then()
					.extract()
					.response();
			System.out.println("----------------------------------------Credit approved status change response ------------------------------------------------");
			System.out.println("Response: " + response2.asPrettyString());
			System.out.println(response2.jsonPath().get("message").toString());
			int statusCode = response.getStatusCode();
			System.out.println("Status code : - " +statusCode);
			
			for(int j=0;j<statusChangeResponseSheet.getRow(0).getLastCellNum();j++) {
				credit_responseMap.put(statusChangeResponseSheet.getRow(0).getCell(j).getStringCellValue(), response2.jsonPath().getString(formatter.formatCellValue(statusChangeResponseSheet.getRow(1).getCell(j))));
			}
			String credit_actualMsg = credit_responseMap.get(statusChangeResponseSheet.getRow(1).getCell(0).getStringCellValue());
			String credit_expectedMsg = statusChangeResponseSheet.getRow(3).getCell(0).getStringCellValue();

			if(credit_expectedMsg.equals(credit_actualMsg)) {
				System.out.println("PASS - Loan status updated to credit_approved successfully!!");
			}
			else
				System.err.println("FAIL - Loan status NOT updated to credit_approved!!!");

			
/*			//disbursal_approved response
			Response response3 = given()
					.contentType("application/json")
					.header(""+apiSheet.getRow(0).getCell(1).getStringCellValue()+"",""+apiSheet.getRow(1).getCell(1).getStringCellValue()+"")        //header - authorization
					.body(disbursal_payload).log().all()
					.when()
					.put(""+apiSheet.getRow(4).getCell(2).getStringCellValue()+""+responseLoanIDValue)                    // URI Extension
					.then()
					.extract()
					.response();
			System.out.println("----------------------------------------Disbursal approved status change response ------------------------------------------------");
			System.out.println("Response: " + response3.asPrettyString());
			System.out.println(response3.jsonPath().get("message").toString());
			for(int j=0;j<statusChangeResponseSheet.getRow(0).getLastCellNum();j++) {
				disbursal_responseMap.put(statusChangeResponseSheet.getRow(0).getCell(j).getStringCellValue(), response3.jsonPath().getString(formatter.formatCellValue(statusChangeResponseSheet.getRow(1).getCell(j))));
			}
			String disbursal_actualMsg = disbursal_responseMap.get(statusChangeResponseSheet.getRow(1).getCell(0).getStringCellValue());
			String disbursal_expectedMsg = statusChangeResponseSheet.getRow(4).getCell(0).getStringCellValue();
			
			if(disbursal_expectedMsg.equals(disbursal_actualMsg)) {
				System.out.println("PASS - Loan status updated to disbursal_approved successfully!!");
			}
			else
				System.err.println("FAIL - Loan status NOT updated to disbursal_approved!!!");        */
			

			System.out.println("---------------------------------------------end of status change--------------------------------------------------------");
			break;
		}
	}
}



