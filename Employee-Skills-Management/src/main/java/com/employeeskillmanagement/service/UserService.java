package com.employeeskillmanagement.service;

import java.util.List;

import com.employeeskillmanagement.entities.User;

public interface UserService {

public List<User> getAll();

public void create(User user);

public User update(User user);

public void delete(Long id);

public void delete(User user);
}
