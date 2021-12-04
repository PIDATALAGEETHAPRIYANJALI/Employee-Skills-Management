package com.employeeskillmanagement.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.employeeskillmanagement.UserDTO;
import com.employeeskillmanagement.entities.Generation_Of_Password;
import com.employeeskillmanagement.entities.User;
import com.employeeskillmanagement.repository.AuthorityRepository;
import com.employeeskillmanagement.repository.UserRepository;

@Service
@ControllerAdvice
public class UserDetailsServiceImpl implements UserDetailsService ,UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Generation_Of_Password generation_of_password;
	
	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user != null) {
			return user;
		}

		throw new UsernameNotFoundException(username);
	}


	@Transactional(readOnly = true)
	 public List<User> getAll() {
		 return userRepository.findAll();
	 }
	
	@Transactional
	public User saveUser(UserDTO userdto) throws Exception {
		
		User user = new User();
		
		user.setFirstName(userdto.getFirstname());
		user.setLastName(userdto.getLastname());
		user.setUsername(userdto.getUsername());
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = generation_of_password.generatingRandomPassword(10);
		String encodedPassword = passwordEncoder.encode(password);
		
		System.out.println(password);
		
		user.setPassword(encodedPassword);
		
		
		return userRepository.save(user);
	}
	
	@Transactional
	public void create(User user) {

		userRepository.save(user);
	}
	
	@Transactional
	public User update(User userdto) {
		return userRepository.save(userdto);
	}
	
	@Transactional
	public void delete(Long id) {
	 userRepository.deleteById(id);
	}
	
	@Transactional
	public void delete(User user) {
		userRepository.delete(user);
	} 

}

