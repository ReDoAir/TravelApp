package com.realdolmen.course.persistence;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by SSTAX38 on 10/09/2015.
 */
@Embeddable
public class CreditCard {
    private String number;
    private String expiryDate;
    private Integer controlNumber;
    @Enumerated(EnumType.STRING)
    private CreditCardType creditCardType;

    public CreditCard(String number, String expiryDate, Integer controlNumber, CreditCardType creditCardType) {
        this.number = number;
        this.expiryDate = expiryDate;
        this.controlNumber = controlNumber;
        this.creditCardType = creditCardType;
    }

    public CreditCard() {
    }

    public String getNumber() {
        return number;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public CreditCardType getCreditCardType() {
        return creditCardType;
    }

    public Integer getControlNumber() {
        return controlNumber;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setControlNumber(Integer controlNumber) {
        this.controlNumber = controlNumber;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCreditCardType(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }
}
