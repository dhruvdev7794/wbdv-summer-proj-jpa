package com.example.wbdvprojectjavajpa.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserProjectId implements Serializable{
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "project_id")
	private Long projectId;
	
	private UserProjectId() {}
	
	public UserProjectId(Long userId, Long projectId) {
		this.projectId = projectId;
		this.userId = userId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if(obj == null || getClass() != obj.getClass())
			return false;
		
		UserProjectId that = (UserProjectId) obj;
		return Objects.equals(projectId, that.projectId) &&
				Objects.equals(userId, that.userId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(userId, projectId);
	}
}
