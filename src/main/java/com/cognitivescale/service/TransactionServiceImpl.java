package com.cognitivescale.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

/**
 * The Class TransactionServiceImpl.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TransactionServiceImpl.class);

	/** The transaction dao. */
	@Autowired
	private TransactionDao transactionDao;

	/** The beneficiary dao. */
	@Autowired
	private BeneficiaryDao beneficiaryDao;

	/** The account dao. */
	@Autowired
	private AccountDao accountDao;

	/**
	 * Finds all transactions by accountNumber.
	 *
	 * @param accountNumber the account number
	 * @param fromDate      the from date
	 * @param toDate        the to date
	 * @return the transactionList
	 */
	@Override
	public ResponseUtils findAllTransactionsByAccountNumber(Integer accountNumber, Date fromDate, Date toDate) {
		LOG.info("find all transactions by account number");
		ResponseUtils response = new ResponseUtils(Constants.STATUS_ERROR);
		try {
			if (ObjectUtils.isEmpty(accountNumber)) {
				response.setMessage("Account Number can not be empty.");
				return response;
			}
			Account account = accountDao.findByAccountNumber(accountNumber);
			if (!ObjectUtils.isEmpty(account)) {
				List<Transaction> transactionList = transactionDao.transactionDetailsByAccountAndDates(accountNumber,
						fromDate, toDate);
				if (transactionList.size() > 0) {
					response.setData(transactionList);
					response.setStatus(Constants.STATUS_SUCCESS);
					response.setMessage("Transaction list is retrieved successfully.");
				} else {
					response.setMessage("No transactions exist for this account number.");
				}

			} else {
				response.setMessage(Constants.ACCOUNT_NOT_REGISTERED);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
			response.setMessage("Exception in getting statement.");
		}
		return response;
	}

	/**
	 * Transfers funds.
	 *
	 * @param beneficiaryAccountNumber the beneficiary account number
	 * @param accountNumber            the account number
	 * @param amount                   the amount
	 * @return the transactionList
	 */
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
						Transaction transactionMaster = buildTransactions(amount, accountNumber, "debit");
						Transaction beneficiaryTransactionMaster = buildTransactions(amount, beneficiaryAccountNumber,
								"credit");
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
		} catch (Exception e) {
			LOG.error(e.getMessage());
			response.setMessage("Exception in fund transfer.");
		}
		return response;
	}

	/**
	 * Schedule funds.
	 *
	 * @param beneficiaryAccountNumber the beneficiary account number
	 * @param accountNumber            the account number
	 * @param amount                   the amount
	 * @param datetime                 the datetime
	 * @return the response transactionList
	 */
	@Override
	public ResponseUtils scheduleFunds(Integer beneficiaryAccountNumber, Integer accountNumber, BigDecimal amount,
			String datetime) {
		LOG.info("schedule funds");
		ResponseUtils response = new ResponseUtils(Constants.STATUS_ERROR);
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			String currDate = dateFormat.format(new Date());
			Date currentDate = dateFormat.parse(currDate);
			Date givenDate = dateFormat.parse(datetime);
			long millis = givenDate.getTime() - currentDate.getTime();
			scheduleTransferFundAtGivenTime(beneficiaryAccountNumber, accountNumber, amount, millis);
			response.setStatus(Constants.STATUS_SUCCESS);
			response.setMessage("Transferring fund is scheduled at given time.");
		} catch (Exception e) {
			LOG.error(e.getMessage());
			response.setMessage("Exception in scheduling fund transfer.");
		}
		return response;
	}

	/**
	 * Schedule transfer fund at given time.
	 *
	 * @param beneficiaryAccountNumber the beneficiary account number
	 * @param accountNumber            the account number
	 * @param amount                   the amount
	 * @param millis                   the millis
	 * @return the response utils
	 */
	@Scheduled(cron = "0 15 10 15 * ?")
	private ResponseUtils scheduleTransferFundAtGivenTime(Integer beneficiaryAccountNumber, Integer accountNumber,
			BigDecimal amount, long millis) {
		LOG.info("schedule transfer funds at give time");
		return transferFunds(beneficiaryAccountNumber, accountNumber, amount);
	}

	/**
	 * Builds the transactions.
	 *
	 * @param amount        the amount
	 * @param accountNumber the account number
	 * @param type          the type
	 * @return the transaction
	 */
	private Transaction buildTransactions(BigDecimal amount, Integer accountNumber, String type) {
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setCreatedDate(new Date());
		transaction.setType(type);
		transaction.setAccountNumber(accountNumber);
		return transaction;
	}
}
