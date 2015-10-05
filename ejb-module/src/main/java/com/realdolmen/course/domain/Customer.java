package com.realdolmen.course.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User {
    private String creditCard;

    public Customer() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(Role.CUSTOMER);
        this.setRoles(roleList);
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
}
