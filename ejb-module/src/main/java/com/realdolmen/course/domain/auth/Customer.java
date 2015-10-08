package com.realdolmen.course.domain.auth;

import com.realdolmen.course.domain.Booking;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

@Entity
@NamedQueries(
        {
                @NamedQuery(name = "Customer.findAll",query = "select c from Customer c"),
                @NamedQuery(name = "Customer.findByUsername",query="select c from Customer c where c.username like :username")
        }
)
public class Customer extends User {

    public static final String FIND_ALL_CUSTOMERS = "Customer.findAll",
    FIND_CUSTOMER_BY_USERNAME = "Customer.findByUsername";

    private String creditCard;

    @OneToMany(mappedBy = "customer")
    private List<Booking> bookings;

    public Customer() {
        addRole(Role.CUSTOMER);

    }

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
