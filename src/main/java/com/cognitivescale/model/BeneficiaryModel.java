package com.cognitivescale.model;

import java.util.Date;

/**
 * The Class BeneficiaryModel.
 */
public class BeneficiaryModel {

	/** The currency. */
	private String beneficiaryName, ifscCode, nickName, accountType, currency;

	/** The beneficiary account number. */
	private Integer beneficiaryAccountNumber;

	/** The account number. */
	private Integer accountNumber;

	/** The created date. */
	private Date createdDate;

	/**
	 * Gets the beneficiary name.
	 *
	 * @return the beneficiary name
	 */
	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	/**
	 * Sets the beneficiary name.
	 *
	 * @param beneficiaryName the new beneficiary name
	 */
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	/**
	 * Gets the beneficiary account number.
	 *
	 * @return the beneficiary account number
	 */
	public Integer getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}

	/**
	 * Sets the beneficiary account number.
	 *
	 * @param beneficiaryAccountNumber the new beneficiary account number
	 */
	public void setBeneficiaryAccountNumber(Integer beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}

	/**
	 * Gets the ifsc code.
	 *
	 * @return the ifsc code
	 */
	public String getIfscCode() {
		return ifscCode;
	}

	/**
	 * Sets the ifsc code.
	 *
	 * @param ifscCode the new ifsc code
	 */
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	/**
	 * Gets the nick name.
	 *
	 * @return the nick name
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * Sets the nick name.
	 *
	 * @param nickName the new nick name
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * Gets the account type.
	 *
	 * @return the account type
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * Sets the account type.
	 *
	 * @param accountType the new account type
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * Gets the account number.
	 *
	 * @return the account number
	 */
	public Integer getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Sets the account number.
	 *
	 * @param accountNumber the new account number
	 */
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the currency.
	 *
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Sets the currency.
	 *
	 * @param currency the new currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
