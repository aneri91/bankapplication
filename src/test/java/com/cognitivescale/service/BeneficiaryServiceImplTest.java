package com.cognitivescale.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognitivescale.dao.BeneficiaryDao;
import com.cognitivescale.entity.Beneficiary;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeneficiaryServiceImplTest {

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public BeneficiaryService beneficiaryService() {
			return new BeneficiaryServiceImpl();
		}
	}

	@MockBean
	private BeneficiaryDao beneficiaryDao;

	@Before
	public void setUp() {
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setAccountNumber(946786736);
		Mockito.when(beneficiaryDao.findByBeneficiaryAccountNumber(beneficiary.getAccountNumber()))
				.thenReturn(beneficiary);
	}

}
