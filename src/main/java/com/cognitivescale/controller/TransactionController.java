package com.cognitivescale.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognitivescale.service.TransactionService;
import com.cognitivescale.util.ResponseUtils;

import io.swagger.annotations.Api;

/**
 * The Class TransactionController.
 */
@RestController
@RequestMapping("/transaction")
@Api(value = "transaction")
public class TransactionController {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TransactionController.class);

	/** The transaction service. */
	@Autowired
	private TransactionService transactionService;

	/**
	 * Transaction summary.
	 *
	 * @param toDate the to date
	 * @param fromDate the from date
	 * @param accountNumber the account number
	 * @return the response utils
	 * @throws Exception the exception
	 */
	@RequestMapping(value = { "/statement" }, method = RequestMethod.GET)
	public ResponseUtils transactionSummary(@Param(value = "toDate") String toDate,
			@Param(value = "fromDate") String fromDate, @Param(value = "accountNumber") Integer accountNumber)
			throws Exception {
		LOG.info("transaction/statement called");
		Date fromParserDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
		Date toParserDate = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
		return transactionService.findAllTransactionsByAccountNumber(accountNumber, fromParserDate, toParserDate);
	}

	/**
	 * Transfer funds.
	 *
	 * @param beneficiaryAccountNumber the beneficiary account number
	 * @param accountNumber the account number
	 * @param amount the amount
	 * @return the response utils
	 */
	@RequestMapping(value = { "/funds" }, method = RequestMethod.GET)
	public ResponseUtils transferFunds(@Param(value = "beneficiaryAccountNumber") Integer beneficiaryAccountNumber,
			@Param(value = "accountNumber") Integer accountNumber, @Param(value = "amount") BigDecimal amount) {
		LOG.info("transaction/funds called");
		return transactionService.transferFunds(beneficiaryAccountNumber, accountNumber, amount);
	}

	/**
	 * Schedule funds.
	 *
	 * @param beneficiaryAccountNumber the beneficiary account number
	 * @param accountNumber the account number
	 * @param amount the amount
	 * @param datetime the datetime
	 * @return the response utils
	 */
	@RequestMapping(value = { "/schedule" }, method = RequestMethod.GET)
	public ResponseUtils scheduleFunds(@Param(value = "beneficiaryAccountNumber") Integer beneficiaryAccountNumber,
			@Param(value = "accountNumber") Integer accountNumber, @Param(value = "amount") BigDecimal amount,
			@Param(value = "datetime") String datetime) {
		LOG.info("transaction/schedule called");
		return transactionService.scheduleFunds(beneficiaryAccountNumber, accountNumber, amount, datetime);
	}
}
