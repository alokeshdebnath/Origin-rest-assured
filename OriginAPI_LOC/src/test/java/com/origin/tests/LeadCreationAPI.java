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
public class LeadCreationAPI extends ExtentReportListener{
	String partnerLoanAppIDValue="";
	String partnerBorrowerIDValue="";
	String loanAppIDValue="";
	String borrowerIDValue="";
	XSSFSheet leadCreationSheet = null;
	XSSFSheet loanCreationSheet = null;
	XSSFSheet apiSheet = null;
	XSSFSheet leadResponseSheet = null;

	HashMap <String,String> responseMap = new HashMap<>();

	public HashMap<String, String> getResponseMap() {
		return responseMap;
	}

	@Test
	public void createLead() throws IOException {

		System.out.println("------------------------------------------------------inside create lead---------------------------------------------");
		leadCreationSheet = excelUtils.getLeadCreationSheet();
		loanCreationSheet = excelUtils.getLoanCreationSheet();
		apiSheet = excelUtils.getAPISheet();
		leadResponseSheet = excelUtils.getLeadResponseSheet();

		RestAssured.baseURI=""+apiSheet.getRow(1).getCell(0).getStringCellValue()+"";

		int colNum = leadCreationSheet.getRow(1).getLastCellNum();
		int rowCount = leadCreationSheet.getPhysicalNumberOfRows();
		System.out.println("Lead sheet Row count ----- "+rowCount);
		System.out.println("Lead sheet Column count ------ "+colNum);
		DataFormatter formatter = new DataFormatter();

		// lead request
		StringJoiner sj = new StringJoiner(",","[{","}]");

		for(int j=2;j<leadCreationSheet.getRow(0).getLastCellNum();j++) {
			String value = formatter.formatCellValue(leadCreationSheet.getRow(1).getCell(j));
			if (value != null && !value.isEmpty()) {
				sj.add("\""+leadCreationSheet.getRow(0).getCell(j).getStringCellValue()+"\": \""+value+"\"" );
			}

		}

		String payload = sj.toString();


		String method = ""+apiSheet.getRow(1).getCell(4).getStringCellValue()+"";                  // POST/PUT/GET method type
		switch(method) {
		case "POST": 
			
			//lead response
			Response response = given()
			.contentType("application/json")
			.header(""+apiSheet.getRow(0).getCell(1).getStringCellValue()+"",""+apiSheet.getRow(1).getCell(1).getStringCellValue()+"")        //header - authorization
			.body(payload).log().all()
			.when()
			.post(""+apiSheet.getRow(1).getCell(2).getStringCellValue()+"")                    // URI Extension
			.then() 
			.extract()
			.response();

			System.out.println("------------------------------------------------------Lead Response -------------------------------------------------------");

			System.out.println("Response: " + response.asPrettyString());
			System.out.println("Message === "+response.jsonPath().get("message").toString());
			int statusCode = response.getStatusCode();
			System.out.println("Status code : - " +statusCode);
 

			for(int j=0;j<leadResponseSheet.getRow(0).getLastCellNum();j++) {
				responseMap.put(leadResponseSheet.getRow(0).getCell(j).getStringCellValue(), response.jsonPath().getString(formatter.formatCellValue(leadResponseSheet.getRow(1).getCell(j))));
			}
			String actualMsg = responseMap.get(leadResponseSheet.getRow(0).getCell(1).getStringCellValue());
			String expectedMsg = leadResponseSheet.getRow(2).getCell(1).getStringCellValue();

			if(expectedMsg.equals(actualMsg)) {
				System.out.println("PASS - Lead generated succesfully!!");
			}
			else
				System.err.println("FAIL - Lead not generated!");
			System.out.println("LEAD RESPONSE MAP ========== "+responseMap.toString());


			//			int statusCode = response.getStatusCode();
			//			Assert.assertEquals(statusCode, 200);
			//			System.out.println("Status code ======= "+statusCode);






			//		int statusCode = response.getStatusCode();
			//		try {
			//			Assert.assertEquals(statusCode, 200);
			//			test.log(LogStatus.PASS, "Status code is as expected = "+statusCode);
			//		} catch (AssertionError e) {
			//			test.log(LogStatus.FAIL, e.fillInStackTrace());
			//			test.log(LogStatus.FAIL,
			//					"Expected status code is :: " + statusCode + " , insted of getting :: " + response.getStatusCode());
			//		} catch (Exception e) {
			//			test.log(LogStatus.FAIL, e.fillInStackTrace());
			//		}
			//		
			//		System.out.println("Response: " + response.asPrettyString());
			//		String msg = response.jsonPath().getString("message");
			//		try {
			//			Assert.assertEquals(msg, "Lead generated successfully");
			//			test.log(LogStatus.PASS, "Successfully validated message. Message is - "+msg);
			//		} catch (AssertionError e) {
			//			test.log(LogStatus.FAIL, e.fillInStackTrace());
			//			test.log(LogStatus.FAIL,
			//					"Expected message is :: " + msg + " , insted of getting :: " + response.jsonPath().getString(msg));
			//		} catch (Exception e) {
			//			test.log(LogStatus.FAIL, e.fillInStackTrace());
			//		}
			//	
			System.out.println("--------------------------------------end of create lead-----------------------------------------------");
			break;
		}
	}

}

