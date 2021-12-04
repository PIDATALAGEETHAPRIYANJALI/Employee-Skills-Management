package com.employeeskillmanagement.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organization")
public class Oraganization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String org_name;
	private String city;
	private String state;
	private String country;
	private Long zip;
	private Long phone;
	private String email;
	private Long user_id_created_by;
	
	@OneToOne(mappedBy = "organization")
	private User user;
	
}



