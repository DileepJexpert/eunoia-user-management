package com.eunoia.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.eunoia.service.CustomUserDetailService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	 @Autowired
	   private CustomUserDetailService userDetailsService;
	 
	 
    @Override
    public Authentication authenticate(Authentication auth) 
      throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials()
            .toString();
        User user = userDetailsService.loadUserByUsername(username);
        
   
        if (user != null ){
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        
        return new UsernamePasswordAuthenticationToken(user, password, authorities);  
        }
          
 System.out.println(" in side CustomAuthenticationProvider -------------------------------------"+username);
        if ("externaluser".equals(username) && "pass".equals(password)) {
            return new UsernamePasswordAuthenticationToken
              (username, password, Collections.emptyList());
        } else {
            throw new
              BadCredentialsException("External system authentication failed");
            
        }
      
      
        
    }
 
@Override
public boolean supports(Class<? extends Object> authentication) {
    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
}
}