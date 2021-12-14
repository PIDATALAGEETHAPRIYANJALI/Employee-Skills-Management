package com.employeeskillmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.employeeskillmanagement.dto.UserDTO;
import com.employeeskillmanagement.entities.Authority;
import com.employeeskillmanagement.entities.Mail;
import com.employeeskillmanagement.entities.PasswordGenerator;
import com.employeeskillmanagement.entities.User;
import com.employeeskillmanagement.exception.CustomException;
import com.employeeskillmanagement.repository.AuthorityRepository;
import com.employeeskillmanagement.repository.UserRepository;

@Service
@ControllerAdvice
public class UserDetailsServiceImpl implements UserDetailsService,UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	 private  PasswordGenerator passwordGenerator;
	
	@Autowired
	private EmailService  emailservice;
	
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
	
	
	@Override
	@Transactional
	public User create(UserDTO userDto) throws Exception {
		
		User user=new User();
		
		user.setFirstName(userDto.getFirstName());
	    user.setLastName(userDto.getLastName());
	    user.setEmail(userDto.getEmail());
	    user.setUsername(userDto.getUsername());
	    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	    String pass= passwordGenerator.generateRandomPassword(8);
	    String encodedPassword = passwordEncoder.encode(pass);	
	    System.out.println(pass);
	    user.setPassword(encodedPassword);
	     
	    User user2 = null;
	    
	    List<Authority> listAll = authorityRepository.findAll();
		String superAdmin = listAll.get(0).getName();
		List<String> superList = new ArrayList<>();
		superList.add(superAdmin);
		     
		List<Authority> addAuthorities = authorityRepository.find(userDto.getRole());
		    
		if(superList.equals(userDto.getRole()))
		{
		   	throw new CustomException("this role was not added ");
		}
		else
		{
		   user.setAuthorities(addAuthorities);
		   user2 = userRepository.save(user);
		}
	          
          
		Mail mail = new Mail();
        mail.setSubject("Welcome to Employee Skill Management Project");
        mail.setToEmail(user.getEmail());
        mail.setContent("Your credentials are :"+"\n"+"username : "+user.getUsername() +"\n"+ "password :"+pass);
        emailservice.sendEmail(mail);
        return user2;
 
	}
	
	
	@Override
	@Transactional
	public User saveUser(UserDTO userdto) throws Exception {
		
		User user1;
		
		User user=new User();
		user.setFirstName(userdto.getFirstName());
	    user.setLastName(userdto.getLastName());
	    user.setEmail(userdto.getEmail());
	    user.setUsername(userdto.getUsername());
	    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	    String pass= passwordGenerator.generateRandomPassword(8);
	    String encodedPassword = passwordEncoder.encode(pass);
	    System.out.println(pass);
	      
	    user.setPassword(encodedPassword);
	     
	    List<Authority> listAll = authorityRepository.findAll();
	    String superAdmin = listAll.get(0).getName();
	      
	    List<String> superAdminList = new ArrayList<>();
	    superAdminList.add(superAdmin);
	      
	      
	    List<Authority> addAuthorities=authorityRepository.find(userdto.getRole());
	      
	    if(superAdminList.equals(userdto.getRole())) {
	    	
	    	throw new CustomException("This role cannot be added again (Because we should have only one super admin)");
	    }
	    else {
	    	user.setAuthorities(addAuthorities); 
	    	user1 = userRepository.save(user);
	    	  
	    }
	     
        Mail mail = new Mail();
        mail.setSubject("Welcome to Employee Skill Management Project");
        mail.setToEmail(user.getEmail());
        mail.setContent("Your credentials are :"+"\n"+"username : "+user.getUsername() +"\n"+ "password :"+pass);
        emailservice.sendEmail(mail);
          
		return user1;
 
	}
	
	@Transactional
	public User update(User userdto) {
		Optional<User> userdb=this.userRepository.findById((int) userdto.getId());
		
		if(userdb.isPresent()) {
			User userUpdate=userdb.get();
			userUpdate.setId(userdto.getId());
			userUpdate.setUsername(userdto.getUsername());
			userUpdate.setFirstName(userdto.getFirstName());
			userUpdate.setLastName(userdto.getLastName());
			userUpdate.setEmail(userdto.getEmail());
		    userUpdate.setPassword(userdto.getPassword());
		    userRepository.save(userUpdate);
		    return userUpdate;
		}
		else {
			throw new CustomException("Record not found with id" + userdto.getId());
		}
	}
	
	@Override
	@Transactional
	public void deleteById(int id) {
		
		Optional<User> userdb = this.userRepository.findById(id);
		
		if(userdb.isPresent()) {
			
			this.userRepository.deleteById(id);
		}
		else {
			throw new CustomException("Record not found with id  :" +id);
		}
	 
	}

	
	
}