package com.savinetwork.ppmtool.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.savinetwork.ppmtool.model.User;
import com.savinetwork.ppmtool.service.CustomUserDetailService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired 
	private CustomUserDetailService customUserDetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
				String jwt = getJwtFromHTTPRequest(request);
				if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
					Long userId = jwtTokenProvider.getUserIdFromJwt(jwt);
					User user = customUserDetailService.loadUserById(userId); 
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							user, null, Collections.emptyList());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
		} catch (Exception e) {
			logger.error("Could not set user authentication in security context"+e);
		}
		filterChain.doFilter(request, response);
	}
	
	private String getJwtFromHTTPRequest(HttpServletRequest request) {
		String bearerToken  = request.getHeader(SecurityConstant.HEADER_STRING);
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(SecurityConstant.TOKEN_PREFIX))
			return bearerToken.substring(7, bearerToken.length());
		return null;
	}

}