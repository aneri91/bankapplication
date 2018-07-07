package com.cognitivescale.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognitivescale.service.BeneficiaryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeneficiaryControllerTest {
	private static final Logger LOG = LoggerFactory.getLogger(BeneficiaryControllerTest.class);

	@Autowired
	private BeneficiaryController beneficiaryController;

	private MockMvc mockMvc;

	@Test
	public void beneficiaryControllerInitializedCorrectly() {
		LOG.info("beneficiaryControllerInitializedCorrectly called");
		assertThat(beneficiaryController).isNotNull();
	}

	@Test
	public void verifySaveToDo() throws Exception {
		BeneficiaryService beneficiaryController = mock(BeneficiaryService.class);
//		when(beneficiaryController.addBeneficiaryDetails(""))).thenReturn(new int[] { 24, 15, 3 });

		mockMvc.perform(MockMvcRequestBuilders.post("/beneficiary/add/").content(
				"{\\r\\n\\t\\\"beneficiaryName\\\": \\\"Mita\\\",\\r\\n\\t\\\"beneficiaryAccountNumber\\\": 121211,\\r\\n\\t\\\"ifscCode\\\": \\\"ICICI123\\\",\\r\\n\\t\\\"nickName\\\": \\\"Mitu\\\",\\r\\n\\t\\\"accountType\\\": \\\"Current\\\",\\r\\n\\t\\\"accountNumber\\\": 946786736\\r\\n}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.text").exists()).andExpect(jsonPath("$.completed").exists())
				.andExpect(jsonPath("$.text").value("New ToDo Sample")).andExpect(jsonPath("$.completed").value(false))
				.andDo(print());
	}
}