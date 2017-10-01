package com.eunoia.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;
 

@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	   private final Logger logger = LoggerFactory.getLogger(getClass());

	    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	    @Autowired
	    ActiveUserStore activeUserStore;
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //do some logic here if you want something to be done whenever
        //the user successfully logs in.
 
        HttpSession session = httpServletRequest.getSession();
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("username", authUser.getUsername());
        session.setAttribute("authorities", authentication.getAuthorities());
 System.out.println("-----hj------hj-----hj----hj-----------------------hj----");
        //set our response to OK status
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        
        if (session != null) {
            session.setMaxInactiveInterval(30 * 60);
            LoggedUser user = new LoggedUser(authentication.getName(), activeUserStore);
            session.setAttribute("user", user);
        }
        /*Set target URL to redirect*/
		//String targetUrl = determineTargetUrl(authentication); 
     //   redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, targetUrl);
        //since we have created our custom success handler, its up to us to where
        //we will redirect the user after successfully login
        httpServletResponse.sendRedirect("home");
    }
    
    protected String determineTargetUrl(Authentication authentication) {
        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (authorities.contains("ROLE_ADMIN")) {
        	return "/admin.htm";
        } else if (authorities.contains("ROLE_USER")) {
        	return "/user.htm";
        } else {
            throw new IllegalStateException();
        }
    }
 
}