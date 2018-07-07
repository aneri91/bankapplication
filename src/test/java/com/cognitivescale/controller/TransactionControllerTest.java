package com.cognitivescale.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionControllerTest {
	private static final Logger LOG = LoggerFactory.getLogger(TransactionControllerTest.class);

	@Autowired
	private TransactionController transactionController;

	@Test
	public void transactionControllerInitializedCorrectly() {
		LOG.info("transactionControllerInitializedCorrectly called");
		assertThat(transactionController).isNotNull();
	}
}