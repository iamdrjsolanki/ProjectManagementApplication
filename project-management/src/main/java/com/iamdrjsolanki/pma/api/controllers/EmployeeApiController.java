package com.iamdrjsolanki.pma.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iamdrjsolanki.pma.entities.Employee;
import com.iamdrjsolanki.pma.services.EmployeeService;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {
	
	@Autowired
	EmployeeService empServ;
	
	@GetMapping
	public List<Employee> getEmployees() {
		return empServ.getAll();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		return empServ.getEmployeeById(id);
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@RequestBody @Valid Employee emp) {
		return empServ.save(emp);
	}
	
	
	//It deletes the dependencies with projects while updating, so patch verb is used
//	@PutMapping(path="/{id}", consumes="application/json")
//	@ResponseStatus(HttpStatus.OK)
//	public Employee updateEmployee(@RequestBody Employee emp) {
//		return empServ.save(emp);
//	}
	
	@PatchMapping(path="/{id}", consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee updateEmployee(@PathVariable Long id, @RequestBody @Valid Employee patchEmp) {
		Employee emp = empServ.getEmployeeById(id);
		
		if(patchEmp.getEmail() != null)
			emp.setEmail(patchEmp.getEmail());
		if(patchEmp.getFirstName() != null)
			emp.setFirstName(patchEmp.getFirstName());
		if(patchEmp.getLastName() != null)
			emp.setLastName(patchEmp.getLastName());
		
		return empServ.save(emp);
	}
	
	@DeleteMapping(path="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmployee(@PathVariable Long id) {
		try {
			empServ.deleteEmployeeById(id);
		} catch(EmptyResultDataAccessException e) {
			//e.printStackTrace();
		}
	}
	
	@GetMapping(params= {"page", "size"})
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Employee> findPaginatedEmployees(@RequestParam("page") int page, @RequestParam("size") int size) {
		Pageable pageAndSize = PageRequest.of(page, size);
		return empServ.getAllEmployeesPageSize(pageAndSize);
	}

}
