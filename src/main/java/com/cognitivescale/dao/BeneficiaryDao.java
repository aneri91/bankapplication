package com.cognitivescale.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognitivescale.entity.Beneficiary;

@Repository
public interface BeneficiaryDao extends MongoRepository<Beneficiary, Long> {

	@Query(value = "{ 'beneficiaryAccountNumber' : ?0 }")
	Beneficiary findByBeneficiaryAccountNumber(Integer beneficiaryAccountNumber);

	@Query(value = "{ 'beneficiaryAccountNumber' : ?0 },{ 'accountNumber' : ?1 }")
	Beneficiary findByAccountNumbers(Integer beneficiaryAccountNumber, Integer accountNumber);
}
