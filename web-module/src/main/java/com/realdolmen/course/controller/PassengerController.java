package com.realdolmen.course.controller;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.PassengerType;
import com.realdolmen.course.persistence.PassengerRepository;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class PassengerController implements Serializable {
    @Inject
    private PassengerRepository passengerRepository;

    private Passenger passenger = new Passenger();

    public String createPassenger(){
        passenger.setPassengerType(PassengerType.OCCASIONAL);
        passengerRepository.create(passenger);
        return "bookTicket?faces-redirect=true";
    }

    public Passenger getPassenger(){
        return passenger;
    }

}
