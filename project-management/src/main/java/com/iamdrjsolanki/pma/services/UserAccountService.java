package com.iamdrjsolanki.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamdrjsolanki.pma.dao.UserAccountRepository;
import com.iamdrjsolanki.pma.entities.UserAccount;

@Service
public class UserAccountService {
	
	@Autowired
	UserAccountRepository userRepo;
	
	public UserAccount saveUser(UserAccount user) {
		return userRepo.save(user);
	}
	
//	public List<Project> getAll() {
//		List<Project> projectList = proRepo.findAll();
//		return projectList;
//	}
//	
//	public List<ChartData> getProjectStatus() {
//		return proRepo.getProjectStatus();
//	}

}
