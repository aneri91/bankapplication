package com.cognitivescale.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;

public class AccountUtils {

	public static Map<String, String> buildAccountModel() {
		Map<String, String> accountMap = new HashMap<String, String>();
		String username = RandomStringUtils.randomAlphabetic(10);
		String password = RandomStringUtils.randomAlphabetic(10);
		long phoneNumber = new Random().nextLong();
		accountMap.put("username", username);
		accountMap.put("password", password);
		accountMap.put("confirmPassword", password);
		accountMap.put("phoneNumber", String.valueOf(phoneNumber));
		accountMap.put("balance", String.valueOf(BigDecimal.TEN));
		return accountMap;
	}

	public static Map<String, String> buildBeneficiaryModel(Integer accountNumber) {
		Map<String, String> beneficiaryMap = new HashMap<String, String>();
		String beneficiaryName = RandomStringUtils.randomAlphabetic(10);
		String ifscCode = RandomStringUtils.randomAlphabetic(10);
		String nickName = RandomStringUtils.randomAlphabetic(10);
		beneficiaryMap.put("beneficiaryName", beneficiaryName);
		beneficiaryMap.put("beneficiaryAccountNumber", String.valueOf(new Random().nextInt(900000000) + 100000000));
		beneficiaryMap.put("ifscCode", ifscCode);
		beneficiaryMap.put("nickName", nickName);
		beneficiaryMap.put("accountType", "Current");
		beneficiaryMap.put("accountNumber", String.valueOf(accountNumber));
		return beneficiaryMap;
	}

	public static String buildResponse(Response response) {
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(response.asString());
		String status = jsonObject.get("status").getAsString();
		String message = jsonObject.get("message").getAsString();
		assertEquals(true, response.getStatusCode() == 200);
		assertThat(status).isEqualTo(TestConstants.STATUS_SUCCESS);
		return message;
	}

	public static void config() {
		RestAssured.baseURI = "http://localhost:5554";
		RestAssured.defaultParser = Parser.JSON;
	}
}
