package com.cognitivescale.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognitivescale.entity.Beneficiary;

/**
 * The Interface BeneficiaryDao.
 */
@Repository
public interface BeneficiaryDao extends MongoRepository<Beneficiary, Long> {

	/**
	 * Find by beneficiary account number.
	 *
	 * @param beneficiaryAccountNumber the beneficiary account number
	 * @return the beneficiary
	 */
	@Query(value = "{ 'beneficiaryAccountNumber' : ?0 }")
	Beneficiary findByBeneficiaryAccountNumber(Integer beneficiaryAccountNumber);

	/**
	 * Find by account numbers.
	 *
	 * @param beneficiaryAccountNumber the beneficiary account number
	 * @param accountNumber the account number
	 * @return the beneficiary
	 */
	@Query(value = "{ 'beneficiaryAccountNumber' : ?0 },{ 'accountNumber' : ?1 }")
	Beneficiary findByAccountNumbers(Integer beneficiaryAccountNumber, Integer accountNumber);
}
