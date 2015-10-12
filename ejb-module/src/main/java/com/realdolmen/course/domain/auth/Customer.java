package com.realdolmen.course.domain.auth;

import com.realdolmen.course.domain.Booking;
import com.realdolmen.course.domain.payment.PaymentMethod;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "customer")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private List<PaymentMethod> paymentMethods;

    public Customer() {
        paymentMethods = new ArrayList<>();
        addRole(Role.CUSTOMER);
    }

    public Customer(String username, String password) {
        super(username, password);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void addPaymentMethod(PaymentMethod paymentMethod){
        paymentMethod.setCustomer(this);
        paymentMethods.add(paymentMethod);
    }
}
