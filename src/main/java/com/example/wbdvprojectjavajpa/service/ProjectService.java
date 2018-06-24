package com.example.wbdvprojectjavajpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wbdvprojectjavajpa.models.Project;
import com.example.wbdvprojectjavajpa.repository.ProjectRepository;

@RestController
public class ProjectService {
	@Autowired
	ProjectRepository projRepo;
	
	@GetMapping("api/projects")
	public Iterable<Project> findAllProjects(){
		return projRepo.findAll();
	}
}
