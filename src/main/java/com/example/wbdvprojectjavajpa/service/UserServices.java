package com.example.wbdvprojectjavajpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
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

import com.example.wbdvprojectjavajpa.models.User;
import com.example.wbdvprojectjavajpa.repository.UserRepository;

@RestController
@CrossOrigin(origins="*",maxAge=3600, allowCredentials = "true")
class UserServices {
	
	@Autowired
	UserRepository userRespository;
	
	
	//delete user
	@DeleteMapping("api/user/{userId}")
	public void deleteUser( @PathVariable("userId") int id) {
		userRespository.deleteById(id);
		
	}
	//create user
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return userRespository.save(user);
	}
	// find all users
	@GetMapping("/api/user")
	public List<User> findAllUsers(){
		return(List<User>) userRespository.findAll();
	}
	
	//update user
	@PutMapping ("api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = userRespository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setUsername(newUser.getUsername());
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setRole(newUser.getRole());
			userRespository.save(user);
			return user;
			
		}
		return null;
	}
	
	
	//find user by Id
	@GetMapping("api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = userRespository.findById(userId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	
/////// http session
//	// registering as a new user 
//	List<User> users = new ArrayList<User>();
//	
//	@PostMapping("/api/register")
//	public User register(@RequestBody User user,
//	HttpSession session) {
//		session.setAttribute("currentUser", user);
//		users.add(user);
//		return user;
//	}
	

//	 register
	@PostMapping("/api/register")
	public User register(@RequestBody User user,HttpSession session,HttpServletResponse response) {
		Iterable<User> data = userRespository.findUserByUsername(user.getUsername());
		for (User newUser : data) {
			if (newUser != null) {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				return null;
			} else {
				session.setAttribute("currentUser", user);
				return createUser(user);
			}
		}
		session.setAttribute("currentUser", user);
		return createUser(user);
	}
	
	//login
	@PostMapping("/api/login")
	public Optional<User> login(@RequestBody User user, HttpSession session) {
		Optional<User> data = userRespository.findByCredentials(user.getUsername(), user.getPassword());
      if (data.isPresent()) {
			session.setAttribute("currentUser", data.get());
			return data;
		}
		return null;

	}
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		System.out.println(session.getAttribute("currentUser"));
		User currentUser = (User) session.getAttribute("currentUser");
		System.out.println(currentUser);
		return currentUser;
	}

	



}
