package com.example.wbdvprojectjavajpa.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.example.wbdvprojectjavajpa.models.User;
import com.example.wbdvprojectjavajpa.models.UserProjectAssociation;
import com.example.wbdvprojectjavajpa.repository.ProjectRepository;



@RestController
@CrossOrigin(origins = "*", maxAge=3600, allowCredentials = "true")
public class ProjectService {
	@Autowired
	ProjectRepository projRepo;
	
	@GetMapping("api/projects")
	public Iterable<Project> findAllProjects(){
		return projRepo.findAll();
	}
	
	@PostMapping("api/projects")
	public Project createProject(@RequestBody Project proj, HttpSession session) {
		if(proj == null) {
			return null;
		}
		// get the user
		User user = (User) session.getAttribute("currentUser");
		// make userProjAssociation
		UserProjectAssociation userProj = new UserProjectAssociation(user, proj, "owner");
		
		//Add userProjAssociation to list
		List<UserProjectAssociation> userProjList = new ArrayList<>();
		userProjList.add(userProj);
		
		// add list to project
		proj.setUsers(userProjList);
		
		return projRepo.save(proj);
	}
	
	@PutMapping("api/projects/{projectId}")
	public Project addProject(@PathVariable("projectId") int id, @RequestBody Project proj, HttpSession session) {
		Optional<Project> data = projRepo.findById(id);
		if(data.isPresent()) {
			Project project = data.get();
			User user = (User) session.getAttribute("currentUser");
			System.out.println(user.getId());
			UserProjectAssociation userProj = new UserProjectAssociation(user, project, "reader");
			List<UserProjectAssociation> userProjList = user.getProjects();
			userProjList.add(userProj);
		
			project.setUsers(userProjList);
			return projRepo.save(project);
			
		}
		return null;
		
//		if(proj == null) {
//			return null;
//		}
//		
//		User user = (User) session.getAttribute("currentUser");
//		UserProjectAssociation userProj = new UserProjectAssociation(user, proj, "reader");
//		
//		List<UserProjectAssociation> userProjList = user.getProjects();
//		userProjList.add(userProj);
//		
//		// add list to project
//		proj.setUsers(userProjList);
//		
//		return projRepo.save(proj);
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
