package com.cognitivescale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The Class BankApplicationTests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BankApplicationTests {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(BankApplicationTests.class);

	/**
	 * Context loads.
	 */
	@Test
	public void contextLoads() {
		LOG.info("Main class is tested");
	}

}
