package com.eunoia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.eunoia.security.CustomAuthenticationProvider;
import com.eunoia.security.CustomWebAuthenticationDetailsSource;

@Configuration
@ComponentScan(basePackages = { "com.eunoia.security" })
//@ImportResource({ "classpath:webSecurityConfig.xml" })
@EnableWebSecurity
public class WebSpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
    @Autowired
    private LogoutSuccessHandler customlogoutSuccessHandler;
    
    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
    @Autowired
    private CustomWebAuthenticationDetailsSource authenticationDetailsSource;
    
    @Autowired
    private CustomAuthenticationProvider customAuthProvider;
    
   

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
					.antMatchers("/", "/home", "/about").permitAll()
					.antMatchers("/admin/**").hasAnyRole("ADMIN")
					.antMatchers("/user/**").hasAnyRole("USER")
					.anyRequest().authenticated()
                .and()
                .formLogin()
					.loginPage("/login")
					 .successHandler(customAuthenticationSuccessHandler)
				      .authenticationDetailsSource(authenticationDetailsSource)
					.permitAll()
					.and()
                .logout()
                .logoutSuccessHandler(customlogoutSuccessHandler)
                .invalidateHttpSession(false)
                .deleteCookies("JSESSIONID")
					.permitAll()
					.and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	 auth.authenticationProvider(customAuthProvider);
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");
    }
}