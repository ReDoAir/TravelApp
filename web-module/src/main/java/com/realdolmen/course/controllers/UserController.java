package com.realdolmen.course.controllers;

import org.apache.shiro.SecurityUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class UserController implements Serializable{
    public String getUserName() {
        return (String) SecurityUtils.getSubject().getSession().getAttribute("userName");
    }
}
