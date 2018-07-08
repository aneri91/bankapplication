package com.cognitivescale.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognitivescale.model.AccountModel;
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
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
	private static final Logger LOG = LoggerFactory.getLogger(AccountControllerTest.class);

	@MockBean
	private AccountService accountService;

	@Before
	public void setUpConfig() {
		RestAssured.baseURI = "http://localhost:5554";
		RestAssured.defaultParser = Parser.JSON;

		AccountModel accountModel = AccountUtils.buildAccountModel();
		Response newUserAccount = RestAssured.given().contentType("application/json").accept("application/json")
				.body(new Gson().toJson(accountModel)).when().post("/account/create_account");
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(newUserAccount.asString());
		String responseMessage = jsonObject.get("message").getAsString();
		String responseStatus = jsonObject.get("status").getAsString();
		assertEquals(true, newUserAccount.getStatusCode() == 200);
		assertThat(responseStatus).isEqualTo(TestConstants.STATUS_SUCCESS);
		assertThat(responseMessage).isEqualTo(TestConstants.ACCOUNT_CREATED);
		LOG.info("/account/create_account response ::: " + newUserAccount.asString());
	}

	@Test
	public void loginWithUsernameAndPassword() {
		AccountModel accountModel = AccountUtils.buildAccountModel();
		Response loggedInUser = RestAssured.given().contentType("application/json").accept("application/json")
				.body(new Gson().toJson(accountModel)).when().post("/account/login");
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(loggedInUser.asString());
		String responseMessage = jsonObject.get("message").getAsString();
		String responseStatus = jsonObject.get("status").getAsString();
		assertEquals(true, loggedInUser.getStatusCode() == 200);
		assertThat(responseStatus).isEqualTo(TestConstants.STATUS_SUCCESS);
		assertThat(responseMessage).isEqualTo(TestConstants.ACCOUNT_IS_REGISTERED);
		LOG.info("/account/login response ::: " + loggedInUser.asString());
	}

}
