package com.cognitivescale.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognitivescale.dao.AccountDao;
import com.cognitivescale.entity.Account;
import com.cognitivescale.model.AccountModel;
import com.cognitivescale.util.Constants;
import com.cognitivescale.util.ObjectUtils;
import com.cognitivescale.util.PasswordUtils;
import com.cognitivescale.util.ResponseUtils;

/**
 * The Class AccountServiceImpl.
 */
@Service
public class AccountServiceImpl implements AccountService {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);

	/** The account dao. */
	@Autowired
	private AccountDao accountDao;

	/**
	 * Saves the account details.
	 *
	 * @param accountModel the account model
	 * @return the accountId
	 */
	@Override
	public ResponseUtils save(AccountModel accountModel) {
		LOG.info("create account");
		ResponseUtils response = new ResponseUtils(Constants.STATUS_ERROR);
		String password = accountModel.getPassword();
		String confirmpassword = accountModel.getConfirmPassword();
		try {
			Account account = accountDao.findByUsername(accountModel.getUsername());
			if (ObjectUtils.isEmpty(account)) {
				if (ObjectUtils.isEmpty(password) || ObjectUtils.isEmpty(confirmpassword)
						|| !confirmpassword.equals(password)) {
					response.setMessage("Password and confirm password must be same and not empty.");
					return response;
				}
				account = buildUserAccount(accountModel);
				accountDao.save(account);
				response.setData(account.getId());
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setMessage("Account is created successfully.");
			} else {
				response.setMessage("User name is already exist. Please register with new username.");
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
			response.setMessage("Exception in creating account.");
		}
		return response;
	}

	/**
	 * Finds by username.
	 *
	 * @param username
	 * @return the account details
	 */
	@Override
	public ResponseUtils findByUsername(String username) {
		LOG.info("find by username");
		ResponseUtils response = new ResponseUtils(Constants.STATUS_ERROR);
		try {
			Account account = accountDao.findByUsername(username);
			if (account != null) {
				response.setData(account);
				response.setStatus(Constants.STATUS_SUCCESS);
			}
			response.setStatus(Constants.STATUS_ERROR);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			response.setMessage("Exception in finding username.");
		}
		return response;
	}

	/**
	 * Validates user.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the account details
	 */
	@Override
	public ResponseUtils validateUser(String username, String password) {
		LOG.info("validate user");
		ResponseUtils response = new ResponseUtils(Constants.STATUS_ERROR);
		try {
			if (ObjectUtils.isEmpty(username) || ObjectUtils.isEmpty(password)) {
				response.setMessage("Username and password must not be empty or null.");
				return response;
			}
			Account account = accountDao.validateUser(username, PasswordUtils.encrypt(password.getBytes()));
			if (account != null) {
				response.setData(account);
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setMessage("User is already registered.");
			} else {
				response.setMessage("User is not registered. Please register to access bank services.");
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
			response.setMessage("Exception in validating user.");
		}
		return response;
	}

	/**
	 * Get balance info by account number.
	 *
	 * @param accountNumber
	 * @return the account balance
	 */
	@Override
	public ResponseUtils getBalanceInfoByAccountNumber(Integer accountNumber) {
		LOG.info("get balance info");
		ResponseUtils response = new ResponseUtils(Constants.STATUS_ERROR);
		try {
			if (ObjectUtils.isEmpty(accountNumber)) {
				response.setMessage("Account number must not be empty or null.");
				return response;
			}
			Account account = accountDao.findByAccountNumber(accountNumber);
			if (account != null) {
				response.setData(account.getBalance());
				response.setStatus(Constants.STATUS_SUCCESS);
				response.setMessage("Account balance is retrieved successfully.");
			} else {
				response.setMessage("Account is not found.");
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
			response.setMessage("Exception in retrieving balance.");
		}
		return response;
	}

	/**
	 * Calculates interest by account number.
	 *
	 * @param date          the date
	 * @param accountNumber the account number
	 * @return the account balance
	 */
	@Override
	public ResponseUtils calculateInterestByAccountNumber(String date, Integer accountNumber) {
		LOG.info("calculate interest by account number");
		ResponseUtils response = new ResponseUtils(Constants.STATUS_ERROR);
		try {
			if (ObjectUtils.isEmpty(accountNumber)) {
				response.setMessage("Account Number can not be empty.");
				return response;
			}
			Account account = accountDao.findByAccountNumber(accountNumber);
			if (!ObjectUtils.isEmpty(account)) {
				String[] splitedDate = date.split("-");
				Date givenDate = new SimpleDateFormat("dd MM yyyy")
						.parse(splitedDate[2] + " " + splitedDate[1] + " " + splitedDate[0]);
				String currentDate = new SimpleDateFormat("dd MM yyyy").format(new Date());
				Date currDate = new SimpleDateFormat("dd MM yyyy").parse(currentDate);
				if (givenDate.compareTo(currDate) >= 0) {
					long diff = givenDate.getTime() - currDate.getTime();
					float days = (diff / (1000 * 60 * 60 * 24));
					float calculatedInterest = (days * 4) / 36500;
					response.setData(account.getBalance().add(BigDecimal.valueOf(calculatedInterest)));
					response.setStatus(Constants.STATUS_SUCCESS);
					response.setMessage("Balance is calculated as per interest of 4%.");
				} else {
					response.setMessage("Given date should be greater than current date.");
				}

			} else {
				response.setMessage(Constants.ACCOUNT_NOT_REGISTERED);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
			response.setMessage("Exception in calculating interest.");
		}
		return response;
	}

	/**
	 * Builds the user account.
	 *
	 * @param accountModel the account model
	 * @return the account
	 */
	private Account buildUserAccount(AccountModel accountModel) {
		Account account = new Account();
		account.setUsername(accountModel.getUsername());
		account.setPassword(PasswordUtils.encrypt(accountModel.getPassword().getBytes()));
		account.setCountry(accountModel.getCountry() != null ? accountModel.getCountry() : "India");
		account.setPhoneNumber(accountModel.getPhoneNumber());
		account.setCreatedDate(new Date());
		account.setAccountType(accountModel.getAccountType() != null ? accountModel.getAccountType() : "Saving");
		account.setRole("CS_USER");
		Random random = new Random();
		account.setAccountNumber(random.nextInt(900000000) + 100000000);
		account.setBalance(accountModel.getBalance());
		account.setCurrency(accountModel.getCurrency() != null ? accountModel.getCurrency() : "INR");
		return account;
	}
}