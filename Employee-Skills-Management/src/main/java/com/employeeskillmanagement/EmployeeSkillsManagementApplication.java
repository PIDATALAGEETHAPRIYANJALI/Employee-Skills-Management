package com.employeeskillmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@SpringBootApplication
public class EmployeeSkillsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeSkillsManagementApplication.class, args);
		System.out.println("start");
	}

}


