package com.rw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rw.entity.User;
import com.rw.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User save(User user) {
		return userRepository.saveAndFlush(user);
	}
	
	public User update(User user) {
		return userRepository.saveAndFlush(user);
	}
}
