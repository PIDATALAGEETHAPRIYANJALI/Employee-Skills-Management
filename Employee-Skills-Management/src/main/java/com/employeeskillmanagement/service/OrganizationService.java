package com.employeeskillmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employeeskillmanagement.dto.OrganizationDTO;
import com.employeeskillmanagement.entities.Organization;


@Service
public interface OrganizationService {
	
	public Organization createOrganization(OrganizationDTO orgDto);
	public List<Organization> getAllOrganizations();
	public Organization getOrganizationById(long id);
	public Organization updateOrganization(OrganizationDTO orgDto);
	public void deleteOrganizationById(long id);

}
