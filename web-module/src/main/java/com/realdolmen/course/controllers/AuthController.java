package com.realdolmen.course.controllers;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@Named
@RequestScoped
public class AuthController {

    public static final String HOME_URL = "/web-module/app/anon/index.faces";
    public static final String CUST_URL = "/web-module/app/cust/home.faces";

    private String username;
    private String password;
    private boolean remember;

    public void login() throws IOException {
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, remember));
            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
            SecurityUtils.getSubject().getSession().setAttribute("userName", username);
            Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : CUST_URL);
        }
        catch (AuthenticationException e) {
            Messages.addGlobalError("Unknown user, please try again");
            e.printStackTrace(); // TODO: logger.
        }
    }

    public void logout() throws IOException {
        SecurityUtils.getSubject().logout();
        Faces.invalidateSession();
        Faces.redirect(HOME_URL);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
