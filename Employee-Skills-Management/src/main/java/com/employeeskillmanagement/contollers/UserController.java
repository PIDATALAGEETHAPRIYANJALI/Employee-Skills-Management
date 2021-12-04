package com.employeeskillmanagement.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employeeskillmanagement.entities.User;
import com.employeeskillmanagement.service.UserDetailsServiceImpl;


@RestController
public class UserController {
	
	@Autowired
    private UserDetailsServiceImpl detailsServiceImpl; 
	
	public UserController(UserDetailsServiceImpl detailsServiceImpl) {
		this.detailsServiceImpl = detailsServiceImpl;
	} 
	
	@GetMapping(value="/user")
    public List<User> getAll() {
        return detailsServiceImpl.getAll();
    }
	
	@RequestMapping(value="/saveUser",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody User user) {
        detailsServiceImpl.create(user);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
	
	
	@PutMapping(value="/updateUser/{id}")
	public ResponseEntity<User> update(@RequestBody User users,@PathVariable Long id) {
		users.setId(id);
		return ResponseEntity.ok().body(detailsServiceImpl.update(users));
		
		}
	
	
	 @DeleteMapping(value="/deleteUser/{id}")
	 @ResponseStatus(value = HttpStatus.OK)
	 public HttpStatus delete(@PathVariable Long id) {
			
			detailsServiceImpl.delete(id);
			return HttpStatus.OK;
	 }
	 	        
}
    


