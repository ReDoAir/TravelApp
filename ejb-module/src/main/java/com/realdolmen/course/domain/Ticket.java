package com.realdolmen.course.domain;

import javax.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    private Passenger passenger;

    public Ticket(double price) {
        this.price = price;
    }

    public Ticket() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return String.format("ID: %d - PRICE: %.2f",id,price);
    }
}
