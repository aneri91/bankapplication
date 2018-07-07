package com.cognitivescale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognitivescale.dao.AccountDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankApplicationTests {

	@MockBean
	private AccountDao accountDao;

	@Test
	public void contextLoads() {
		System.out.println(accountDao);
	}

}
