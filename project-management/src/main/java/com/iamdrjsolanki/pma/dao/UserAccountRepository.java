package com.iamdrjsolanki.pma.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.iamdrjsolanki.pma.entities.UserAccount;

@Repository
public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long>{
	
	

}
