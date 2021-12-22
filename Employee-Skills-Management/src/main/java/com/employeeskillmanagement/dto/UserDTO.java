package com.employeeskillmanagement.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserDTO {

	private long id;
	@NotEmpty(message = "firstName should not be empty or null")
    private String firstName;
	@NotEmpty(message = "lastName should not be empty or null")
    private String lastName;
	@NotEmpty(message = "Username should be unique or should not be null")
    private String username;
    private String password;
    @Pattern(regexp ="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$",message="mail id is not valid")
    @NotEmpty(message = "email should not be empty")
    @Email(message = "email should be in correct format and should not be null")
    private String email;
    @NotEmpty(message = "role should not be empty")
    private List<String> role;
    
    
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getRole() {
		return role;
	}
	public void setRole(List<String> role) {
		this.role = role;
	}
	
    
    
    
    
    
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", emailId=" + email + ", role=" + role + "]";
	}

}