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
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

/**
 * The Class BeneficiaryControllerTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BeneficiaryControllerTest {

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
	 * Beneficiary APIs.
	 */
	@Test
	public void beneficiaryAPIs() {

		Map<String, String> accountMap = AccountUtils.buildAccountModel();

		// creates account
		AccountControllerTest.createAccount(accountMap);

		// login with username and password
		Integer accountNumber = AccountControllerTest.loginWithUsernameAndPassword(accountMap);

		Map<String, String> beneficiaryMap = AccountUtils.buildBeneficiaryModel(accountNumber);

		// adds beneficiary
		addBeneficiary(beneficiaryMap);

		// deletes beneficiary
		deleteBeneficiary(Integer.parseInt(beneficiaryMap.get("beneficiaryAccountNumber")), accountNumber);
	}

	/**
	 * Adds the beneficiary.
	 *
	 * @param beneficiaryMap the beneficiary map
	 */
	public static void addBeneficiary(Map<String, String> beneficiaryMap) {
		Response response = RestAssured.given().contentType("application/json").accept("application/json")
				.body(new Gson().toJson(beneficiaryMap)).when().post("/beneficiary/add");
		String beneficiaryMessage = AccountUtils.buildResponse(response);
		assertThat(beneficiaryMessage).isEqualTo(TestConstants.BENEFICIARY_CREATED);
		LOG.info("/beneficiary/add response ::: " + response.asString());
	}

	/**
	 * Delete beneficiary.
	 *
	 * @param beneficiaryAccountNumber the beneficiary account number
	 * @param accountNumber            the account number
	 */
	private void deleteBeneficiary(Integer beneficiaryAccountNumber, Integer accountNumber) {
		Response response = RestAssured.given().param("beneficiaryAccountNumber", beneficiaryAccountNumber)
				.param("accountNumber", accountNumber).when().delete("/beneficiary/delete");
		String beneficiaryMessage = AccountUtils.buildResponse(response);
		assertThat(beneficiaryMessage).isEqualTo(TestConstants.BENEFICIARY_DELETED);
		LOG.info("/beneficiary/delete response ::: " + response.asString());
	}
}
