package com.employeeskillmanagement.contollers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employeeskillmanagement.dto.UserDTO;
import com.employeeskillmanagement.entities.User;
import com.employeeskillmanagement.service.UserDetailsServiceImpl;




@RestController
public class UserController {

	@Autowired
	 private UserDetailsServiceImpl detailsServiceImpl; 
		
		public UserController(UserDetailsServiceImpl detailsServiceImpl) {
			this.detailsServiceImpl = detailsServiceImpl;
		} 
		
	@GetMapping("/users")
    public List<User> getAll() {
        return detailsServiceImpl.getAll();
    }
	
	@PostMapping("/saveUser")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<User> create(@Valid @RequestBody UserDTO user) throws Exception {
		
		User newUser=detailsServiceImpl.saveUser(user);
		return new ResponseEntity<>( newUser,HttpStatus.CREATED);
        
    }
	
	
	@DeleteMapping("/deleteUser/{id}")
	public HttpStatus deleteById(@PathVariable int id){
		this.detailsServiceImpl.deleteById(id);
		return HttpStatus.OK;
	}
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<User> update(@RequestBody User users,@PathVariable Integer id) {
		users.setId(id);
		//return detailsServiceImpl.update(users);
		return ResponseEntity.ok().body(detailsServiceImpl.update(users));
		
		}
	
	
}