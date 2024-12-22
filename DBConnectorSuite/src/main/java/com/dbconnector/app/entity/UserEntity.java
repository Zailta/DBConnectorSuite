package com.dbconnector.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="bauser_entity" )
public class UserEntity {
	@Id
	private String user_id;
	private String about;
	private String email;
	private String firstname;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	@Override
	public String toString() {
		return "UserEntity [user_id=" + user_id + ", about=" + about + ", email=" + email + ", firstname=" + firstname
				+ "]";
	}
	
	
	
	
	

}
