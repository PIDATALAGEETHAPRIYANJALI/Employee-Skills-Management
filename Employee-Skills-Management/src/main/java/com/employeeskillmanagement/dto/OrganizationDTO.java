package com.employeeskillmanagement.dto;

public class OrganizationDTO {
	
	private long id;
	private String organizationName;
	private String city;
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
	public Long getUser_id_created_by() {
		return user_id_created_by;
	}
	public void setUser_id_created_by(long user_id_created_by) {
		this.user_id_created_by = user_id_created_by;
	}
	private String state;
	private String country;
	private Long zip;
	private Long phone;
	private String email;
	private Long user_id_created_by;
	
	public OrganizationDTO(long id, String organizationName, String city, String state, String country, 
			long zip, long phone, String email, long user_id_created_by) {
		
		super();
		
		this.id = id;
		this.organizationName = organizationName;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		this.user_id_created_by = user_id_created_by;
	}
	
	public OrganizationDTO () {
		super();
	}
	
}










