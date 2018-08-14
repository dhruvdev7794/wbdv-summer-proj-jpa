package com.example.wbdvprojectjavajpa.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="user_project")
public class UserProjectAssociation implements Serializable{
	
//	@EmbeddedId
//	private UserProjectId id;
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private int id;
	
	private String role;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=false, updatable = false)
	@JsonIgnore
	private User user;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="project_id", insertable=false, updatable = false)
	private Project project;
	
	public UserProjectAssociation() {}
	
	public UserProjectAssociation(User user, Project project, String role){
		this.user = user;
		this.project = project;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

//	public UserProjectId getId() {
//		return id;
//	}
//
//	public void setId(UserProjectId id) {
//		this.id = id;
//	}
	
	
}
