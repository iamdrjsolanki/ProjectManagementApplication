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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iamdrjsolanki.pma.dto.ChartData;
import com.iamdrjsolanki.pma.dto.TimelineData;
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
	public String createProject(@Valid Project project, Model model, Errors errors) {
		if(errors.hasErrors())
			return "projects/new-project";
		proServ.save(project);
		return "redirect:/projects";
	}
	
	@GetMapping("/update")
	public String displayProjectUpdateForm(@RequestParam("id") Long id, Model model) {
		Project pro = proServ.getProjectById(id);
		List<Employee> employeeList = empServ.getAll();
		model.addAttribute("project", pro);
		model.addAttribute("employeeList", employeeList);
		return "projects/new-project";
	}
	
	@GetMapping("/delete")
	public String deleteProject(@RequestParam("id") Long id, Model model) {
		proServ.deleteProjectById(id);
		return "redirect:/projects";
	}
	
	@GetMapping("/timelines")
	public String displayProjectTimelines(Model model) throws JsonProcessingException {
		List<TimelineData> projectTimelineData = proServ.getProjectTimelines();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTimelineString = objectMapper.writeValueAsString(projectTimelineData);
		model.addAttribute("projectTimelineData", jsonTimelineString);
		System.out.println("jsonTimelineData"+jsonTimelineString);
		return "projects/project-timelines";
	}

}
