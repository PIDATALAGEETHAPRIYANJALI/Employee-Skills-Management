package com.employeeskillmanagement.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employeeskillmanagement.entities.Authority;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>
{
	
	@Query(value = "SELECT * FROM Authority a where a.name IN (:roles)", nativeQuery = true)
    List<Authority> find(@Param("roles") String roles);
	

}
