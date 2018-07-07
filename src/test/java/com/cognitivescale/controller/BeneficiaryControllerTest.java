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
public class BeneficiaryControllerTest {
	private static final Logger LOG = LoggerFactory.getLogger(BeneficiaryControllerTest.class);

	@Autowired
	private BeneficiaryController beneficiaryController;

	@Test
	public void beneficiaryControllerInitializedCorrectly() {
		LOG.info("beneficiaryControllerInitializedCorrectly called");
		assertThat(beneficiaryController).isNotNull();
	}
}