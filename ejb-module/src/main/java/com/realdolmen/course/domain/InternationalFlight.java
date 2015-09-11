package com.realdolmen.course.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;

@Entity
//Zorgt voor errors
//@PrimaryKeyJoinColumn(name = "internationalFlightId")
public class InternationalFlight extends Flight {
    private boolean blacklisted;
    private String regulations;

    public InternationalFlight(String number,Date departureDate ,Date arrivalDate , boolean blacklisted, String regulations) {
        super(number,arrivalDate,departureDate);
        this.blacklisted = blacklisted;
        this.regulations = regulations;
    }

    public InternationalFlight() {
    }

    public String getRegulations() {
        return regulations;
    }

    public void setRegulations(String regulations) {
        this.regulations = regulations;
    }

    public boolean isBlacklisted() {
        return blacklisted;
    }

    public void setBlacklisted(boolean blacklisted) {
        this.blacklisted = blacklisted;
    }
}
