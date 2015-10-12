package com.realdolmen.course.domain.payment;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public class Endorsement extends PaymentMethod {
    @Basic(optional = false)
    private String bank;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
