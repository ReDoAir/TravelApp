package com.realdolmen.course.domain;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Trip implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tripName;

    @OneToMany
    private List<Flight> flights;

    @ManyToOne
    private Destination destination;

    @ManyToOne
    private Residence residence;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }
}
