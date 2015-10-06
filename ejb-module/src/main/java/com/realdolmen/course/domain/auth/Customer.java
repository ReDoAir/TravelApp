package com.realdolmen.course.domain.auth;

import javax.persistence.Entity;

@Entity
public class Customer extends User {
    private String creditCard;

    public Customer() {
        addRole(Role.CUSTOMER);

    }

    //
    public Customer(String username, String password) {
        super(username, password, Role.CUSTOMER);
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
}
