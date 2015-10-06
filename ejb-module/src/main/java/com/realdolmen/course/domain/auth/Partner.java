package com.realdolmen.course.domain.auth;



import javax.persistence.Entity;

@Entity
public class Partner extends User {

    private String airlines;

    public Partner(String airlines) {
        this.airlines = airlines;
//method of superclass
        addRole(Role.PARTNER);

    }

    public Partner(){

    }

    public String getAirlines() {
        return airlines;
    }

    public void setAirlines(String airlines) {
        this.airlines = airlines;
    }
}
