package com.realdolmen.course.controllers;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.omnifaces.util.Faces;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class WorldMapController {
    @Inject
    private SearchController searchController;

    private String country;
    private int amount;

    public void search() throws IOException {
        searchController.setCountry(country);
        searchController.setCount(amount);

        Subject currentUser = SecurityUtils.getSubject();

        if(currentUser.hasRole("CUSTOMER")) {
            Faces.redirect(AuthController.CUST_URL);
        }else{
            Faces.redirect(AuthController.SEARCH_URL);
        }
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
