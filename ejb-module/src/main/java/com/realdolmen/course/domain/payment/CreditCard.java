package com.realdolmen.course.domain.payment;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public class CreditCard extends PaymentMethod {

    @Basic(optional = false)
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
