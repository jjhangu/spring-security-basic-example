package com.mkyong.web.service;

import java.io.IOException;

import javax.naming.NameNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		System.out.println("AuthenticationTokenProcessingFilter : + "  );
		filterChain.doFilter(request, response);
	}

}
