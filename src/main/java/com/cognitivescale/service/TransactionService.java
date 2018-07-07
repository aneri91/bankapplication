package com.cognitivescale.service;

import java.math.BigDecimal;
import java.util.Date;

import com.cognitivescale.util.ResponseUtils;

/**
 * The Interface TransactionService.
 */
public interface TransactionService {

	/**
	 * Find all transactions by account number.
	 *
	 * @param accountNumber the account number
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @return the response utils
	 */
	ResponseUtils findAllTransactionsByAccountNumber(Integer accountNumber, Date fromDate, Date toDate);

	/**
	 * Transfer funds.
	 *
	 * @param beneficiaryAccountNumber the beneficiary account number
	 * @param accountNumber the account number
	 * @param amount the amount
	 * @return the response utils
	 */
	ResponseUtils transferFunds(Integer beneficiaryAccountNumber, Integer accountNumber, BigDecimal amount);

	/**
	 * Schedule funds.
	 *
	 * @param beneficiaryAccountNumber the beneficiary account number
	 * @param accountNumber the account number
	 * @param amount the amount
	 * @param datetime the datetime
	 * @return the response utils
	 */
	ResponseUtils scheduleFunds(Integer beneficiaryAccountNumber, Integer accountNumber, BigDecimal amount,
			String datetime);
}
