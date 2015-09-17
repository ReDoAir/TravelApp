package com.realdolmen.course.controller;

import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.Ticket;
import com.realdolmen.course.persistence.FlightRepository;
import com.realdolmen.course.persistence.PassengerRepository;
import com.realdolmen.course.persistence.TicketRepository;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class TicketController implements Serializable{

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

    public String createTicket(int id){
        Flight f = flightRepository.findFlightById(flightId);
        Passenger p = passengerRepository.findPassengerById(id);
        ticketRepository.create(new Ticket(price,p,f));
        return "tickets?faces-redirect=true";
    }

    public List<Ticket> getTickets() {
        return ticketRepository.getAllTickets();
    }
}
