package com.eunoia.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eunoia.common.event.UserEvent;
import com.eunoia.common.exception.ResourceNotFoundException;
import com.eunoia.common.utils.CustomErrorType;
import com.eunoia.entity.User;
import com.eunoia.repository.UserRepository;
import com.eunoia.services.IUserService;
import com.eunoia.web.dto.UserDto;

@RestController
@RequestMapping("/api")
public class UserResource {
	
	 private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private IUserService userService;
	
	 @Autowired
	    private ApplicationEventPublisher applicationEventPublisher;
	
	// Create a new ser
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody UserDto userdto) {
		   final User registered = userService.registerNewUserAccount(userdto);
		return registered;
	   
	}
	
	  // -------------------Retrieve All Users---------------------------------------------
	 
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
 
    // -------------------Retrieve Single User------------------------------------------
 
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
    	LOGGER.info("Fetching User with id {}", id);
        User user = userService.findById(id);
    /*    if (user == null) {
        	LOGGER.error("User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("User with id " + id  + " not found"), HttpStatus.NOT_FOUND);
        }*/
        if (user == null) {
            throw new ResourceNotFoundException(id, "user not found");
        }
        
        UserEvent userEvent = new UserEvent(this, user);
        
        applicationEventPublisher.publishEvent(userEvent);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
 

}
