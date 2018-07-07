package com.cognitivescale.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cognitivescale.dao.AccountDao;
import com.cognitivescale.dao.BeneficiaryDao;
import com.cognitivescale.dao.TransactionDao;
import com.cognitivescale.entity.Account;
import com.cognitivescale.entity.Beneficiary;
import com.cognitivescale.entity.Transaction;
import com.cognitivescale.util.Constants;
import com.cognitivescale.util.ObjectUtils;
import com.cognitivescale.util.ResponseUtils;

@Service
public class TransactionServiceImpl implements TransactionService {
	private static final Logger LOG = LoggerFactory.getLogger(TransactionServiceImpl.class);
	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	private BeneficiaryDao beneficiaryDao;

	@Autowired
	private AccountDao accountDao;

	@Override
	public ResponseUtils findAllTransactions(Integer accountNumber, Date fromDate, Date toDate) {
		LOG.info("transaction summary");
		ResponseUtils response = new ResponseUtils(Constants.STATUS_ERROR);
		Transaction transaction = transactionDao.findByAccountNumber(accountNumber);
		try {
			if (transaction != null) {
				List<Transaction> transactionList = transactionDao.transactionDetailsByAccountAndDates(accountNumber,
						fromDate, toDate);
				System.out.println(transactionList.size());
				response.setData(transactionList);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setMessage("Transaction list is retrieved successfully.");
			} else {
				response.setMessage("No transactions exist for this account number.");
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
			response.setMessage("Exception in getting statement.");
		}
		return response;
	}

	@Override
	public ResponseUtils transferFunds(Integer beneficiaryAccountNumber, Integer accountNumber, BigDecimal amount) {
		LOG.info("fund transfer");
		ResponseUtils response = new ResponseUtils(Constants.STATUS_ERROR);
		try {
			if (ObjectUtils.isEmpty(beneficiaryAccountNumber) || ObjectUtils.isEmpty(accountNumber)) {
				response.setMessage("Beneficiary Account Number and Account Number can not be empty.");
				return response;
			}
			if (ObjectUtils.isEmpty(amount)) {
				response.setMessage("Please enter amount.");
				return response;
			}
			if (beneficiaryAccountNumber.equals(accountNumber)) {
				response.setMessage("You can not transfer funds to same account.");
				return response;
			}
			Account account = accountDao.findByAccountNumber(accountNumber);
			if (!ObjectUtils.isEmpty(account)) {
				Beneficiary beneficiary = beneficiaryDao.findByBeneficiaryAccountNumber(beneficiaryAccountNumber);
				if (beneficiary != null) {
					if (account.getBalance().compareTo(amount) < 0 || account.getBalance().compareTo(amount) < 1) {
						response.setMessage("Insufficient balance. Please load money to your registered account.");
					} else {
						Transaction transactionMaster = transactionDao.findByAccountNumber(accountNumber);
						Transaction beneficiaryTransactionMaster = transactionDao
								.findByAccountNumber(beneficiaryAccountNumber);
						if (ObjectUtils.isEmpty(transactionMaster)) {
							transactionMaster = buildTransactions(amount, accountNumber, "debit");
						} else {
							transactionMaster.setAmount(transactionMaster.getAmount().add(amount));
							transactionMaster.setDate(new Date());
						}
						if (ObjectUtils.isEmpty(beneficiaryTransactionMaster)) {
							beneficiaryTransactionMaster = buildTransactions(amount, beneficiaryAccountNumber,
									"credit");
						} else {
							beneficiaryTransactionMaster.setAmount(beneficiaryTransactionMaster.getAmount().add(amount));
							beneficiaryTransactionMaster.setDate(new Date());
						}
						account.setBalance(account.getBalance().subtract(amount));
						beneficiary.setBalance(beneficiary.getBalance().add(amount));
						
						transactionDao.save(transactionMaster);
						transactionDao.save(beneficiaryTransactionMaster);
						
						List<Transaction> addUpdateTransactions = null;
						if (account.getTransactions() == null) {
							addUpdateTransactions = new ArrayList<>();
						} else {
							addUpdateTransactions = account.getTransactions();
						}
						addUpdateTransactions.add(beneficiaryTransactionMaster);
						account.setTransactions(addUpdateTransactions);
						accountDao.save(account);
						
						List<Transaction> list = new ArrayList<>();
						list.add(transactionMaster);
						list.add(beneficiaryTransactionMaster);
						response.setStatus(Constants.STATUS_SUCCESS);
						response.setData(list);
						response.setMessage("Amount has been transferred successfully.");
					}
				} else {
					response.setMessage("Entered beneficiary account number is not added.");
				}
			} else {
				response.setMessage(Constants.ACCOUNT_NOT_REGISTERED);
			}
		} catch (

		Exception e) {
			LOG.error(e.getMessage());
			response.setMessage("Exception in fund transfer.");
		}
		return response;
	}

	@Override
	public ResponseUtils scheduleFunds(Integer beneficiaryAccountNumber, Integer accountNumber, BigDecimal amount,
			Timestamp datetime) {
		LOG.info("schedule funds");
		return scheduleTransferFundAtGivenTime(beneficiaryAccountNumber, accountNumber, amount, datetime);
	}

	@Scheduled(cron = "0 0 * * * *")
	private ResponseUtils scheduleTransferFundAtGivenTime(Integer beneficiaryAccountNumber, Integer accountNumber,
			BigDecimal amount, Timestamp datetime) {
		return transferFunds(beneficiaryAccountNumber, accountNumber, amount);
	}

	private Transaction buildTransactions(BigDecimal amount, Integer accountNumber, String type) {
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setDate(new Date());
		transaction.setType(type);
		transaction.setAccountNumber(accountNumber);
		return transaction;
	}
}
