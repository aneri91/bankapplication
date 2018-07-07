package com.cognitivescale.service;

import com.cognitivescale.model.BeneficiaryModel;
import com.cognitivescale.util.ResponseUtils;

/**
 * The Interface BeneficiaryService.
 */
public interface BeneficiaryService {
	
	/**
	 * Adds the beneficiary details.
	 *
	 * @param beneficiaryModel the beneficiary model
	 * @return the response utils
	 */
	ResponseUtils addBeneficiaryDetails(BeneficiaryModel beneficiaryModel);

	/**
	 * Delete beneficiary details.
	 *
	 * @param beneficiaryAccountNumber the beneficiary account number
	 * @param accountNumber the account number
	 * @return the response utils
	 */
	ResponseUtils deleteBeneficiaryDetails(Integer beneficiaryAccountNumber, Integer accountNumber);
}
