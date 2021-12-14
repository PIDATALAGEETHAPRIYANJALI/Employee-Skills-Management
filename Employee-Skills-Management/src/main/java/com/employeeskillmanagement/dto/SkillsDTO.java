package com.employeeskillmanagement.dto;

public class SkillsDTO {
	
	private long skill_id;
	private String skill_name;
	private String description;
	private long skill_created_by;
	private long org_id;
	
	public long getSkill_id() {
		return skill_id;
	}
	public void setSkill_id(long skill_id) {
		this.skill_id = skill_id;
	}
	public String getSkill_name() {
		return skill_name;
	}
	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getSkill_created_by() {
		return skill_created_by;
	}
	public void setSkill_created_by(long skill_created_by) {
		this.skill_created_by = skill_created_by;
	}
	public long getOrg_id() {
		return org_id;
	}
	public void setOrg_id(long org_id) {
		this.org_id = org_id;
	}
	
	
	

}
