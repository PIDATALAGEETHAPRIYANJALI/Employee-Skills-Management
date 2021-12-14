package com.employeeskillmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employeeskillmanagement.entities.Organization;

public interface OrganizationRepository extends JpaRepository <Organization, Long> {
	
	@Query(value = "select * from Organization o where o.organizationName IN (:org_name)", nativeQuery = true)
	List<Organization> findByOrganizationName(@Param("org_name") List<String> list);
	

}
