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

/**
 * The Class TransactionControllerTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionControllerTest {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(BeneficiaryControllerTest.class);

	/**
	 * Sets the up config.
	 */
	@Before
	public void setUpConfig() {
		AccountUtils.config();
	}

	/**
	 * Transaction APIs.
	 */
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

	/**
	 * Transfer funds.
	 *
	 * @param beneficiaryAccountNumber the beneficiary account number
	 * @param accountNumber            the account number
	 */
	private void transferFunds(Integer beneficiaryAccountNumber, Integer accountNumber) {
		Response response = RestAssured.given().param("beneficiaryAccountNumber", beneficiaryAccountNumber)
				.param("accountNumber", accountNumber).param("amount", 9).when().get("/transaction/funds");
		String transactionMessage = AccountUtils.buildResponse(response);
		assertThat(transactionMessage).isEqualTo(TestConstants.TRANSFERRED_FUNDS);
		LOG.info("/transaction/funds response ::: " + response.asString());
	}

	/**
	 * Transaction summary.
	 *
	 * @param accountNumber the account number
	 */
	private void transactionSummary(Integer accountNumber) {
		Response response = RestAssured.given().param("accountNumber", accountNumber).param("toDate", "2019-05-06")
				.param("fromDate", "2017-05-06").when().get("/transaction/statement");
		String transactionMessage = AccountUtils.buildResponse(response);
		assertThat(transactionMessage).isEqualTo(TestConstants.TRANSACTION_LIST);
		LOG.info("/transaction/statement response ::: " + response.asString());
	}

}
