package com.example.wbdvprojectjavajpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wbdvprojectjavajpa.models.Comment;
import com.example.wbdvprojectjavajpa.models.Image;
import com.example.wbdvprojectjavajpa.repository.CommentRepository;
import com.example.wbdvprojectjavajpa.repository.ImageRepository;

@RestController
@CrossOrigin(origins="*",maxAge=3600, allowCredentials = "true")
public class CommentService {
	@Autowired
	CommentRepository commentRepo;
	
	@Autowired
	ImageRepository imageRepo;
	
	@GetMapping("api/image/{imageId}/comment")
	public Iterable<Comment> findCommentsforImage(@PathVariable("imageId") int imageId){
		Optional<Image> data = imageRepo.findById(imageId);
		if(data.isPresent()) {
			Image image = data.get();
			return image.getComments();
		}
		return null;
	}
	
	@PostMapping("api/image/{imageId}/comment")
	public Comment addCommentForImage(@PathVariable("imageId") int imageId, @RequestBody Comment comment){
		Optional<Image> data = imageRepo.findById(imageId);
		if(data.isPresent()) {
			Image image = data.get();
			comment.setImage(image);
			return commentRepo.save(comment);
		}
		return null;
	}
}
