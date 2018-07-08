package com.cognitivescale.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cognitivescale.entity.Account;
import com.cognitivescale.model.AccountModel;
import com.cognitivescale.service.AccountService;
import com.cognitivescale.util.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
	private static final Logger LOG = LoggerFactory.getLogger(AccountControllerTest.class);

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AccountService accountService;

	@Test
	public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {
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

//		given(accountService.save(accountModel)).willReturn("");
//		Object service;
//		Object allEmployees;
//		given("").willReturn("");
//
//		mockMvc.perform(post("/account/create_account").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].name", is(alex.getName())));
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
