package com.iamdrjsolanki.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iamdrjsolanki.pma.entities.Employee;
import com.iamdrjsolanki.pma.entities.Project;
import com.iamdrjsolanki.pma.services.EmployeeService;
import com.iamdrjsolanki.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService proServ;
	
	@Autowired
	EmployeeService empServ;
	
	@GetMapping("")
	public String displayProjectList(Model model) {
		List<Project> projectList = proServ.getAll();
		model.addAttribute("projectList", projectList);
		return "projects/project-list";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project project = new Project();
		List<Employee> employeeList = empServ.getAll();
		model.addAttribute("project", project);
		model.addAttribute("employeeList", employeeList);
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) {
		proServ.save(project);		
		return "redirect:/projects";
	}

}
