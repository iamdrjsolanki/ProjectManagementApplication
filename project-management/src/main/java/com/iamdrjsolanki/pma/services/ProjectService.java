package com.iamdrjsolanki.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamdrjsolanki.pma.dao.ProjectRepository;
import com.iamdrjsolanki.pma.dto.ChartData;
import com.iamdrjsolanki.pma.dto.TimelineData;
import com.iamdrjsolanki.pma.entities.Project;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository proRepo;
	
	public Project save(Project project) {
		return proRepo.save(project);
	}
	
	public List<Project> getAll() {
		List<Project> projectList = proRepo.findAll();
		return projectList;
	}
	
	public Project getProjectById(Long id) {
		return proRepo.findById(id).get();
	}
	
	public List<ChartData> getProjectStatus() {
		return proRepo.getProjectStatus();
	}
	
	public void deleteProjectById(Long id) {
		proRepo.deleteById(id);
	}
	
	public List<TimelineData> getProjectTimelines() {
		return proRepo.getProjectTimelines();
	}

}
