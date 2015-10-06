package com.realdolmen.course.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by SDOAX36 on 5/10/2015.
 */
@Entity
public class Flight implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String flightCode;

    private Double price;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalDate;

    @ManyToOne
    private Airport arrivalAirport;

    @ManyToOne
    private Airline airline;

    @ManyToOne
    private Airport departAirport;

    @ManyToOne
    //A plane can fly a flight, but it will not disappear when the flight is over, probably it will disappear during the flight => set boolean LOST on true
    //But this is not LOST nor the Malysian airlines => we have banned that airline company!!!! so a plane can be re-used later on! that is why this is ManyToOne
    private Plane plane;


    public Flight(String flightCode, Date departureDate, Date arrivalDate,Double price) {
        this.flightCode = flightCode;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Airport getDepartAirport() {
        return departAirport;
    }

    public void setDepartAirport(Airport departAirport) {
        this.departAirport = departAirport;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
