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
public class PatchAPI extends ExtentReportListener{
	String partnerLoanAppIDValue="";
	String partnerBorrowerIDValue="";
	String loanAppIDValue="";
	String borrowerIDValue="";
	XSSFSheet apiSheet = null;
	XSSFSheet processSheet = null;
	XSSFSheet patchResponseSheet = null;


	HashMap <String,String> responseMap = new HashMap<>();

	public HashMap<String, String> getResponseMap(){
		return responseMap;
	}

	@Test
	public void createPatch(HashMap<String, String> requestMap,XSSFSheet apiSheet,XSSFSheet leadResponseSheet,XSSFSheet processSheet) throws IOException {

		System.out.println("------------------------------------------------------inside Patch API ---------------------------------------------");
		apiSheet = excelUtils.getAPISheet();
		processSheet = excelUtils.getPatchSheet();
		patchResponseSheet = excelUtils.getPatchResponseSheet();

		RestAssured.baseURI=""+apiSheet.getRow(1).getCell(0).getStringCellValue()+"";

		DataFormatter formatter = new DataFormatter();
		int colNum = processSheet.getRow(1).getLastCellNum();
		int rowCount = processSheet.getPhysicalNumberOfRows();
		System.out.println("Patch sheet Row count ----- "+rowCount);
		System.out.println("Patch sheet Column count ------ "+colNum);

		// lead request
		StringJoiner sj = new StringJoiner(",","[{","}]");
			
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
		
		String payload = sj.toString();


		String method = ""+apiSheet.getRow(17).getCell(3).getStringCellValue()+"";                  // POST/PUT/GET method type
		switch(method) {
		case "PUT": 
			
			//lead response
			Response response = given()
			.contentType("application/json")
			.header(""+apiSheet.getRow(0).getCell(1).getStringCellValue()+"",""+apiSheet.getRow(17).getCell(1).getStringCellValue()+"")        //header - authorization
			.body(payload).log().all()
			.when()
			.put(""+apiSheet.getRow(17).getCell(2).getStringCellValue()+"")                    // URI Extension
			.then()
			.extract()
			.response();

			System.out.println("------------------------------------------------------Patch Response -------------------------------------------------------");

			System.out.println("Response: " + response.asPrettyString());
			System.out.println("Message === "+response.jsonPath().get("message").toString());


			for(int j=0;j<patchResponseSheet.getRow(0).getLastCellNum();j++) {
				responseMap.put(patchResponseSheet.getRow(0).getCell(j).getStringCellValue(), response.jsonPath().getString(formatter.formatCellValue(patchResponseSheet.getRow(1).getCell(j))));
			}
			String actualMsg = responseMap.get(patchResponseSheet.getRow(0).getCell(1).getStringCellValue());
			String expectedMsg = patchResponseSheet.getRow(2).getCell(1).getStringCellValue();

			if(expectedMsg.equals(actualMsg)) {
				System.out.println("PASS - Successfully updated 1 rows!!");
			}
			else
				System.err.println("FAIL - Could not update any rows!");
			System.out.println("PATCH RESPONSE MAP ========== "+responseMap.toString());

			System.out.println("--------------------------------------end of Patch API-----------------------------------------------");
			break;
		}
	}

}

