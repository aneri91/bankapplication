package com.cognitivescale.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cognitivescale.entity.Beneficiary;
import com.cognitivescale.model.BeneficiaryModel;
import com.cognitivescale.service.BeneficiaryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeneficiaryControllerTest {
	private static final Logger LOG = LoggerFactory.getLogger(BeneficiaryControllerTest.class);

	@Autowired
	private BeneficiaryController beneficiaryController;

	@Autowired
	private BeneficiaryService beneficiaryService;

	private MockMvc mockMvc;

//	@Test
	public void beneficiaryControllerInitializedCorrectly() {
		LOG.info("beneficiaryControllerInitializedCorrectly called");
		assertThat(beneficiaryController).isNotNull();
	}

	@Test
	public void testAddBeneficiaryDetails() {
		Beneficiary beneficiary = mock(Beneficiary.class);
		BeneficiaryModel beneficiaryModel = new BeneficiaryModel();

		when(beneficiary.getBeneficiaryName()).thenReturn("Aneri Parikh");
		when(beneficiary.getIfscCode()).thenReturn("ICIC0001234");
		when(beneficiary.getNickName()).thenReturn("Anu");
		when(beneficiary.getAccountType()).thenReturn("Saving");
		when(beneficiary.getCurrency()).thenReturn("INR");
		when(beneficiary.getBeneficiaryAccountNumber()).thenReturn(121211);
		when(beneficiary.getAccountNumber()).thenReturn(946786736);

		beneficiaryModel.setBeneficiaryName(beneficiary.getBeneficiaryName());
		beneficiaryModel.setBeneficiaryAccountNumber(beneficiary.getBeneficiaryAccountNumber());
		beneficiaryModel.setIfscCode(beneficiary.getIfscCode());
		beneficiaryModel.setNickName(beneficiary.getNickName());
		beneficiaryModel.setAccountType(beneficiary.getAccountType());
		beneficiaryModel.setAccountNumber(beneficiary.getAccountNumber());

		beneficiaryService.addBeneficiaryDetails(beneficiaryModel);
		assertEquals("Aneri Parikh", beneficiary.getAccountNumber());
		assertEquals(beneficiaryModel.getBeneficiaryAccountNumber(), beneficiary.getBeneficiaryAccountNumber());
	}
}