package com.employeeskillmanagement.entities;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "skills")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skills {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_sequence")
	@SequenceGenerator(name = "skill_sequence", sequenceName = "skill_sequence", allocationSize = 1)
	
	@Column(name = "id")
	private Integer skill_id;
	
	@Column(name = "skill_name")
	private String skill_name;
	
	@Column(name = "description")
	private String description;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_skills", 
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id"))
	@OrderBy
	@JsonIgnore
	private Collection<User> users;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn
	@JsonIgnore
	private Organization organization;
	
}
