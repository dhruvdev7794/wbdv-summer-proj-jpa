package com.example.wbdvprojectjavajpa.service;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
@CrossOrigin(origins = "*", maxAge=3600, allowCredentials = "true")
public class ImageService {
	@Autowired
	ImageRepository imageRepo;

	@Autowired
	ProjectRepository projectRepo;
	
	@GetMapping("api/projects/{projectId}/images")
	public Iterable<Image> findAllImages(@PathVariable("projectId") int projectId){
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
			try {
				File file = File.createTempFile(image.getName(), ".png", new File("./src/image"));
				FileOutputStream out = new FileOutputStream(file); 
				out.write(contents);
				out.close();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			image.setContents(contents);
			return imageRepo.save(image);
		}
		return null;
	}
	
	@GetMapping("api/image/{imageId}")
	public byte[] getImage(@PathVariable("imageId") int imageId, HttpServletResponse response) {
		Optional<Image> data = imageRepo.findById(imageId);
		if(data.isPresent()) {
			Image image = data.get();
//			return System.getProperty("user.dir")+"/src/image/"+image.getName();
			byte[] contents =  image.getContents();
			response.setContentType(image.getMimeType());
			try {
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(contents);
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}
}
