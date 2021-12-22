package com.employeeskillmanagement.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class OrganizationDTO {
	
	private long id;
	@NotEmpty(message = "Should provide organizationName")
	private String organizationName;
	@NotEmpty(message = "Should have to provide city")
	private String city;
	@NotEmpty(message = "Should have to provide state")
	private String state;
	@NotEmpty(message = "Should have to provide country")
	private String country;
	@NotEmpty(message = "Zip value is must not be empty")
	private Long zip;
	@NotEmpty(message = "Should provide phone number")
	@Size(min = 10, message = "Phone number should contain 10 numbers")
	private Long phone;
    @NotEmpty(message = "give email")
    @Email(message = "Please enter valid email address")
	private String email;
	
	private UserDTO userDto;
	
	public UserDTO getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public void setZip(Long zip) {
		this.zip = zip;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Long getZip() {
		return zip;
	}
	public void setZip(long zip) {
		this.zip = zip;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public OrganizationDTO() {
		
	}
	
	@Override
	public String toString() {
		return "OrganizationDTO [id = " + id + "organizationName = " + organizationName + 
				"city = " + city + "state = " + state + "country = " + country + 
				"zip = "+ zip + "phone = " + phone + "email = " + email + "userDto = " + userDto + "]";
	}
}










