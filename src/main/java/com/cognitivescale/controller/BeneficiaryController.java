package com.cognitivescale.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognitivescale.model.BeneficiaryModel;
import com.cognitivescale.service.BeneficiaryService;
import com.cognitivescale.util.ResponseUtils;

import io.swagger.annotations.Api;

/**
 * The Class BeneficiaryController.
 */
@RestController
@RequestMapping("beneficiary")
@Api(value = "beneficiary")
public class BeneficiaryController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(BeneficiaryController.class);

	/** The beneficiary service. */
	@Autowired
	private BeneficiaryService beneficiaryService;

	/**
	 * Adds the beneficiary.
	 *
	 * @param beneficiaryModel the beneficiary model
	 * @return the response utils
	 */
	@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
	public ResponseUtils addBeneficiary(@RequestBody BeneficiaryModel beneficiaryModel) {
		LOG.info("beneficiary/add called");
		return beneficiaryService.addBeneficiaryDetails(beneficiaryModel);
	}

	/**
	 * Delete beneficiary.
	 *
	 * @param beneficiaryAccountNumber the beneficiary account number
	 * @param accountNumber the account number
	 * @return the response utils
	 */
	@RequestMapping(value = { "/delete" }, method = RequestMethod.DELETE)
	public ResponseUtils deleteBeneficiary(@Param(value = "beneficiaryAccountNumber") Integer beneficiaryAccountNumber,
			@Param(value = "accountNumber") Integer accountNumber) {
		LOG.info("beneficiary/delete called");
		return beneficiaryService.deleteBeneficiaryDetails(beneficiaryAccountNumber, accountNumber);
	}
}