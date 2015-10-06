package com.realdolmen.course.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User {


    private String creditCard;

    public Customer() {
        //addRole(Role.CUSTOMER);

    }

    //
    public Customer(String username, String password, String creditCard ) {

        super(username, password);
        this.creditCard = creditCard;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
}
