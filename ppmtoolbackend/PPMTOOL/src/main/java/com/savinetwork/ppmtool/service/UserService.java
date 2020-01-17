package com.savinetwork.ppmtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.savinetwork.ppmtool.exception.UsernameAlreadyExistsException;
import com.savinetwork.ppmtool.model.User;
import com.savinetwork.ppmtool.repository.UserRespository;

@Service
public class UserService {

	@Autowired
	private UserRespository userRespository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User saveUser(User newUser) {
		try {
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
			newUser.setUsername(newUser.getUsername());
			newUser.setConfirmPassword("");
			return userRespository.save(newUser);
		} catch (Exception e) {
			throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exist");
		}
		
		
	}
	
	
	

}
