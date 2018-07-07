package com.cognitivescale.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Class Account.
 */
@Document(collection = "accounts")
public class Account {

	/** The id. */
	@Id
	private String id;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The country. */
	private String country;
	
	/** The phone number. */
	private Long phoneNumber;
	
	/** The created date. */
	private Date createdDate;
	
	/** The account type. */
	private String accountType;
	
	/** The role. */
	private String role;
	
	/** The account number. */
	private Integer accountNumber;
	
	/** The balance. */
	private BigDecimal balance;
	
	/** The currency. */
	private String currency;
	
	/** The beneficiaries. */
	private List<Beneficiary> beneficiaries;
	
	/** The beneficiaries list. */
	private List<Integer> beneficiariesList;
	
	/** The transactions. */
	private List<Transaction> transactions;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public Long getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
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
	 * Gets the balance.
	 *
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * Sets the balance.
	 *
	 * @param balance the new balance
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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

	/**
	 * Gets the beneficiaries.
	 *
	 * @return the beneficiaries
	 */
	public List<Beneficiary> getBeneficiaries() {
		return beneficiaries;
	}

	/**
	 * Sets the beneficiaries.
	 *
	 * @param beneficiaries the new beneficiaries
	 */
	public void setBeneficiaries(List<Beneficiary> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}

	/**
	 * Gets the beneficiaries list.
	 *
	 * @return the beneficiaries list
	 */
	public List<Integer> getBeneficiariesList() {
		return beneficiariesList;
	}

	/**
	 * Sets the beneficiaries list.
	 *
	 * @param beneficiariesList the new beneficiaries list
	 */
	public void setBeneficiariesList(List<Integer> beneficiariesList) {
		this.beneficiariesList = beneficiariesList;
	}

	/**
	 * Gets the transactions.
	 *
	 * @return the transactions
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * Sets the transactions.
	 *
	 * @param transactions the new transactions
	 */
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
