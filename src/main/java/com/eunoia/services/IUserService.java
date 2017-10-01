package com.eunoia.services;



import java.util.List;

import com.eunoia.entity.User;
import com.eunoia.web.dto.UserDto;

public interface IUserService {
	  User registerNewUserAccount(UserDto accountDto);

	List<User> findAllUsers();

	User findById(long id);
}
