package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.*;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import java.util.Date;

@Stateful
@LocalBean
public class PassengerEJB implements RemotePassengerEJB{

    @EJB
    private TicketRepository ticketRepository;
    @EJB
    private PassengerRepository passengerRepository;

    private Passenger passenger;

    @Override
    public void createPassenger(String ssn, String firstName, String lastName, Date dateOfBirth, PassengerType passengerType, Integer frequentFlyerMiles){
        passenger = new Passenger(ssn,firstName,lastName,dateOfBirth,passengerType,frequentFlyerMiles);
    }

    @Override
    public void addAddress(String street1, String street2, String city, String zipCode, String country){
        if(passenger != null)
            passenger.setAddress(new Address(street1,street2,city,zipCode,country));
    }

    @Override
    public void addCreditCard(String number, String expiryDate, Integer controlNumber, CreditCardType creditCardType){
        if(passenger != null)
            passenger.setCreditCard(new CreditCard(number, expiryDate, controlNumber, creditCardType));
    }

    @Override
    public void addTicket(double price){
        if(passenger != null) {
            Ticket ticket = new Ticket(price);
            ticketRepository.create(ticket);

            passenger.addTicket(ticket);
        }
    }

    @Remove
    public void checkOut(){
        if(passenger != null)
        passengerRepository.create(passenger);
    }
}
