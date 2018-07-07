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

@RestController
@RequestMapping("/account")
public class AccountController {
	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/create_account", method = RequestMethod.POST)
	public ResponseUtils createAccount(@Valid @RequestBody AccountModel accountModel) {
		LOG.info("account/create_account called");
		return accountService.save(accountModel);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseUtils login(@Valid @RequestBody AccountModel accountModel) {
		LOG.info("account/login called");
		return accountService.validateUser(accountModel.getUsername(), accountModel.getPassword());
	}

	@RequestMapping(value = { "/balance" }, method = RequestMethod.GET)
	public ResponseUtils getBalanceInfo(@Param(value = "accountNumber") Integer accountNumber) {
		LOG.info("account/balance called");
		return accountService.getBalanceInfoByAccountNumber(accountNumber);
	}

	@RequestMapping(value = { "/calculate_interest" }, method = RequestMethod.GET)
	public ResponseUtils calculateInterest(@Param(value = "date") String date,
			@Param(value = "accountNumber") Integer accountNumber) {
		LOG.info("account/calculate_interest called");
		return accountService.calculateInterestByAccountNumber(date, accountNumber);
	}
}
