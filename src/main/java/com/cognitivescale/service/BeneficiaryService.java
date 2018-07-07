package com.cognitivescale.service;

import com.cognitivescale.model.BeneficiaryModel;
import com.cognitivescale.util.ResponseUtils;

public interface BeneficiaryService {
	ResponseUtils addBeneficiaryDetails(BeneficiaryModel beneficiaryModel);

	ResponseUtils deleteBeneficiaryDetails(Integer beneficiaryAccountNumber, Integer accountNumber);
}
