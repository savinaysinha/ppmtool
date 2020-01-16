package com.savinetwork.ppmtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savinetwork.ppmtool.repository.UserRespository;

@Service
public class UserService {

	@Autowired
	private UserRespository userRespository;
}
