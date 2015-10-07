package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.persistence.FlightRepo;
import com.realdolmen.course.persistence.PartnerRepo;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Date;

@Named
@RequestScoped
public class FlightController {

    private Date arrivalDate;
    private Date departureDate;
    private String flightCode;

    @EJB
    private FlightRepo flightRepo;
    @EJB
    private PartnerRepo partnerRepo;

    public void createFlight(String userName){
        Flight flight = new Flight();
        //flight.setAirline(partnerRepo.findPartner(userName).getAirline());
        flight.setFlightCode(flightCode);
        flight.setArrivalDate(arrivalDate);
        flight.setDepartureDate(departureDate);
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }
}
