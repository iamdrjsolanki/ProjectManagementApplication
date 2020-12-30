package com.iamdrjsolanki.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iamdrjsolanki.pma.entities.Employee;
import com.iamdrjsolanki.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService empServ;
	
	@GetMapping("")
	public String displayEmployeeList(Model model) {
		List<Employee> employeeList = empServ.getAll();
		model.addAttribute("employeeList", employeeList);
		return "employees/employee-list";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Employee emp = new Employee();
		model.addAttribute("emp", emp);
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createProject(Employee emp, Model model) {
		empServ.save(emp);
		return "redirect:/employees";
	}

}
