package com.realdolmen.course.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by SDOAX36 on 5/10/2015.
 */
@Entity
public class Airport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String airportCode;

    private double pricePerDay;

    @ManyToOne
    private Country country;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arrivalAirport")
    private List<Flight> arrivalFlights;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departAirport")
    private List<Flight> departFlights;

    public Airport() {
    }

    public Airport(String name, String airportCode, double pricePerDay) {
        this.name = name;
        this.airportCode = airportCode;
        this.pricePerDay = pricePerDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
