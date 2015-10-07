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


    @ManyToOne
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
