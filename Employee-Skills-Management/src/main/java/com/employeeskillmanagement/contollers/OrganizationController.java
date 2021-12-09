package com.employeeskillmanagement.contollers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employeeskillmanagement.dto.OrganizationDTO;
import com.employeeskillmanagement.entities.Organization;
import com.employeeskillmanagement.service.OrganizationService;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	@GetMapping()
	@ResponseStatus(value = HttpStatus.OK)
	public List<Organization> getAllOrganizations(){
		return this.organizationService.getAllOrganizations();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Organization> getOrganizationById(@PathVariable Long id) {
		return ResponseEntity.ok().body(this.organizationService.getOrganizationById(id));
	}
	
	@PostMapping(value = "/saveOrganization")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Organization> create(@Valid @RequestBody OrganizationDTO organizationDto) {
		
		Organization organization = this.organizationService.createOrganization(organizationDto);
		return new ResponseEntity<>(organization, HttpStatus.CREATED);
		
	}
	
	@PutMapping(value = "/updateOrganization/{id}")
	public ResponseEntity<Organization> update(@RequestBody OrganizationDTO organizationDto, @PathVariable Long id){
		organizationDto.setId(id);
		System.out.println("Updated the Organization Details");
		return ResponseEntity.ok().body(this.organizationService.updateOrganization(organizationDto));
	}
	
	@DeleteMapping(value = "deleteOrganization/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public HttpStatus delete(@PathVariable Long id) {
		System.out.println("Deleted");
		this.organizationService.deleteOrganizationById(id);
		return HttpStatus.OK;
	}
	

}
