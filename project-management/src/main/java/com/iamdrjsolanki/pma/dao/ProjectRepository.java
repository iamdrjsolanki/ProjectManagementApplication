package com.iamdrjsolanki.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.iamdrjsolanki.pma.dto.ChartData;
import com.iamdrjsolanki.pma.dto.TimelineData;
import com.iamdrjsolanki.pma.entities.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
	
	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery = true, value = "SELECT stage as label, count(project_id) as value"
			+ " FROM PROJECT "
			+ " group by  stage"
			+ " order by 2 desc")
	public List<ChartData> getProjectStatus();
	
	@Query(nativeQuery = true, value = "SELECT name, start_date as startDate, end_date as endDate FROM PROJECT where start_date is not null")
	public List<TimelineData> getProjectTimelines();

}
