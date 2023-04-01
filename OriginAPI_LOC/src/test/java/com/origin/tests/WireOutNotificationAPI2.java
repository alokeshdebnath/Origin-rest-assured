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
public class WireOutNotificationAPI2 extends ExtentReportListener{ 
	
	XSSFSheet apiSheet = null;
	XSSFSheet wireoutResponseSheet = null;

	
	HashMap <String,String> responseMap = new HashMap<>();
	
	public HashMap<String, String> getResponseMap() {
		return responseMap;
	}


	@Test
	public void wireoutNotification2 (HashMap<String, String> requestMap,XSSFSheet apiSheet,XSSFSheet requestSheet,XSSFSheet processSheet,String txn_id) throws IOException {

		System.out.println("----------------------------------------inside wireout notification 2------------------------------------------------");
		apiSheet = excelUtils.getAPISheet();
		processSheet = excelUtils.getWireoutSheet();
		wireoutResponseSheet = excelUtils.getWireoutResponseSheet();
		DataFormatter formatter = new DataFormatter();
		RestAssured.baseURI=""+apiSheet.getRow(8).getCell(0).getStringCellValue()+"";

		// wireout notification request
		StringJoiner sj = new StringJoiner(",","{", "}");
		for(int j=1;j<processSheet.getRow(0).getLastCellNum();j++) {

			if(formatter.formatCellValue(processSheet.getRow(1).getCell(j)).equals("placeholder")){
				sj.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+txn_id+"\"" );


/*				if(requestMap.containsKey(processSheet.getRow(0).getCell(j).getStringCellValue())){
					sj.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+txn_id+"\"" );
				} 
				else {
					System.err.println(processSheet.getRow(0).getCell(j).getStringCellValue() +" is missing");
					return;
				}	*/		
			}
			else {
				sj.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+formatter.formatCellValue(processSheet.getRow(1).getCell(j))+"\"" );
			}
		}

		String payload =  sj.toString();	
		System.out.println("Wireout payload 2 ===== "+payload);

		String method = ""+apiSheet.getRow(8).getCell(4).getStringCellValue()+"";    // POST/PUT/GET method type
		switch(method) {
		case "POST": 
			
			//wireout notification response
			Response response = given()
			.contentType("application/json")
			.header(""+apiSheet.getRow(0).getCell(1).getStringCellValue()+"",""+apiSheet.getRow(8).getCell(1).getStringCellValue()+"")         //header - authorization
			.body(payload).log().all()
			.when()
			.post(""+apiSheet.getRow(8).getCell(2).getStringCellValue()+"")                    // URI Extension
			.then()
			.extract()
			.response();
			System.out.println("---------------------------------------- wireout notification response 2 ------------------------------------------------");
			System.out.println("Response: " + response.asPrettyString());
			
			for(int j=0;j<wireoutResponseSheet.getRow(0).getLastCellNum();j++) {
				responseMap.put(wireoutResponseSheet.getRow(0).getCell(j).getStringCellValue(), response.jsonPath().getString(formatter.formatCellValue(wireoutResponseSheet.getRow(1).getCell(j))));
			}
			System.out.println(responseMap.toString());

			String actualMsg = responseMap.get(wireoutResponseSheet.getRow(0).getCell(1).getStringCellValue());
			String expectedMsg = wireoutResponseSheet.getRow(2).getCell(1).getStringCellValue();
			if(expectedMsg.equals(actualMsg)) {
				System.out.println("PASS - Webhook response recorded successfully.!");
			}
			else
				System.err.println("FAIL - Webhook response not recorded!!");

			System.out.println("---------------------------------------------end of  wireout notification 2--------------------------------------------------------");
			break;
		}
	}

}



