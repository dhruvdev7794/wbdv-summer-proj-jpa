package com.example.wbdvprojectjavajpa.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserProjectId implements Serializable{
	@Column(name = "user_id")
	private Long user_id;
	
	@Column(name = "project_id")
	private Long project_id;
	
	private UserProjectId() {}
	
	public UserProjectId(Long userId, Long projectId) {
		this.project_id = projectId;
		this.user_id = userId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if(obj == null || getClass() != obj.getClass())
			return false;
		
		UserProjectId that = (UserProjectId) obj;
		return Objects.equals(project_id, that.project_id) &&
				Objects.equals(user_id, that.user_id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(user_id, project_id);
	}
}
