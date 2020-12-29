package com.iamdrjsolanki.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iamdrjsolanki.pma.dao.EmployeeRepository;
import com.iamdrjsolanki.pma.dao.ProjectRepository;
import com.iamdrjsolanki.pma.dto.ChartData;
import com.iamdrjsolanki.pma.dto.EmployeeProject;
import com.iamdrjsolanki.pma.entities.Employee;
import com.iamdrjsolanki.pma.entities.Project;

@Controller
public class HomeController {
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/home")
	public String displayHome(Model model) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();
		
		List<Project> projectList = proRepo.findAll();
		model.addAttribute("projectList", projectList);
		
		List<ChartData> projectData = proRepo.getProjectStatus();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		model.addAttribute("projectStatusCount", jsonString);
		
		List<EmployeeProject> employeeProjectCountList = empRepo.employeeProjects();
		model.addAttribute("employeeProjectCountList", employeeProjectCountList);
		
		return "main/home";
	}

}
