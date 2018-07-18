package com.example.wbdvprojectjavajpa.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wbdvprojectjavajpa.models.Image;
import com.example.wbdvprojectjavajpa.models.Project;
import com.example.wbdvprojectjavajpa.repository.ImageRepository;
import com.example.wbdvprojectjavajpa.repository.ProjectRepository;

@RestController
@CrossOrigin(origins = "*")
public class ImageService {
	@Autowired
	ImageRepository imageRepo;

	@Autowired
	ProjectRepository projectRepo;
	
	@GetMapping("api/projects/{projectId}/images")
	public Iterable<Image> findAllProjects(@PathVariable("projectId") int projectId){
		Optional<Project> data = projectRepo.findById(projectId);
		if(data.isPresent()) {
			Project project = data.get();
//			File file = new File("./src/image/hi.txt");
//			//Create the file
//			try {
//				if (file.createNewFile()){
//				System.out.println("File is created!");
//				}else{
//				System.out.println("File already exists.");
//				}
//				//Write Content
//				FileWriter writer = new FileWriter(file);
//				writer.write("Test data");
//				writer.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			 
			
			return project.getImages();
		}
		return null;
	}
	
	@PostMapping("api/projects/{projectId}/images")
	public Image createImage(@PathVariable("projectId") int projectId, @RequestBody Image image) {
		Optional<Project> data = projectRepo.findById(projectId);
		if(data.isPresent()) {
			Project project = data.get();
			image.setProject(project);
			return imageRepo.save(image);
		}
		return null;
	}
	
	@PutMapping("api/image/{imageId}")
	public Image updateImage(@PathVariable("imageId") int imageId, @RequestBody byte[] contents) {
		Optional<Image> data = imageRepo.findById(imageId);
		if(data.isPresent()) {
			Image image = data.get();
			image.setContents(contents);
			return imageRepo.save(image);
		}
		return null;
	}
}
