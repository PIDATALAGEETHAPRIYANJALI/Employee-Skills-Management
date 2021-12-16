package com.employeeskillmanagement.dto;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

import com.employeeskillmanagement.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SkillsDTO {
	
	private Integer skill_id;
	private String skill_name;
	private String description;
	
	private UserDTO userDto;
	
//	private String organizationName;
	
	public SkillsDTO() {
		
	}
	
	@Override
	public String toString() {
		
		return "SkillsDto[ skill_id = " + skill_id + " skill_name =  " + skill_name + 
				" description = " + description + " userDto = " + userDto +"]";
		
	}
	

}
