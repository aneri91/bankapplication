package com.cognitivescale.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.cognitivescale.service.TransactionService;
import com.cognitivescale.util.ResponseUtils;

@RestController
@RequestMapping("/account")
public class AccountController {
	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionService transactionService;

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

	@RequestMapping(value = { "/statement" }, method = RequestMethod.GET)
	public ResponseUtils transactionSummary(@Param(value = "toDate") String toDate,
			@Param(value = "fromDate") String fromDate, @Param(value = "accountNumber") Integer accountNumber)
			throws Exception {
		LOG.info("account/statement called");
		Date fromParserDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
		Date toParserDate = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
		return transactionService.findAllTransactions(accountNumber, fromParserDate, toParserDate);
	}

	@RequestMapping(value = { "/calculate_interest" }, method = RequestMethod.GET)
	public ResponseUtils calculateInterest(@Param(value = "date") String date,
			@Param(value = "accountNumber") Integer accountNumber) {
		LOG.info("account/calculate_interest called");
		return accountService.calculateInterestByAccountNumber(date, accountNumber);
	}
}
