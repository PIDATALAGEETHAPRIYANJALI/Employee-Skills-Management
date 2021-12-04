package com.employeeskillmanagement;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserDTO {

	private long id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private List<String> role;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public List<String> getRole() {
		return role;
	}
	public void setRole(List<String> role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return username + firstname + lastname ;
	}
	
}
