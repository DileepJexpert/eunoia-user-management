package com.eunoia.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component("customlogoutSuccessHandler")
public class CustomlogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        final HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute("user");
        }
        if(authentication != null) {
			System.out.println("------------------------------------------------------------------------------------"+authentication.getName());
			System.out.println("------------------------------------------------------------------------------------"+authentication.getName());
			System.out.println("------------------------------------------------------------------------------------"+authentication.getName());
		}
		//perform other required operation
		String URL = request.getContextPath() + "/app";
		response.setStatus(HttpStatus.OK.value());

        response.sendRedirect("/login");
    }
}
