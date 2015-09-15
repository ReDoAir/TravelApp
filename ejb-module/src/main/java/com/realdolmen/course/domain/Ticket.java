package com.realdolmen.course.domain;

import javax.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double price;

    @ManyToOne
    private Passenger passenger;

    @ManyToOne
    private Flight flight;

    public Ticket(double price) {
        this.price = price;
    }

    public Ticket(double price, Passenger passenger, Flight flight) {
        this.price = price;
        this.passenger = passenger;
        this.flight = flight;
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

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
