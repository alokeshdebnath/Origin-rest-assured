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
public class RepaymentScheduleAPI extends ExtentReportListener{

	XSSFSheet apiSheet = null;
//	XSSFSheet repaymentSheet = null;
	XSSFSheet repaymentScheduleResponseSheet = null;

	HashMap <String,String> responseMap = new HashMap<>();

	public HashMap<String, String> getResponseMap() {
		return responseMap;
	}

	@Test
	public void repaymentSchedule(HashMap<String, String> requestMap,XSSFSheet apiSheet,XSSFSheet loanResponseSheet,XSSFSheet processSheet) throws IOException {

		System.out.println("----------------------------------------inside repayment schedule ----------------------------------------------");
		apiSheet = excelUtils.getAPISheet();
		processSheet = excelUtils.getRepaymentScheduleSheet();
		repaymentScheduleResponseSheet = excelUtils.getRepaymentScheduleResponseSheet();


		DataFormatter formatter = new DataFormatter();

		RestAssured.baseURI=""+apiSheet.getRow(1).getCell(0).getStringCellValue()+"";
		
//		int colNum = processSheet.getRow(0).getLastCellNum();
//		int rowCount = processSheet.getPhysicalNumberOfRows();
//		System.out.println("Repayment Sch Row count ----- "+rowCount);
//		System.out.println("Repayment Sch Column count ------ "+colNum);


		// repayment schedule request
		StringJoiner emi1 = new StringJoiner(",",":[{", "},");
		for(int j=7;j<processSheet.getRow(0).getLastCellNum();j++) {
			emi1.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+formatter.formatCellValue(processSheet.getRow(1).getCell(j))+"\"" );

		}
		StringJoiner emi2 = new StringJoiner(",","{", "},");
		for(int j=7;j<processSheet.getRow(0).getLastCellNum();j++) {
			emi2.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+formatter.formatCellValue(processSheet.getRow(2).getCell(j))+"\"" );

		}
		StringJoiner emi3 = new StringJoiner(",","{", "},");
		for(int j=7;j<processSheet.getRow(0).getLastCellNum();j++) {
			emi3.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+formatter.formatCellValue(processSheet.getRow(3).getCell(j))+"\"" );

		}
		StringJoiner emi4 = new StringJoiner(",","{", "}]}");
		for(int j=7;j<processSheet.getRow(0).getLastCellNum();j++) {
			emi4.add("\""+processSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+formatter.formatCellValue(processSheet.getRow(4).getCell(j))+"\"" );

		}

		String emiJoin = emi1.toString()+emi2.toString()+emi3.toString()+emi4.toString();
		
		StringJoiner sj = new StringJoiner(",","{", ",");
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
		}
		String repayment_schedule_json = "\"repayment_schedule_json\""+emiJoin;
		String payload =sj.toString()+repayment_schedule_json;

		System.out.println("Repayment Schedule payload ======= "+ payload);

		String method = ""+apiSheet.getRow(7).getCell(3).getStringCellValue()+"";    // POST/PUT/GET method type
		switch(method) {
		case "POST": 
			
			//repayment response
			Response response = given()
			.contentType("application/json")
			.header(""+apiSheet.getRow(0).getCell(1).getStringCellValue()+"",""+apiSheet.getRow(11).getCell(1).getStringCellValue()+"")        //header - authorization
			.body(payload).log().all()
			.when()
			.post(""+apiSheet.getRow(11).getCell(2).getStringCellValue()+"")                    // URI Extension
			.then()
			.extract()
			.response();
			System.out.println("----------------------------------------repayment schedule response ------------------------------------------------");
			System.out.println("Response: " + response.asPrettyString());

			for(int j=0;j<repaymentScheduleResponseSheet.getRow(0).getLastCellNum();j++) {
				responseMap.put(repaymentScheduleResponseSheet.getRow(0).getCell(j).getStringCellValue(), response.jsonPath().getString(formatter.formatCellValue(repaymentScheduleResponseSheet.getRow(1).getCell(j))));
			}
			System.out.println(responseMap.toString());

			String actualMsg = responseMap.get(repaymentScheduleResponseSheet.getRow(0).getCell(1).getStringCellValue());
			String expectedMsg = repaymentScheduleResponseSheet.getRow(2).getCell(1).getStringCellValue();

			if(expectedMsg.equals(actualMsg)) {
				System.out.println("PASS - Repayment schedule recorded successfully!");
			}
			else
				System.err.println("FAIL - Repayment schedule NOT recorded!!");


			System.out.println("---------------------------------------------end of repayment schedule--------------------------------------------------------");
			break;
		}
	}

}



