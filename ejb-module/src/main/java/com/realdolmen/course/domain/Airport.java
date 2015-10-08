package com.realdolmen.course.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
public class Airport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String airportCode;

    @Basic(optional = false)
    private String city;

    @ManyToOne
    @NotNull
    private Country country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arrivalAirport")
    private List<Flight> arrivalFlights;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departAirport")
    private List<Flight> departFlights;

    public Airport() {
    }

    public Airport(String name, String airportCode) {
        this.name = name;
        this.airportCode = airportCode;

    }

    public Airport(String name, String airportCode, String city, Country country) {
        this.name = name;
        this.airportCode = airportCode;
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Flight> getArrivalFlights() {
        return arrivalFlights;
    }

    public void setArrivalFlights(List<Flight> arrivalFlights) {
        this.arrivalFlights = arrivalFlights;
    }

    public List<Flight> getDepartFlights() {
        return departFlights;
    }

    public void setDepartFlights(List<Flight> departFlights) {
        this.departFlights = departFlights;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
