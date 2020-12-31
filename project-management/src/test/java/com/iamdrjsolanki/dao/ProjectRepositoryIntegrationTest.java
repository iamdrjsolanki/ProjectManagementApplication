package com.iamdrjsolanki.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iamdrjsolanki.pma.ProjectManagementApplication;
import com.iamdrjsolanki.pma.dao.ProjectRepository;
import com.iamdrjsolanki.pma.entities.Project;

//the package is out of the root package that's why use @ContextConfiguration to let spring know where to start
@ContextConfiguration(classes = ProjectManagementApplication.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
@SqlGroup({@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"}),
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:drop.sql")})
public class ProjectRepositoryIntegrationTest {
	
	@Autowired
	ProjectRepository proRepo;
	
	@Test
	public void ifNewProjectSave_thenSuccess() {
		Project p = new Project("New Test Project", "COMPLETE", "Test Description");
		proRepo.save(p);
		
		assertEquals(5, proRepo.findAll().size());
	}

}
