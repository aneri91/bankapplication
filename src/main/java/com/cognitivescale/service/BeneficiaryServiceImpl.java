package com.cognitivescale.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognitivescale.dao.AccountDao;
import com.cognitivescale.dao.BeneficiaryDao;
import com.cognitivescale.entity.Account;
import com.cognitivescale.entity.Beneficiary;
import com.cognitivescale.model.BeneficiaryModel;
import com.cognitivescale.util.Constants;
import com.cognitivescale.util.ObjectUtils;
import com.cognitivescale.util.ResponseUtils;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
	private static final Logger LOG = LoggerFactory.getLogger(BeneficiaryServiceImpl.class);

	@Autowired
	private BeneficiaryDao beneficiaryDao;

	@Autowired
	private AccountDao accountDao;

	@Override
	public ResponseUtils addBeneficiaryDetails(BeneficiaryModel beneficiaryModel) {
		LOG.info("add beneficiary : " + beneficiaryModel.getBeneficiaryAccountNumber());
		ResponseUtils response = new ResponseUtils(Constants.STATUS_ERROR);
		try {
			Account account = accountDao.findByAccountNumber(beneficiaryModel.getAccountNumber());
			if (!ObjectUtils.isEmpty(account)) {
				Beneficiary beneficiary = beneficiaryDao.findByAccountNumbers(
						beneficiaryModel.getBeneficiaryAccountNumber(), beneficiaryModel.getAccountNumber());
				if (ObjectUtils.isEmpty(beneficiary)) {
					beneficiary = buildBeneficiary(beneficiaryModel);
					beneficiaryDao.save(beneficiary);
					List<Integer> addUpdateBeneficiaries = null;
					if (account.getBeneficiariesList() == null) {
						addUpdateBeneficiaries = new ArrayList<>();
					} else {
						addUpdateBeneficiaries = account.getBeneficiariesList();
					}
					addUpdateBeneficiaries.add(beneficiary.getBeneficiaryAccountNumber());
					account.setBeneficiariesList(addUpdateBeneficiaries);
					accountDao.save(account);
					response.setData(beneficiary);
					response.setStatus(Constants.STATUS_SUCCESS);
					response.setMessage("Beneficiary is added successfully.");
				} else {
					response.setMessage("Beneficiary is already added.");
				}
			} else {
				response.setMessage(Constants.ACCOUNT_NOT_REGISTERED);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
			response.setMessage("Exception in adding beneficiary.");
		}
		return response;
	}

	@Override
	public ResponseUtils deleteBeneficiaryDetails(Integer beneficiaryAccountNumber, Integer accountNumber) {
		LOG.info("remove beneficiary : " + beneficiaryAccountNumber);
		ResponseUtils response = new ResponseUtils(Constants.STATUS_ERROR);
		try {
			if (ObjectUtils.isEmpty(beneficiaryAccountNumber) || ObjectUtils.isEmpty(accountNumber)
					|| beneficiaryAccountNumber.equals(accountNumber)) {
				response.setMessage("Beneficiary Account Number and Account Number can not be same and empty.");
				return response;
			}
			Account account = accountDao.findByAccountNumber(accountNumber);
			if (!ObjectUtils.isEmpty(account)) {
				Beneficiary beneficiary = beneficiaryDao.findByAccountNumbers(beneficiaryAccountNumber, accountNumber);
				if (!ObjectUtils.isEmpty(beneficiary)) {
					if (beneficiary.getAccountNumber().equals(account.getAccountNumber())) {
						beneficiaryDao.delete(beneficiary);
						if (account.getBeneficiariesList() != null) {
//						List<Integer> beneficiaries = account.getBeneficiariesList();
//						for (Integer beneficiaryAccNumber : beneficiaries) {
//							
//							account.setBeneficiariesList(beneficiaries);
//						}
						}
						response.setStatus(Constants.STATUS_SUCCESS);
						response.setMessage("Beneficiary is deleted successfully.");
					} else {
						response.setMessage("There is no account number in Account Table.");
					}
				} else {
					response.setMessage("Beneficiary Number does not exist.");
				}
			} else {
				response.setMessage(Constants.ACCOUNT_NOT_REGISTERED);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
			response.setMessage("Exception in removing beneficiary.");
		}
		return response;
	}

	private Beneficiary buildBeneficiary(BeneficiaryModel beneficiaryModel) {
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setBeneficiaryName(beneficiaryModel.getName());
		beneficiary.setBeneficiaryAccountNumber(beneficiaryModel.getBeneficiaryAccountNumber());
		beneficiary.setIfscCode(beneficiaryModel.getIfscCode());
		beneficiary.setNickName(beneficiaryModel.getNickName());
		beneficiary.setAccountType(
				beneficiaryModel.getAccountType() != null ? beneficiaryModel.getAccountType() : "Saving");
		beneficiary.setAccountNumber(beneficiaryModel.getAccountNumber());
		beneficiary.setBalance(BigDecimal.ZERO);
		beneficiary.setCurrency(beneficiaryModel.getCurrency() != null ? beneficiaryModel.getCurrency() : "INR");
		beneficiary.setCreatedDate(new Date());
		return beneficiary;
	}
}
