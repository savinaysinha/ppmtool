package com.savinetwork.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savinetwork.ppmtool.model.User;
import com.savinetwork.ppmtool.payload.JwtLoginSuccessResponse;
import com.savinetwork.ppmtool.payload.LoginRequest;
import com.savinetwork.ppmtool.security.JwtTokenProvider;
import com.savinetwork.ppmtool.security.SecurityConstant;
import com.savinetwork.ppmtool.service.MapErrorValidationService;
import com.savinetwork.ppmtool.service.UserService;
import com.savinetwork.ppmtool.validator.UserValidator;

@RestController
@RequestMapping("api/users")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MapErrorValidationService mapErrorValidationService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,BindingResult result){
		System.out.println("Inside");
		System.out.println(loginRequest.getUsername()+" "+loginRequest.getPassword());
		ResponseEntity<?> errors = mapErrorValidationService.mapError(result);
		if (errors != null)
			return errors;
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = SecurityConstant.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtLoginSuccessResponse(true, jwt));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result ){
		userValidator.validate(user, result);
		ResponseEntity<?> errors = mapErrorValidationService.mapError(result);
		if (errors != null)
			return errors;
		User newUser = userService.saveUser(user);
		return new ResponseEntity<User>(newUser,HttpStatus.CREATED);
	}
}
