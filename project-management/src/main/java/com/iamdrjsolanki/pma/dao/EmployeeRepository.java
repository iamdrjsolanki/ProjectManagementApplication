package com.iamdrjsolanki.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.iamdrjsolanki.pma.dto.EmployeeProject;
import com.iamdrjsolanki.pma.entities.Employee;

//Spring Data Rest (RestRepositories in WebStarters) gives all the data as JSON without creating a RestController with all functionalities
@RepositoryRestResource(collectionResourceRel="apiemployees", path="apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
	
	@Override
	public List<Employee> findAll();
	
	@Query(nativeQuery = true, 
			value="select e.first_name as firstName, e.last_name as lastName, count(pe.employee_id) as projectCount"
			+ " from employee e "
			+ " left join project_employee pe on (pe.employee_id=e.employee_id)"
			+ " group by e.first_name, e.last_name"
			+ " order by 3 desc")
	public List<EmployeeProject> employeeProjects();

}
