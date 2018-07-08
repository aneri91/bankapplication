package com.cognitivescale.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class AccountUtils {
	
	public static Map<String, String> buildAccountModel() {
		Map<String, String> userMap = new HashMap<String, String>();
		String username = RandomStringUtils.randomAlphabetic(10);
		String password = RandomStringUtils.randomAlphabetic(10);
		long phoneNumber = new Random().nextLong();
		BigDecimal balance = new BigDecimal(Math.random());
		userMap.put("username", username);
		userMap.put("password", password);
		userMap.put("confirmPassword", password);
		userMap.put("phoneNumber", String.valueOf(phoneNumber));
		userMap.put("balance", String.valueOf(balance));
		return userMap;
	}
}
