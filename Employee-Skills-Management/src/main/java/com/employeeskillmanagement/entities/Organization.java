package com.employeeskillmanagement.entities;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organization")
public class Organization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_sequence")
	@SequenceGenerator(name = "organization_sequence", sequenceName = "organization_sequence", allocationSize = 1 )
	
	@Column(name = "id")
	private long id;
	
	@Column(name = "org_name", nullable = false)	
	private String OrganizationName;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "zip")
	private long zip;
	
	@Column(name = "phone")
	private long phone;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn
	@JsonIgnore
	private User user;

	
	
}



