package com.iamdrjsolanki.pma.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iamdrjsolanki.pma.entities.Project;
import com.iamdrjsolanki.pma.services.ProjectService;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {
	
	@Autowired
	ProjectService proServ;
	
	@GetMapping
	public List<Project> getProjects() {
		return proServ.getAll();
	}
	
	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") Long id) {
		return proServ.getProjectById(id);
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project createProject(@RequestBody Project pro) {
		return proServ.save(pro);
	}
	
	//It deletes the dependencies with employees while updating, so patch verb is used	
//	@PutMapping(path="/{id}", consumes="application/json")
//	@ResponseStatus(HttpStatus.OK)
//	public Employee updateEmployee(@RequestBody Employee emp) {
//		return empServ.save(emp);
//	}
	
	@PatchMapping(value="/{id}", consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Project updateProject(@PathVariable("id") Long id, @RequestBody Project patchPro) {
		Project pro = proServ.getProjectById(id);
		if(patchPro.getDescription() != null)
			pro.setDescription(patchPro.getDescription());
		if(patchPro.getName() != null)
			pro.setName(patchPro.getName());
		if(patchPro.getStage() != null)
			pro.setStage(patchPro.getStage());
		return proServ.save(pro);
	}
	
	@DeleteMapping(path="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProject(@PathVariable Long id) {
		try {
			proServ.deleteProjectById(id);
		} catch(EmptyResultDataAccessException e) {
			//e.printStackTrace();
		}
	}

}
