package com.employeeskillmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employeeskillmanagement.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	 @Query("SELECT DISTINCT user FROM User user " +
	            "INNER JOIN FETCH user.authorities AS authorities " +
	            "WHERE user.username = :username")
	    User findByUsername(@Param("username") String username);

//	 @Query("delete from User where id=:id")
//		public void delete(@Param("id")Long id);
	 
}
