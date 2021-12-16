package com.employeeskillmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employeeskillmanagement.dto.SkillsDTO;
import com.employeeskillmanagement.dto.UserDTO;
import com.employeeskillmanagement.entities.Authority;
import com.employeeskillmanagement.entities.Mail;
import com.employeeskillmanagement.entities.Organization;
import com.employeeskillmanagement.entities.PasswordGenerator;
import com.employeeskillmanagement.entities.Skills;
import com.employeeskillmanagement.entities.User;
import com.employeeskillmanagement.exception.CustomException;
import com.employeeskillmanagement.repository.AuthorityRepository;
import com.employeeskillmanagement.repository.OrganizationRepository;
import com.employeeskillmanagement.repository.SkillsRepository;
import com.employeeskillmanagement.repository.UserRepository;

@Service
public class SkillsServiceImpl implements SkillsService {
	
	@Autowired
	private SkillsRepository skillsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordGenerator passwordGenerator;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Override
	@Transactional
	public Skills createSkill(SkillsDTO skillsDto) {
		
		Skills skills = new Skills();
		
		skills.setSkill_name(skillsDto.getSkill_name());
		skills.setDescription(skillsDto.getDescription());
		
		UserDTO userDto = skillsDto.getUserDto();
		
		
		User user = new User();
		
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
		String pass= passwordGenerator.generateRandomPassword(8);
		String encodedPassword = passwordEncoder.encode(pass);
		user.setPassword(encodedPassword);
		
		User user2 = null;
		
		List<Authority> listAll = authorityRepository.findAll();
		String superAdmin = listAll.get(0).getName();
		List<String> superList = new ArrayList<>();
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
		   
		   
		   Mail mail = new Mail();
	       mail.setSubject("Welcome to Employee Skill Management Project");
	       mail.setToEmail(user.getEmail());
	       mail.setContent("Your credentials are :"+"\n"+"username : "+user.getUsername() +"\n"+ "password :"+pass);
	       try {
	    	   emailService.sendEmail(mail);
	    	   } catch (Exception e) {
				e.printStackTrace();
				}
		}
		
		     
        skills.setUser(List.of(user2)); //when user entity declared as list in skills entity
        
//        skills.setUser(user2);
		
        
//        System.out.println("skillsDto.getOrganizationName() : "+ skillsDto.getOrganizationName());
//        
//        System.out.println("iiiiiiiiiiiiiiiiiiiii ");
//        Organization org = this.organizationRepository.findByOrganizationName(skillsDto.getOrganizationName());
//        Organization org = this.organizationRepository.findByOrganizationName(skillsDto.getOrganizationName());
        
//        System.out.println("skillsDto.getOrganizationName() : "+ skillsDto.getOrganizationName());
        
//        skills.setOrganization(org);
		return skillsRepository.save(skills);
		
	}

	@Override
	public Skills getSkillById(Integer id) {
		Optional<Skills> skill = this.skillsRepository.findById(id);
		
		if(skill.isPresent()) {
			return skill.get();
		}
		else {
			throw new CustomException("Record not found with this id" + id);
		}
	}

	@Override
	public List<Skills> getAllSkills() {
		return this.skillsRepository.findAll();
	}

	@Override
	public Skills updateSkill(SkillsDTO skillsDto) {
//		Optional<Skills> skill = this.skillsRepository.findById(skillsDto.getSkill_id());
//		
//		if(skill.isPresent()) {
//			
//			Skills skillUpdate = skill.get();
//			skillUpdate.setSkill_name(skillsDto.getSkill_name());
//			skillUpdate.setDescription(skillsDto.getDescription());
//			
//			UserDTO user = skillsDto.getUserDto();
//			
//			User userUpdate = new User();
//			
//			System.out.println("update"+skill.get().getUser());
//			
//			userUpdate.setId(skill.get().getUser().g);
//			userUpdate.setFirstName(user.getFirstName());
//			userUpdate.setLastName(user.getLastName());
//			userUpdate.setEmail(skill.get().getUser().getEmail());
//			userUpdate.setPassword(skill.get().getUser().getPassword());
//			
//			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
//			String pass= passwordGenerator.generateRandomPassword(8);
//			String encodedPassword = passwordEncoder.encode(pass);
//			System.out.println(pass);
//			user.setPassword(encodedPassword);
//		    
//		    
//		    Mail mail = new Mail();
//	        mail.setSubject("Welcome to Employee Skill Management Project");
//	        mail.setToEmail(skill.get().getUser().getEmail());
//	        mail.setContent("Your credentials are :"+"\n"+"username : "+user.getUsername() +"\n"+ "password :"+ pass);
//	        try {
//				emailService.sendEmail(mail);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//	        
//	        userUpdate.setAuthorities(skill.get().getUser().getAuthorities());
//	        
//	        userRepository.save(userUpdate);
//	        
//	        skillUpdate.setUser(userUpdate);
//	        
//	        this.skillsRepository.save(skillUpdate);
//	        
//	        return skillUpdate;
//	        
//		}else {
//			throw new CustomException("Record not found with this id :" + skillsDto.getSkill_id());
//		}
//		
		return null;
		
	}


	@Override
	@Transactional
	public void deleteSkillById(Integer id) {
		Optional<Skills> skill = this.skillsRepository.findById(id);
		
		if(skill.isPresent()) {
			this.skillsRepository.deleteById(id);
		}else {
			throw new CustomException("Record not found with this id :"+ id);
		}
		
	}

}
