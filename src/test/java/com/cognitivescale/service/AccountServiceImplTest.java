package com.cognitivescale.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognitivescale.dao.AccountDao;
import com.cognitivescale.entity.Account;
import com.cognitivescale.model.AccountModel;
import com.cognitivescale.util.PasswordUtils;
import com.cognitivescale.util.ResponseUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {

	@TestConfiguration
	static class AccountServiceImplTestContextConfiguration {

		@Bean
		public AccountService accountService() {
			return new AccountServiceImpl();
		}
	}

	@Autowired
	private AccountService accountService;

	@MockBean
	private AccountDao accountDao;

	@Before
	public void setUpAccountDetails() {

		AccountModel accountModel = buildAccountModel();

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

		Mockito.when(accountDao.save(account)).thenReturn(account);
		Mockito.when(accountDao.validateUser(accountModel.getUsername(), accountModel.getPassword()))
				.thenReturn(account);
	}

	@Test
	public void createAccountDetailsIfUsernameNotExist() {
		AccountModel accountModel = buildAccountModel();
		ResponseUtils responseUtils = accountService.save(accountModel);
		assertThat(responseUtils.getMessage()).isEqualTo("Account is created successfully.");
	}

	@Test
	public void loginWithUsernameAndPassword() {
		String username = "aneri.parikh";
		String password = "aneri";
		ResponseUtils responseUtils = accountService.validateUser(username, PasswordUtils.encrypt(password.getBytes()));
		assertThat(responseUtils.getMessage()).isEqualTo("User is already registered.");
	}

	private AccountModel buildAccountModel() {
		AccountModel accountModel = new AccountModel();
		accountModel.setUsername("aneri.parikh");
		accountModel.setPassword("aneri");
		accountModel.setConfirmPassword("aneri");
		accountModel.setCountry("India");
		accountModel.setPhoneNumber(9725575554L);
		accountModel.setAccountType("Current");
		accountModel.setBalance(BigDecimal.TEN);
		return accountModel;
	}
}