package com.mkyong.web.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;


public class LoginFailureHandler  extends SimpleUrlAuthenticationFailureHandler {

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
				
		if(exception instanceof SessionAuthenticationException){ // 동시 접속 세션이 초과했을때
		
			if(request.getContentType().contains("json")){ // json으로 리턴
				response.setCharacterEncoding("utf8");
		        response.setContentType("application/json"); 
		        PrintWriter out = response.getWriter();
		        JSONObject obj = new JSONObject();
		        obj.put("message", "Maximum sessions of 1 for this principal exceeded");
		        out.print(obj);			
			}else { // redirect and message
				response.sendRedirect("www.naver.com" + "?message=" + "message");
			}
		}else if(exception instanceof UsernameNotFoundException){
			response.sendRedirect("login");
		}
		
//		if(exception instanceof UsernameNotFoundException) {
//			response.setCharacterEncoding("utf8");
//	        response.setContentType("application/json"); 
//	        PrintWriter out = response.getWriter();
//	        JSONObject obj = new JSONObject();
//	        obj.put("message", "hello from server");
//	        out.print(obj);			
//		}
		
//		super.onAuthenticationFailure(request, response, exception);
	}
}
