package com.cognitivescale.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognitivescale.entity.Transaction;

/**
 * The Interface TransactionDao.
 */
@Repository
public interface TransactionDao extends MongoRepository<Transaction, Long> {

	/**
	 * Find by account number.
	 *
	 * @param accountNumber the account number
	 * @return the list
	 */
	@Query(value = "{ 'accountNumber' : ?0 }")
	List<Transaction> findByAccountNumber(Integer accountNumber);

	/**
	 * Transaction details by account and dates.
	 *
	 * @param accountNumber the account number
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @return the list
	 */
	@Query(value = "{ 'accountNumber' : ?0 },{ 'fromDate' : ?1 },{ 'toDate' : ?1 }")
	List<Transaction> transactionDetailsByAccountAndDates(Integer accountNumber, Date fromDate, Date toDate);
}
