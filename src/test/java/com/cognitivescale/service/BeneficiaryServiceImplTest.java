package com.cognitivescale.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognitivescale.dao.BeneficiaryDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeneficiaryServiceImplTest {

	@Autowired
	private BeneficiaryService beneficiaryService;
	
	@MockBean
	private BeneficiaryDao beneficiaryDao;
}
