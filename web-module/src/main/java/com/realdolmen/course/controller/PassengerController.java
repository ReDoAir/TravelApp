package com.realdolmen.course.controller;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.PassengerType;
import com.realdolmen.course.persistence.PassengerRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class PassengerController {
    @Inject
    private PassengerRepository passengerRepository;

    private Passenger passenger = new Passenger();

    public String createPassenger(){
        passenger.setPassengerType(PassengerType.OCCASIONAL);
        passengerRepository.create(passenger);
        return "bookTicket";
    }

    public Passenger getPassenger(){
        return passenger;
    }

    public String getName(){
        return passenger.getFirstName() + " " + passenger.getLastName();
    }

}
