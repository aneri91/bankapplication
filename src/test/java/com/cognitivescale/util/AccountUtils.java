package com.cognitivescale.util;

import java.math.BigDecimal;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.cognitivescale.model.AccountModel;

public class AccountUtils {
	
	public static AccountModel buildAccountModel() {
		AccountModel accountModel = new AccountModel();
		String username = RandomStringUtils.randomAlphabetic(10);
		String password = RandomStringUtils.randomAlphabetic(10);
		long phoneNumber = new Random().nextLong();
		BigDecimal balance = new BigDecimal(Math.random());
		accountModel.setUsername(username);
		accountModel.setPassword(password);
		accountModel.setConfirmPassword(password);
		accountModel.setPhoneNumber(phoneNumber);
		accountModel.setBalance(balance);
		return accountModel;
	}
}
