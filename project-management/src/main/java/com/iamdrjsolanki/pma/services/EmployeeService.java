package com.iamdrjsolanki.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamdrjsolanki.pma.dao.EmployeeRepository;
import com.iamdrjsolanki.pma.dto.EmployeeProject;
import com.iamdrjsolanki.pma.entities.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository empRepo;
	
	public Employee save(Employee emp) {
		return empRepo.save(emp);
	}
	
	public List<Employee> getAll() {
		List<Employee> employeeList = empRepo.findAll();
		return employeeList;
	}

	public List<EmployeeProject> employeeProjects() {
		return empRepo.employeeProjects();
	}

}
