package com.origin.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import com.origin.utils.excelUtils;

public class Controller {

	private LeadCreationAPI leadCreationAPI;
	private LoanCreationAPI loanCreationAPI;
	private LoanDocumentAPI loanDocumentAPI;
	private SetCreditLimitAPI setCreditLimitAPI;
	private StatusChangeAPI statusChangeAPI;
	private CompositeDrawdownAPI compositeDrawdownAPI;
	private WireOutNotificationAPI wireOutNotificationAPI;
	private WireOutNotificationAPI2 wireOutNotificationAPI2;
	private DrawdownRequestAPI drawdownRequest;
	private LoginPage loginPage;
	private Disbursement disbursement;
	private RepaymentAPI repaymentAPI;
	private RepaymentScheduleAPI repaymentScheduleAPI; 

 

	@Test
	public void control() throws IOException {
		leadCreationAPI = new LeadCreationAPI();
		loanCreationAPI = new LoanCreationAPI();
		loanDocumentAPI = new LoanDocumentAPI();
		statusChangeAPI = new StatusChangeAPI();
		setCreditLimitAPI = new SetCreditLimitAPI();
		compositeDrawdownAPI = new CompositeDrawdownAPI();
		wireOutNotificationAPI = new WireOutNotificationAPI();
		wireOutNotificationAPI2 = new WireOutNotificationAPI2();
		drawdownRequest = new DrawdownRequestAPI();
		repaymentAPI = new RepaymentAPI();
		repaymentScheduleAPI = new RepaymentScheduleAPI();
		loginPage = new LoginPage();
		disbursement = new Disbursement();
		
		

		leadCreationAPI.createLead();
		
		loanCreationAPI.createLoan(leadCreationAPI.getResponseMap(),excelUtils.getAPISheet(),excelUtils.getLeadResponseSheet(),excelUtils.getLoanCreationSheet());

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		loanDocumentAPI.createLoanDocument(loanCreationAPI.getResponseMap(),excelUtils.getAPISheet(),excelUtils.getLoanResponseSheet(),excelUtils.getLoanDocSheet());


		String responseLoanIDValue=loanCreationAPI.getResLoanIDValue();
	
		statusChangeAPI.statusChange(loanCreationAPI.getResponseMap(),excelUtils.getAPISheet(),excelUtils.getLoanResponseSheet(),excelUtils.getStatusChangeSheet(),responseLoanIDValue);
		
		setCreditLimitAPI.setCreditLimit(loanCreationAPI.getResponseMap(),excelUtils.getAPISheet(),excelUtils.getLoanResponseSheet(), excelUtils.getCreditLimitSheet());
		
		compositeDrawdownAPI.compositeDrawdown(loanCreationAPI.getResponseMap(),excelUtils.getAPISheet(),excelUtils.getLoanResponseSheet(),excelUtils.getCompositeDrwdwnSheet());

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		wireOutNotificationAPI.wireoutNotification(compositeDrawdownAPI.getResponseMap(),excelUtils.getAPISheet(),excelUtils.getCompositeDrwdwnResponseSheet(),excelUtils.getWireoutSheet());
		drawdownRequest.drawdownReq(loanCreationAPI.getResponseMap(),excelUtils.getAPISheet(),excelUtils.getLoanResponseSheet(),excelUtils.getDrawDownSheet());
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	
		loginPage.login();
		disbursement.disbursement(responseLoanIDValue);
		String txn_id=disbursement.getTxnID();

		wireOutNotificationAPI2.wireoutNotification2(compositeDrawdownAPI.getResponseMap(),excelUtils.getAPISheet(),excelUtils.getCompositeDrwdwnResponseSheet(),excelUtils.getWireoutSheet(),txn_id);


		repaymentAPI.repayment(loanCreationAPI.getResponseMap(),compositeDrawdownAPI.getResponseMap(),excelUtils.getAPISheet(),excelUtils.getLoanResponseSheet(),excelUtils.getRepaymentSheet());




	}

}
