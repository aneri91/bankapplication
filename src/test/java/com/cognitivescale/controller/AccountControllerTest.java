package com.cognitivescale.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognitivescale.service.AccountService;
import com.cognitivescale.util.AccountUtils;
import com.cognitivescale.util.TestConstants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountControllerTest {
	private static final Logger LOG = LoggerFactory.getLogger(AccountControllerTest.class);

	@Before
	public void addBeneficiaryDetails() {

		RestAssured.baseURI = "http://localhost:5554";
		RestAssured.defaultParser = Parser.JSON;
	}

	@Test
	public void accountsAPIs() {

		Map<String, String> accountMap = AccountUtils.buildAccountModel();

		addAccountDetails(accountMap);
		Integer accountNumber = loginWithUsernameAndPassword(accountMap);
		getBalanceInfo(accountNumber);
		calculateInterest(accountNumber);
	}

	private void addAccountDetails(Map<String, String> accountMap) {
		Response newUserAccount = RestAssured.given().contentType("application/json").accept("application/json")
				.body(new Gson().toJson(accountMap)).when().post("/account/create_account");
		JsonObject accountObject = (JsonObject) new JsonParser().parse(newUserAccount.asString());
		String accountMessage = accountObject.get("message").getAsString();
		String accountStatus = accountObject.get("status").getAsString();
		assertEquals(true, newUserAccount.getStatusCode() == 200);
		assertThat(accountStatus).isEqualTo(TestConstants.STATUS_SUCCESS);
		assertThat(accountMessage).isEqualTo(TestConstants.ACCOUNT_CREATED);
		LOG.info("/account/create_account response ::: " + newUserAccount.asString());
	}

	private Integer loginWithUsernameAndPassword(Map<String, String> accountMap) {
		Response loggedInUser = RestAssured.given().contentType("application/json").accept("application/json")
				.body(new Gson().toJson(accountMap)).when().post("/account/login");
		JsonObject loginObject = (JsonObject) new JsonParser().parse(loggedInUser.asString());
		String loginMessage = loginObject.get("message").getAsString();
		String loginStatus = loginObject.get("status").getAsString();
		JsonObject loginDataJson = loginObject.get("data").getAsJsonObject();
		Integer accountNumber = loginDataJson.get("accountNumber").getAsInt();
		assertEquals(true, loggedInUser.getStatusCode() == 200);
		assertThat(loginStatus).isEqualTo(TestConstants.STATUS_SUCCESS);
		assertThat(loginMessage).isEqualTo(TestConstants.ACCOUNT_IS_REGISTERED);
		LOG.info("/account/login response ::: " + loggedInUser.asString());
		return accountNumber;
	}

	private void getBalanceInfo(Integer accountNumber) {
		Response registeredAccount = RestAssured.given().param("accountNumber", accountNumber).when()
				.get("/account/balance");
		JsonObject accountObject = (JsonObject) new JsonParser().parse(registeredAccount.asString());
		String accountMessage = accountObject.get("message").getAsString();
		String accountStatus = accountObject.get("status").getAsString();
		assertEquals(true, registeredAccount.getStatusCode() == 200);
		assertThat(accountStatus).isEqualTo(TestConstants.STATUS_SUCCESS);
		assertThat(accountMessage).isEqualTo(TestConstants.ACCOUNT_BALANCE);
		LOG.info("/account/balance response ::: " + registeredAccount.asString());
	}

	private void calculateInterest(Integer accountNumber) {
		Response registeredAccount = RestAssured.given().param("accountNumber", accountNumber)
				.param("date", "2050-08-30").when().get("/account/calculate_interest");
		JsonObject accountObject = (JsonObject) new JsonParser().parse(registeredAccount.asString());
		String accountMessage = accountObject.get("message").getAsString();
		String accountStatus = accountObject.get("status").getAsString();
		assertEquals(true, registeredAccount.getStatusCode() == 200);
		assertThat(accountStatus).isEqualTo(TestConstants.STATUS_SUCCESS);
		assertThat(accountMessage).isEqualTo(TestConstants.ACCOUNT_CALCULATED_BALANCE);
		LOG.info("/account/calculate_interest response ::: " + registeredAccount.asString());
	}
}
