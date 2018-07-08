package com.cognitivescale.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognitivescale.util.AccountUtils;
import com.cognitivescale.util.TestConstants;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionControllerTest {
	private static final Logger LOG = LoggerFactory.getLogger(BeneficiaryControllerTest.class);

	@Before
	public void setUpConfig() {
		AccountUtils.config();
	}

	@Test
	public void transactionAPIs() {

		Map<String, String> accountMap = AccountUtils.buildAccountModel();

		// creates account
		AccountControllerTest.createAccount(accountMap);

		// login with username and password
		Integer accountNumber = AccountControllerTest.loginWithUsernameAndPassword(accountMap);

		Map<String, String> beneficiaryMap = AccountUtils.buildBeneficiaryModel(accountNumber);

		// adds beneficiary
		BeneficiaryControllerTest.addBeneficiary(beneficiaryMap);

		// transfer funds
		transferFunds(Integer.parseInt(beneficiaryMap.get("beneficiaryAccountNumber")), accountNumber);

		// transaction summary
		transactionSummary(accountNumber);

	}

	private void transferFunds(Integer beneficiaryAccountNumber, Integer accountNumber) {
		Response response = RestAssured.given().param("beneficiaryAccountNumber", beneficiaryAccountNumber)
				.param("accountNumber", accountNumber).param("amount", 9).when().get("/transaction/funds");
		String transactionMessage = AccountUtils.buildResponse(response);
		assertThat(transactionMessage).isEqualTo(TestConstants.TRANSFERRED_FUNDS);
		LOG.info("/transaction/funds ::: " + response.asString());
	}

	private void transactionSummary(Integer accountNumber) {
		Response response = RestAssured.given().param("accountNumber", accountNumber).param("toDate", "2019-05-06")
				.param("fromDate", "2017-05-06").when().get("/transaction/statement");
		String transactionMessage = AccountUtils.buildResponse(response);
		assertThat(transactionMessage).isEqualTo(TestConstants.TRANSACTION_LIST);
		LOG.info("/transaction/statement response ::: " + response.asString());
	}

}
