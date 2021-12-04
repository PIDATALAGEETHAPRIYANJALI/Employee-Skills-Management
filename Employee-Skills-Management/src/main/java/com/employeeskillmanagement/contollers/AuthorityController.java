package com.employeeskillmanagement.contollers;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeskillmanagement.entities.Authority;
import com.employeeskillmanagement.service.AuthorityService;


@RestController
@RequestMapping("roles")
public class AuthorityController 
{
	@Autowired
	private AuthorityService authorityService; 
	
	@PostMapping
	public ResponseEntity<Authority> saveRole(@RequestBody Authority authority) {
	
	
	   Authority newRole =authorityService.saveAuthority(authority);
	   
	   return new ResponseEntity<>(newRole, HttpStatus.CREATED);
	}

}