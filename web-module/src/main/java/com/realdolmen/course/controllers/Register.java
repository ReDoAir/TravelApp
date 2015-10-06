package com.realdolmen.course.controllers;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.realdolmen.course.domain.Customer;
import com.realdolmen.course.domain.User;
import com.realdolmen.course.persistence.UserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.omnifaces.util.Messages;


@Named
@RequestScoped
public class Register {

    private User user;

    @EJB
    private UserService service;

    @PostConstruct
    public void init() {
        user = new Customer();
    }

    public void submit() {
        try {
            user.setPassword(new Sha256Hash(user.getPassword()).toHex());
            service.create(user);
            Messages.addGlobalInfo("Registration succeed, new user ID is: {0}", user.getId());
        }
        catch (RuntimeException e) {
            Messages.addGlobalError("Registration failed: {0}", e.getMessage());
            e.printStackTrace(); // TODO: logger.
        }
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }
}
