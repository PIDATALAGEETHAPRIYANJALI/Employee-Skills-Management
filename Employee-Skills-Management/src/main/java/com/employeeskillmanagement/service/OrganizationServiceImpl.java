package com.employeeskillmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employeeskillmanagement.dto.OrganizationDTO;
import com.employeeskillmanagement.dto.UserDTO;
import com.employeeskillmanagement.entities.Authority;
import com.employeeskillmanagement.entities.Mail;
import com.employeeskillmanagement.entities.Organization;
import com.employeeskillmanagement.entities.PasswordGenerator;
import com.employeeskillmanagement.entities.User;
import com.employeeskillmanagement.exception.CustomException;
import com.employeeskillmanagement.repository.AuthorityRepository;
import com.employeeskillmanagement.repository.OrganizationRepository;
import com.employeeskillmanagement.repository.UserRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordGenerator passwordGenerator;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private EmailService emailService;
		

	@Override
	@Transactional
	public Organization createOrganization(OrganizationDTO organizationDto) throws Exception{
		
		Organization organization = new Organization();
		organization.setOrganizationName(organizationDto.getOrganizationName());
		organization.setCity(organizationDto.getCity());
		organization.setState(organizationDto.getState());
		organization.setCountry(organizationDto.getCountry());
		organization.setZip(organizationDto.getZip());
		organization.setPhone(organizationDto.getPhone());
		organization.setEmail(organizationDto.getEmail());
		
		UserDTO userDto = organizationDto.getUserDto();
		User user=new User();
				
		user.setUsername(userDto.getUsername());
	    user.setFirstName(userDto.getFirstName());
	    user.setLastName(userDto.getLastName());
	    user.setEmail(userDto.getEmail());
	    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
		String pass= passwordGenerator.generateRandomPassword(8);
		String encodedPassword = passwordEncoder.encode(pass);
		      
	
		user.setPassword(encodedPassword);
				
		User user2 = null;
	    List<Authority> listAll = authorityRepository.findAll();
		String superAdmin=listAll.get(0).getName();
		List<String> superList=new ArrayList<>();
		superList.add(superAdmin);
		     
		List<Authority> addAuthorities=authorityRepository.find(userDto.getRole());
		    
		if(superList.equals(userDto.getRole()))
		{
		   	throw new CustomException("this role was not added ");
		}
		else
		{
		   user.setAuthorities(addAuthorities);
		   user2= userRepository.save(user);
		}
		  
		Mail mail = new Mail();
        mail.setSubject("Welcome to Employee Skill Management Project");
        mail.setToEmail(user.getEmail());
        mail.setContent("Your credentials are :"+"\n"+"username : "+user.getUsername() +"\n"+ "password :"+pass);
        emailService.sendEmail(mail);
		
		organization.setUser(user2);
		  
		return organizationRepository.save(organization);

	}
	
	@Override
	@Transactional
	public List<Organization> getAllOrganizations() {
		
		return this.organizationRepository.findAll();
	}
	
	
	@Override
	@Transactional
	public Organization getOrganizationById(long id) {
		Optional<Organization> jobpostdb=this.organizationRepository.findById(id);
		if(jobpostdb.isPresent()) {
			return jobpostdb.get();
		}
		
		else {
			throw  new CustomException("Record not found with this id  :" +id);
		}
	}

	@Override
	@Transactional
	public Organization updateOrganization(OrganizationDTO organizationDto) {
		
		Optional<Organization> organization = this.organizationRepository.findById(organizationDto.getId());
		
		
		if(organization.isPresent()) {
			
			Organization organizationUpdate = organization.get();
			organizationUpdate.setOrganizationName(organizationDto.getOrganizationName());
			organizationUpdate.setCity(organizationDto.getCity());
			organizationUpdate.setState(organizationDto.getState());
			organizationUpdate.setCountry(organizationDto.getCountry());
			organizationUpdate.setZip(organizationDto.getZip());
			organizationUpdate.setPhone(organizationDto.getPhone());
			organizationUpdate.setEmail(organizationDto.getEmail());
			
			UserDTO user = organizationDto.getUserDto();
			
			User userUpdate = new User();
					
			userUpdate.setId(organization.get().getUser().getId());
			userUpdate.setUsername(organization.get().getUser().getUsername());
		    userUpdate.setFirstName(user.getFirstName());
		    userUpdate.setLastName(user.getLastName());
		    userUpdate.setEmail(organization.get().getUser().getEmail());
		    userUpdate.setPassword(organization.get().getUser().getPassword());
		    
		    
		    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
			String pass= passwordGenerator.generateRandomPassword(8);
			String encodedPassword = passwordEncoder.encode(pass);
			System.out.println(pass);
			user.setPassword(encodedPassword);
		    
		    
		    Mail mail = new Mail();
	        mail.setSubject("Welcome to Employee Skill Management Project");
	        mail.setToEmail(organization.get().getUser().getEmail());
	        mail.setContent("Your credentials are :"+"\n"+"username : "+user.getUsername() +"\n"+ "password :"+ pass);
	        try {
				emailService.sendEmail(mail);
			} catch (Exception e) {
				e.printStackTrace();
			}
		    
		    
		    userUpdate.setAuthorities(organization.get().getUser().getAuthorities()); // It will take role from db that means we cannot change the role of user.
		    
		    userRepository.save(userUpdate);
		    
		    organizationUpdate.setUser(userUpdate);
			
			this.organizationRepository.save(organizationUpdate);
			return organizationUpdate;
					
		}
		else {
			throw new CustomException("Record not found with id" + organizationDto.getId());
		}

	}
	
	
	@Override
	@Transactional
	public void deleteOrganizationById(long id) {
		System.out.println("Delete");
		Optional<Organization> organization= this.organizationRepository.findById(id);
        if(organization.isPresent()) {
			
        	this.organizationRepository.deleteById(id);
		}
		else {
			throw new CustomException("Record not found with id  :" +id);
		}
		
	}
	
	
	

}
