package com.employeeskillmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employeeskillmanagement.entities.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository <Organization, Long> {
	
//	@Query(value = "select * from Organization o where o.org_name IN (:org_name)", nativeQuery = true)
//	Organization findByOrganizationName(@Param("org_name") List<String> list);
	
	@Query(value = "select distinct o.* from Organization o where o.org_name = :org_name", nativeQuery = true)
	public Organization findByOrganizationName(@Param("org_name") String list);

	/*@Query("SELECT DISTINCT user FROM User user " +
            "INNER JOIN FETCH user.authorities AS authorities " +
            "WHERE user.username = :username")
   public User findByUsername(@Param("username") String username);*/

}
