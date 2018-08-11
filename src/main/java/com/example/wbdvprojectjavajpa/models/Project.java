package com.example.wbdvprojectjavajpa.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String projName;
	
	@OneToMany(mappedBy="project",
			cascade= CascadeType.ALL,
			orphanRemoval = true)
    private List<Image> images;

	@OneToMany(mappedBy="project")
	private List<UserProjectAssociation> users;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public List<UserProjectAssociation> getUsers() {
		return users;
	}
	public void setUsers(List<UserProjectAssociation> users) {
		this.users = users;
	}
	
	
	
	
}
