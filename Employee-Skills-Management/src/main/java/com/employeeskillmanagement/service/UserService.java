package com.employeeskillmanagement.service;

import java.util.List;

import com.employeeskillmanagement.dto.UserDTO;
import com.employeeskillmanagement.entities.User;


public interface UserService {

	public List<User> getAll();
	
	   public User saveUser(UserDTO userdto)throws Exception;
		
		public User update(User user);
		
		 public void deleteById(int id);
	
}