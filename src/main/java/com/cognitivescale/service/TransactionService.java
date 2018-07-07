package com.cognitivescale.service;

import java.math.BigDecimal;
import java.util.Date;

import com.cognitivescale.util.ResponseUtils;

public interface TransactionService {

	ResponseUtils findAllTransactionsByAccountNumber(Integer accountNumber, Date fromDate, Date toDate);

	ResponseUtils transferFunds(Integer beneficiaryAccountNumber, Integer accountNumber, BigDecimal amount);

	ResponseUtils scheduleFunds(Integer beneficiaryAccountNumber, Integer accountNumber, BigDecimal amount,
			String datetime);
}
