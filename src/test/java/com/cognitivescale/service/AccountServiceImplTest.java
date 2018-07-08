package com.cognitivescale.service;

import static org.assertj.core.api.Assertions.assertThat;

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
	public void setUp() {
		Account account = new Account();
		account.setUsername("aneri.parikh");
		account.setPassword("aneri");
		Mockito.when(accountDao.validateUser(account.getUsername(), account.getPassword())).thenReturn(account);
		System.out.println(account.getUsername());
	}

	@Test
	public void whenValidName_thenEmployeeShouldBeFound() {
		String username = "aneri.parikh";
		String password = "aneri";
		ResponseUtils validateUser = accountService.validateUser(username, password);
		System.out.println(validateUser.getData());
		System.out.println(validateUser.getMessage());
		assertThat(validateUser.getData()).isEqualTo("");
	}
}