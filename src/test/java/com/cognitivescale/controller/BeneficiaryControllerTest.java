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
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeneficiaryControllerTest {
	private static final Logger LOG = LoggerFactory.getLogger(BeneficiaryControllerTest.class);

	@Before
	public void setUpConfig() {
		RestAssured.baseURI = "http://localhost:5554";
		RestAssured.defaultParser = Parser.JSON;
	}

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

	private void addBeneficiary(Map<String, String> beneficiaryMap) {
		Response response = RestAssured.given().contentType("application/json").accept("application/json")
				.body(new Gson().toJson(beneficiaryMap)).when().post("/beneficiary/add");
		String beneficiaryMessage = AccountUtils.buildResponse(response);
		assertThat(beneficiaryMessage).isEqualTo(TestConstants.BENEFICIARY_CREATED);
		LOG.info("/beneficiary/add response ::: " + response.asString());
	}

	private void deleteBeneficiary(Integer beneficiaryAccountNumber, Integer accountNumber) {
		Response response = RestAssured.given().param("beneficiaryAccountNumber", beneficiaryAccountNumber)
				.param("accountNumber", accountNumber).when().delete("/beneficiary/delete");
		String beneficiaryMessage = AccountUtils.buildResponse(response);
		assertThat(beneficiaryMessage).isEqualTo(TestConstants.BENEFICIARY_DELETED);
		LOG.info("/beneficiary/delete response ::: " + response.asString());
	}
}
