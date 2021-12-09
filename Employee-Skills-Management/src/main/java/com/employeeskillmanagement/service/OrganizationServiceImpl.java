package com.employeeskillmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employeeskillmanagement.dto.OrganizationDTO;
import com.employeeskillmanagement.entities.Organization;
import com.employeeskillmanagement.entities.User;
import com.employeeskillmanagement.exception.CustomException;
import com.employeeskillmanagement.repository.OrganizationRepository;
import com.employeeskillmanagement.repository.UserRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public Organization createOrganization(OrganizationDTO organizationDto) {
		Organization organization = new Organization();
		organization.setOrganizationName(organizationDto.getOrganizationName());
		organization.setCity(organizationDto.getCity());
		organization.setState(organizationDto.getState());
		organization.setCountry(organizationDto.getCountry());
		organization.setZip(organizationDto.getZip());
		organization.setPhone(organizationDto.getPhone());
		organization.setEmail(organizationDto.getEmail());
		organization.setUser_id_created_by(organizationDto.getUser_id_created_by());
		
		User user = this.organizationRepository.findByOrganizationName(organizationDto.getOrganizationName());
		
		organization.setUser(user);
		
		return this.organizationRepository.save(organization);
		
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
			throw  new CustomException("Record not found with id  :" +id);
		}
	}

	@Override
	@Transactional
	public Organization updateOrganization(OrganizationDTO organizationDto) {
	Optional<Organization> organizationdb=this.organizationRepository.findById(organizationDto.getId());
		
		if(organizationdb.isPresent()) {
			Organization organizationUpdate = organizationdb.get();
			System.out.println(organizationUpdate + "organization");
			
			organizationUpdate.setOrganizationName(organizationDto.getOrganizationName());
			organizationUpdate.setOrganizationName(organizationDto.getOrganizationName());
			organizationUpdate.setCity(organizationDto.getCity());
			organizationUpdate.setState(organizationDto.getState());
			organizationUpdate.setCountry(organizationDto.getCountry());
			organizationUpdate.setZip(organizationDto.getZip());
			organizationUpdate.setPhone(organizationDto.getPhone());
			organizationUpdate.setEmail(organizationDto.getEmail());
			organizationUpdate.setUser_id_created_by(organizationDto.getUser_id_created_by());
			
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
