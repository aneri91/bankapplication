package com.cognitivescale.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognitivescale.model.AccountModel;
import com.cognitivescale.service.AccountService;
import com.cognitivescale.util.ResponseUtils;

import io.swagger.annotations.Api;

/**
 * The Class AccountController.
 */
@RestController
@RequestMapping("/account")
@Api(value = "account")
public class AccountController {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

	/** The account service. */
	@Autowired
	private AccountService accountService;

	/**
	 * Creates the account.
	 *
	 * @param accountModel the account model
	 * @return the response utils
	 */
	@RequestMapping(value = "/create_account", method = RequestMethod.POST)
	public ResponseUtils createAccount(@Valid @RequestBody AccountModel accountModel) {
		LOG.info("account/create_account called");
		return accountService.save(accountModel);
	}

	/**
	 * Login.
	 *
	 * @param accountModel the account model
	 * @return the response utils
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseUtils login(@Valid @RequestBody AccountModel accountModel) {
		LOG.info("account/login called");
		return accountService.validateUser(accountModel.getUsername(), accountModel.getPassword());
	}

	/**
	 * Gets the balance info.
	 *
	 * @param accountNumber the account number
	 * @return the balance info
	 */
	@RequestMapping(value = { "/balance" }, method = RequestMethod.GET)
	public ResponseUtils getBalanceInfo(@Param(value = "accountNumber") Integer accountNumber) {
		LOG.info("account/balance called");
		return accountService.getBalanceInfoByAccountNumber(accountNumber);
	}

	/**
	 * Calculate interest.
	 *
	 * @param date the date
	 * @param accountNumber the account number
	 * @return the response utils
	 */
	@RequestMapping(value = { "/calculate_interest" }, method = RequestMethod.GET)
	public ResponseUtils calculateInterest(@Param(value = "date") String date,
			@Param(value = "accountNumber") Integer accountNumber) {
		LOG.info("account/calculate_interest called");
		return accountService.calculateInterestByAccountNumber(date, accountNumber);
	}
}
