package com.employeeskillmanagement.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.employeeskillmanagement.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkillsDTO {

	private Integer skill_id;
	@NotEmpty(message = "Should provide skill_name")
	private String skill_name;
	@NotEmpty(message = "Should provide description")
	private String description;

	private List<UserDTO> userDto;

	private String username;

	private String organizationName;

	@Override
	public String toString() {

		return "SkillsDto[ skill_id = " + skill_id + " skill_name =  " + skill_name + " description = " + description
				+ " userDto = " + userDto + "]";

	}

}
