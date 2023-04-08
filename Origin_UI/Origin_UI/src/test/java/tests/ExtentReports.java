package tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReports {


	@BeforeSuite
	public void initialiseExtentReports() {
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("Report.html");
		//		extent.attachReporter(spark);


	}

	// inside test 
	//	ExtentTest test = extent.createTest("TestName");
	//	test.log(Status.PASS, "pass");
	//	test.pass("pass");
	//	test.log(Status.FAIL, "fail");
	//	test.fail("fail");



	@AfterSuite
	public void generateExtentReports() throws Exception {

	}

}
