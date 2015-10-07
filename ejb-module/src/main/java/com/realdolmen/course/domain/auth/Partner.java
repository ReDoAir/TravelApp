package com.realdolmen.course.domain.auth;



import com.realdolmen.course.domain.Airline;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Partner extends User {

    @OneToOne
    private Airline airline;

    public Partner(Airline airlines) {
//method of superclass
        addRole(Role.PARTNER);

    }

    public Partner(){

    }


    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
