package com.employeeskillmanagement.dto;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

import com.employeeskillmanagement.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillsDTO {
	
	private long skill_id;
	private String skill_name;
	private String description;
	
	private UserDTO userDto;
	
	public SkillsDTO() {
		
	}
	
	@Override
	public String toString() {
		
		return "SkillsDto[ skill_id = " + skill_id + " skill_name =  " + skill_name + 
				" description = " + description + " userDto = " + userDto +"]";
		
	}
	
	
	
	
	

}
