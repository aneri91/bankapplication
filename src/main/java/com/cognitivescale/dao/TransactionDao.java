package com.cognitivescale.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognitivescale.entity.Transaction;

@Repository
public interface TransactionDao extends MongoRepository<Transaction, Long> {

	@Query(value = "{ 'accountNumber' : ?0 }")
	List<Transaction> findByAccountNumber(Integer accountNumber);

	@Query(value = "{ 'accountNumber' : ?0 },{ 'fromDate' : ?1 },{ 'toDate' : ?1 }")
	List<Transaction> transactionDetailsByAccountAndDates(Integer accountNumber, Date fromDate, Date toDate);
}
