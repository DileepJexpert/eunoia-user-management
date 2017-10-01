package com.eunoia.common.event;

import org.springframework.context.ApplicationEvent;

import com.eunoia.entity.User;

public class UserEvent extends ApplicationEvent {

    private User user;

    /** UserEventListener 
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}