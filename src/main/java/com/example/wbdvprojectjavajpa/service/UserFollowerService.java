package com.example.wbdvprojectjavajpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wbdvprojectjavajpa.models.Image;
import com.example.wbdvprojectjavajpa.models.Project;
import com.example.wbdvprojectjavajpa.models.User;
import com.example.wbdvprojectjavajpa.models.UserFollowers;
import com.example.wbdvprojectjavajpa.repository.UserFollowersRepository;
import com.example.wbdvprojectjavajpa.repository.UserRepository;

@RestController
@CrossOrigin(origins = "*", maxAge=3600, allowCredentials = "true")
public class UserFollowerService {
	
	@Autowired
	UserFollowersRepository userFRepo;
	
	@Autowired
	UserRepository userRepo;

	
	@PostMapping("/api/follow/{userId}")
	public UserFollowers createFollower(@PathVariable("userId") int userId, @RequestBody UserFollowers userFollowers) {
		Optional<User> data = userRepo.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			userFollowers.setUser(user);
			return userFRepo.save(userFollowers);
		}
		return null;
	}
	
	@GetMapping("/api/follow/{userId}")
	public Iterable<UserFollowers> findAllFollowers(@PathVariable("userId") int userId) {
		Optional<User> data = userRepo.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			return user.getFollowers();
		}
		return null;
	}

}
