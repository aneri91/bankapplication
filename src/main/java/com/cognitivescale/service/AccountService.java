package com.cognitivescale.service;

import com.cognitivescale.model.AccountModel;
import com.cognitivescale.util.ResponseUtils;

/**
 * The Interface AccountService.
 */
public interface AccountService {

	/**
	 * Save.
	 *
	 * @param userModel the user model
	 * @return the response utils
	 */
	ResponseUtils save(AccountModel userModel);

	/**
	 * Validate user.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the response utils
	 */
	ResponseUtils validateUser(String username, String password);

	/**
	 * Gets the balance info by account number.
	 *
	 * @param accountNumber the account number
	 * @return the balance info by account number
	 */
	ResponseUtils getBalanceInfoByAccountNumber(Integer accountNumber);

	/**
	 * Calculate interest by account number.
	 *
	 * @param date          the date
	 * @param accountNumber the account number
	 * @return the response utils
	 */
	ResponseUtils calculateInterestByAccountNumber(String date, Integer accountNumber);

}
