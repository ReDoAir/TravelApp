package com.realdolmen.course.domain.payment;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class CreditCard extends PaymentMethod {

    @Basic(optional = false)
    private String number;
    @Basic(optional = false)
    private Date expireDate;

    public CreditCard(String number, Date expire) {
        super("creditcard");
        this.number = number;
        expireDate = expire;
    }

    public CreditCard() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard)) return false;

        CreditCard that = (CreditCard) o;

        if (!number.equals(that.number)) return false;
        return expireDate.equals(that.expireDate);

    }

    @Override
    public int hashCode() {
        int result = number.hashCode();
        result = 31 * result + expireDate.hashCode();
        return result;
    }
}
