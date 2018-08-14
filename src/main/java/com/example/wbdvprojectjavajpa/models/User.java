package com.example.wbdvprojectjavajpa.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String role;
	
	@OneToMany(mappedBy="user",
			orphanRemoval = true)
	@JsonIgnore
    private List<UserFollowers> followers;
	
	@OneToMany(mappedBy="user",
			fetch = FetchType.LAZY,
			cascade= CascadeType.PERSIST,
			orphanRemoval = true)
	private List<UserProjectAssociation> projects;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<UserProjectAssociation> getProjects() {
		return projects;
	}
	public void setProjects(List<UserProjectAssociation> projects) {
		this.projects = projects;
	}
	public List<UserFollowers> getFollowers() {
		return followers;
	}
	public void setFollowers(List<UserFollowers> followers) {
		this.followers = followers;
	}
	


}
