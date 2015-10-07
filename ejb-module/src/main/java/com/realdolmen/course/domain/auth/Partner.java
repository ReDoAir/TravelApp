package com.realdolmen.course.domain.auth;


import com.realdolmen.course.domain.Airline;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.auth.User;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Partner extends User {


    @ManyToOne
    @NotNull
    private Airline airline;

    public Partner(){
        addRole(Role.PARTNER);
    }

    public Partner(String username, String password,Airline airline) {
        super(username,password);
        addRole(Role.PARTNER);
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
}
