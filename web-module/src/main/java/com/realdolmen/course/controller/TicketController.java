package com.realdolmen.course.controller;

import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.Ticket;
import com.realdolmen.course.persistence.FlightRepository;
import com.realdolmen.course.persistence.PassengerRepository;
import com.realdolmen.course.persistence.TicketRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class TicketController {

    private int price;
    private Integer flightId;

    @Inject
    private TicketRepository ticketRepository;
    @Inject
    private FlightRepository flightRepository;
    @Inject
    private PassengerRepository passengerRepository;


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public String createTicket(String id){
        Flight f = flightRepository.findFlightById(flightId);
        Passenger p = passengerRepository.findPassengerById(Integer.parseInt(id));
        ticketRepository.create(new Ticket(price,p,f));
        return "tickets";
    }
}
