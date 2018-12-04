package com.techtest.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.techtest.entity.UserRegistration;

@Transactional(value="userTransactionManager")
@Repository
public interface UserRegistrationRepository extends PagingAndSortingRepository<UserRegistration, String> {
	
	UserRegistration findUserRegistrationByssn(@Param("ssn") String ssn);
	
	@Override
	List<UserRegistration> findAll();

}
