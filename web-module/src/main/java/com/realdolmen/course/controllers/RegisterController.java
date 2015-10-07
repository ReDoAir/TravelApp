package com.realdolmen.course.controllers;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.realdolmen.course.domain.auth.Customer;
import com.realdolmen.course.domain.auth.User;
import com.realdolmen.course.persistence.UserRepo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import java.io.IOException;


@Named
@RequestScoped
public class RegisterController {

    private User user;

    @Inject
    private UserRepo service;

    @PostConstruct
    public void init() {
        user = new Customer();
    }

    public void register() throws IOException {
        try {
            user.setPassword(new Sha256Hash(user.getPassword()).toHex());
            service.create(user);
            Messages.addGlobalInfo("Registration succeed, new user ID is: {0}", user.getId());
            Faces.redirect("/web-module/app/anon/login.faces");
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
