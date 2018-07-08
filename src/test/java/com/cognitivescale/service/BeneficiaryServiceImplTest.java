package com.cognitivescale.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognitivescale.dao.BeneficiaryDao;
import com.cognitivescale.entity.Beneficiary;

/**
 * The Class BeneficiaryServiceImplTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BeneficiaryServiceImplTest {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(BeneficiaryServiceImplTest.class);

	/**
	 * The Class EmployeeServiceImplTestContextConfiguration.
	 */
	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		/**
		 * Beneficiary service.
		 *
		 * @return the beneficiary service
		 */
		@Bean
		public BeneficiaryService beneficiaryService() {
			return new BeneficiaryServiceImpl();
		}
	}

	/** The beneficiary dao. */
	@MockBean
	private BeneficiaryDao beneficiaryDao;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setAccountNumber(946786736);
		Mockito.when(beneficiaryDao.findByBeneficiaryAccountNumber(beneficiary.getAccountNumber()))
				.thenReturn(beneficiary);
	}

	/**
	 * Test beneficiary APIs.
	 */
	@Test
	public void testBeneficiaryAPIs() {
		LOG.info("testBeneficiaryAPIs called");
	}

}
