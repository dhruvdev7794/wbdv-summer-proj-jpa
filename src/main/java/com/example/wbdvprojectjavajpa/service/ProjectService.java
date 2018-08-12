package com.example.wbdvprojectjavajpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@DeleteMapping("api/projects/{projectId}")
	public void deleteProject(@PathVariable("projectId") int id) {
		projRepo.deleteById(id);
	}
	
	@GetMapping("api/projects/{projectId}")
	public Optional<Project> findProjectById(@PathVariable("projectId") int id) {
		return projRepo.findById(id);
	}
	
}
