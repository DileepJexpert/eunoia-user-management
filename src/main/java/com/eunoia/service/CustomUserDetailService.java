package com.eunoia.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunoia.entity.Privilege;
import com.eunoia.entity.Role;
import com.eunoia.entity.User;
import com.eunoia.repository.UserRepository;

@Service("userDetailsService")
@Transactional
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HttpServletRequest request;

	@Override
	public org.springframework.security.core.userdetails.User loadUserByUsername(final String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 try {
	            final User user = userRepository.findByEmail(email);
	            if (user == null) {
	            	// uncomment this after registeration of firsttime
	            	
	            	
	              //  throw new UsernameNotFoundException("No user found with username: " + email);
	            }

	            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
	        } catch (final Exception e) {
	            throw new RuntimeException(e);
	        }
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		  return getGrantedAuthorities(getPrivileges(roles));
	}

	private final List<String> getPrivileges(final Collection<Role> roles) {
        final List<String> privileges = new ArrayList<String>();
        final List<Privilege> collection = new ArrayList<Privilege>();
        for (final Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (final Privilege item : collection) {
            privileges.add(item.getName());
        }
        
        return privileges;
    }
	
	  private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
	        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        for (final String privilege : privileges) {
	            authorities.add(new SimpleGrantedAuthority(privilege));
	        }
	        return authorities;
	    }
}