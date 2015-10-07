package com.realdolmen.course.domain.auth;


import com.realdolmen.course.domain.Airline;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.auth.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Partner extends User {


    @ManyToOne
    @NotNull
    private Airline airline;

    @Id
    private Long id;

    public Long getId() {
        return id;
    }
    public Partner(){

    }

    public Partner(String username, String password,Airline airline) {
        super(username,password);
        this.airline = airline;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airlines) {

        this.airline = airlines;
        this.airline.addPartner(this);
    }
    
    public void addNewFlightToAirline(Flight flight)
    {
        this.airline.addFlight(flight);
    }
    
    public void setId(Long id) {
        this.id = id;
    }
}
