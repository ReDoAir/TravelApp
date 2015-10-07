package com.realdolmen.course.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class Flight implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String flightCode;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalDate;

    private int availablePlaces;

    @OneToOne
    private Airline airline;

    @ManyToOne
    private Airport arrivalAirport;

    @ManyToOne
    private Airport departAirport;

    public Flight(String flightCode, Date departureDate, Date arrivalDate) {
        this.flightCode = flightCode;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public Flight() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
