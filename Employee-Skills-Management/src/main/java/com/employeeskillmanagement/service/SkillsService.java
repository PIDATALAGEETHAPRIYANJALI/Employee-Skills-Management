package com.employeeskillmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employeeskillmanagement.dto.SkillsDTO;
import com.employeeskillmanagement.entities.Skills;


public interface SkillsService {
	
	public Skills createSkill(SkillsDTO skillsDto);
	
	public Skills getSkillById(Integer id);
	
	public List<Skills> getAllSkills();
	
	public Skills updateSkill(SkillsDTO skillsDto);
	
	public void deleteSkillById(Integer id);


}
