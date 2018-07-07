package com.cognitivescale.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognitivescale.entity.Account;

/**
 * The Interface AccountDao.
 */
@Repository
public interface AccountDao extends MongoRepository<Account, Long> {

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the account
	 */
	@Query("{username: { $regex: ?0 } })")
	Account findByUsername(String username);

	/**
	 * Validate user.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the account
	 */
	@Query(value = "{ 'username' : ?0 },{ 'password' : ?1 }")
	Account validateUser(String username, String password);

	/**
	 * Find by account number.
	 *
	 * @param accountNumber the account number
	 * @return the account
	 */
	@Query(value = "{ 'accountNumber' : ?0 }")
	Account findByAccountNumber(Integer accountNumber);

}
