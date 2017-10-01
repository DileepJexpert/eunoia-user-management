package com.eunoia.common.listener;

import org.springframework.stereotype.Component;

import com.eunoia.common.event.UserEvent;


import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

@Component
public class UserEventListener{
	@Async
	  @EventListener(condition = "#userEvent.admin")

    public void handleUserEvent(UserEvent event) {
        //do some operations
        System.out.println(event.getUser().getFirstName());
    }
}