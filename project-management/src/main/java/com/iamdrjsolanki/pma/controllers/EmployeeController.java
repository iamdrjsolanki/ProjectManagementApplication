package com.iamdrjsolanki.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String displayEmployeeForm(Model model) {
		Employee emp = new Employee();
		model.addAttribute("employee", emp);
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createEmployee(@Valid Employee emp, Model model, Errors errors) {
		if(errors.hasErrors())
			return "employees/new-employee";
		else {
			empServ.save(emp);
			return "redirect:/employees";
		}
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") Long id, Model model) {
		Employee emp = empServ.getEmployeeById(id);
		model.addAttribute("employee", emp);
		return "employees/new-employee";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") Long id, Model model) {
		empServ.deleteEmployeeById(id);
		return "redirect:/employees";
	}

}
