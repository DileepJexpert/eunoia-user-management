package com.eunoia.security;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    private static final long serialVersionUID = 1L;
    
    private final String verificationCode;
    private final String referer;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        verificationCode = request.getParameter("code");
        this.referer = request.getHeader("Referer");
        System.out.println("verificationCode-----------------verificationCode---------------"+verificationCode);
    }

    public String getVerificationCode() {
        return verificationCode;
    }
    public String getReferer() {
        return referer;
      }
}