package com.realdolmen.course.domain.auth;

import com.realdolmen.course.domain.Booking;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Customer extends User {


    private String creditCard;

    @OneToMany(mappedBy = "customer")
    private List<Booking> bookings;

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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
