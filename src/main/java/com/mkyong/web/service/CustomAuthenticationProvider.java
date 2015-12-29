package com.mkyong.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

//        User user = new User(username, password, null);

//        if(true)throw new UsernameNotFoundException(username);
        
        System.out.println("CustomAuthenticationProvider : " + username + " : " + password);
        if(username.equals("error")){
        	throw new UsernameNotFoundException(username);
        }
        
        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
    	return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
