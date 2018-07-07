package com.cognitivescale.service;

import com.cognitivescale.model.AccountModel;
import com.cognitivescale.util.ResponseUtils;

public interface AccountService {

	ResponseUtils save(AccountModel userModel);

	ResponseUtils findByUsername(String username);

	ResponseUtils validateUser(String username, String password);

	ResponseUtils getBalanceInfoByAccountNumber(Integer accountNumber);

	ResponseUtils calculateInterestByAccountNumber(String date, Integer accountNumber);

}
