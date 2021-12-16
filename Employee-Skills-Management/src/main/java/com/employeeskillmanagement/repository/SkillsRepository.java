package com.employeeskillmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employeeskillmanagement.entities.Skills;

public interface SkillsRepository extends JpaRepository <Skills, Integer> {
	
	@Query(value = "select * from skills s  where s.skill_name IN (:skill_name)", nativeQuery = true)
	List<Skills> findById(@Param("skill_name") List<String> list);
	
//	@Query(value = "select * from skills s  where s.skill_name IN (:skill_name)", nativeQuery = true)
//	List<Skills> findById(@Param("skill_name") String list);

}
