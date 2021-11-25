package com.employeeskillmanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeeskillmanagement.model.User;

@Repository
public interface EmployeeRepository extends JpaRepository<User, Long>{

}
