package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Trip;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class AuthController {

    public static final String HOME_URL = "/web-module/app/anon/index.faces";
    public static final String CUST_URL = "/web-module/app/cust/search.faces";
    public static final String EMPL_URL = "/web-module/app/empl/home.faces";
    public static final String PART_URL = "/web-module/app/part/home.faces";
    public static final String BOOKING_URL = "/web-module/app/cust/booktrip.faces";
    public static final String SEARCH_URL = "/web-module/app/anon/search.faces";

    private String username;
    private String password;
    private boolean remember;

    public void login() throws IOException {
        try {
            Subject currentUser = SecurityUtils.getSubject();

            currentUser.login(new UsernamePasswordToken(username, password, remember));
            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());

            //keep the username in the session for booking , adding trips and adding flights
            SecurityUtils.getSubject().getSession().setAttribute("userName", username);

            //redirect to the right home page
            if(currentUser.hasRole("CUSTOMER")) {
                Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : HOME_URL);
            }else if(currentUser.hasRole("PARTNER")){
                Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : PART_URL);
            }else if(currentUser.hasRole("EMPLOYEE")){
                Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : EMPL_URL);
            }

        }
        catch (AuthenticationException e) {
            Messages.addGlobalError("Username and/or password were wrong");
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

    public void goToBookingPage(Trip trip) throws IOException {
        Faces.setSessionAttribute("trip",trip);
        Faces.redirect(BOOKING_URL);
    }
}
