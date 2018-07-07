package com.cognitivescale.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognitivescale.entity.Account;

@Repository
public interface AccountDao extends MongoRepository<Account, Long> {

	@Query("{username: { $regex: ?0 } })")
	Account findByUsername(String username);

	@Query(value = "{ 'username' : ?0 },{ 'password' : ?1 }")
	Account validateUser(String username, String password);

	@Query(value = "{ 'accountNumber' : ?0 }")
	Account findByAccountNumber(Integer accountNumber);

}
