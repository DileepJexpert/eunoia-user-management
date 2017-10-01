package com.eunoia.services;


import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eunoia.entity.User;
import com.eunoia.repository.RoleRepository;
import com.eunoia.repository.UserRepository;
import com.eunoia.web.dto.UserDto;

@Service
@Transactional
public class UserService implements IUserService {

	
	@Autowired
	private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

  
    
    @Override
    public User registerNewUserAccount(final UserDto accountDto) {
    
        final User user = new User();

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
     //   user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setUsing2FA(accountDto.isUsing2FA());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }



	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
