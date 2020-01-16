package com.savinetwork.ppmtool.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.savinetwork.ppmtool.exception.InvalidLoginResponse;

/**
 * So basically the AuthenticationEntryPoint is an interface that provides the implementation for method call commence.
 * This is called whenever an exception is thrown because a user is trying to access a resource that requires authentication
 * if you're going to change or update or any thing in API you want the user to be logged in. 
 * So this is the main reason why we want to have our entry point.
 * 
 * @author Savinay Sinha
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		InvalidLoginResponse invalidLoginResponse = new InvalidLoginResponse();
		String jsonLoginResponse = new Gson().toJson(invalidLoginResponse);
		
		response.setContentType("application/json");
		response.setStatus(401);
		response.getWriter().print(jsonLoginResponse);
		
		
		
	}
	

}
