package com.employeeskillmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employeeskillmanagement.entities.Authority;
import com.employeeskillmanagement.repository.AuthorityRepository;


@Service
public class AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Transactional
	public Authority saveAuthority(Authority authority) {
		
		Authority newAuthority=authorityRepository.save(authority);
		return newAuthority;
	}

}
