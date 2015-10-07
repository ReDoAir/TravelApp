package com.realdolmen.course.domain;

import com.realdolmen.course.domain.auth.Partner;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SDOAX36 on 5/10/2015.
 */
@Entity
public class Airline implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    private String name;


    @OneToMany (cascade = CascadeType.ALL, mappedBy = "airline")
    private List<Flight>flights;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "airline")
    private List<Partner> partners;

    public Airline(String name) {
        this.name = name;
        flights = new ArrayList<>();
        partners = new ArrayList<>();
    }

    public Airline()
    {
        flights = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addFlight(Flight flight)
    {
        if(flights == null)
        {
            flights = new ArrayList<>();
        }
        flights.add(flight);
    }

    private void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    private void setPartners(List<Partner> partners) {
        this.partners = partners;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public List<Partner> getPartners() {
        return partners;
    }

    public void addPartner(Partner partner)
    {

    }
}
