package com.example.wbdvprojectjavajpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wbdvprojectjavajpa.models.Project;
import com.example.wbdvprojectjavajpa.repository.ProjectRepository;

@RestController
@CrossOrigin(origins = "*")
public class ProjectService {
	@Autowired
	ProjectRepository projRepo;
	
	@GetMapping("api/projects")
	public Iterable<Project> findAllProjects(){
		return projRepo.findAll();
	}
	
	@PostMapping("api/projects")
	public Project createProject(@RequestBody Project proj) {
		if(proj == null) {
			return null;
		}
		return projRepo.save(proj);
	}
}
