package com.eunoia.security;

import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.eunoia.entity.User;

public class ApplicationUser extends User {
private static final long serialVersionUID = 1L;
    private final String email;
    public ApplicationUser(String username, String password, boolean enabled,
        boolean accountNonExpired, boolean credentialsNonExpired,
        boolean accountNonLocked,
        SimpleGrantedAuthority grantedAuths,
        String email) {
            super(username, password, enabled, accountNonExpired,
               credentialsNonExpired, accountNonLocked, grantedAuths);
            this.email = email;
    }
public String getEmail() {
return email;
}
 }