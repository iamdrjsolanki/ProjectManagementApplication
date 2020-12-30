package com.iamdrjsolanki.pma.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iamdrjsolanki.pma.dao.ProjectRepository;
import com.iamdrjsolanki.pma.entities.Project;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class HttpRequestTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void homePageReturnsVersionNumberCorrectly_thenSuccess() {
		String renderedHtml = this.restTemplate.getForObject("http://localhost:"+this.port+"/home", String.class);
		assertEquals(renderedHtml.contains("Project"), true);
	}

}
