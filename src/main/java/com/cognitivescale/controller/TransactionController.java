package com.cognitivescale.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognitivescale.service.TransactionService;
import com.cognitivescale.util.ResponseUtils;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	private static final Logger LOG = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = { "/funds" }, method = RequestMethod.GET)
	public ResponseUtils transferFunds(@Param(value = "beneficiaryAccountNumber") Integer beneficiaryAccountNumber,
			@Param(value = "accountNumber") Integer accountNumber, @Param(value = "amount") BigDecimal amount) {
		LOG.info("transaction/funds called");
		return transactionService.transferFunds(beneficiaryAccountNumber, accountNumber, amount);
	}

	@RequestMapping(value = { "/schedule" }, method = RequestMethod.GET)
	public ResponseUtils scheduleFunds(@Param(value = "beneficiaryAccountNumber") Integer beneficiaryAccountNumber,
			@Param(value = "accountNumber") Integer accountNumber, @Param(value = "amount") BigDecimal amount,
			@Param(value = "datetime") Timestamp datetime) {
		LOG.info("transaction/schedule called");
		return transactionService.scheduleFunds(beneficiaryAccountNumber, accountNumber, amount, datetime);
	}
}
