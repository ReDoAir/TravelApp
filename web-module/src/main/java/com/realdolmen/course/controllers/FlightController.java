package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Airport;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Plane;
import com.realdolmen.course.persistence.FlightRepo;
import com.realdolmen.course.persistence.PartnerRepo;
import org.apache.shiro.SecurityUtils;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Date;
import java.util.DoubleSummaryStatistics;

@Named
@RequestScoped
public class FlightController {

    private Date arrivalDate;
    private Date departureDate;
    private String flightCode;
    private Double price;
    private Airport arrivalAirport;
    private Airport departureAirport;
    private Plane plane;

    @EJB
    private FlightRepo flightRepo;
    @EJB
    private PartnerRepo partnerRepo;

    public void createFlight(){
        Flight flight = new Flight();

        flight.setAirline(partnerRepo.findPartner((String)SecurityUtils.getSubject().getSession().getAttribute("userName")).getAirline());
        flight.setFlightCode(flightCode);
        flight.setArrivalDate(arrivalDate);
        flight.setDepartureDate(departureDate);
        flight.setPrice(price);
        flight.setDepartAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flight.setPlane(plane);

        flightRepo.addFlight(flight);
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

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }
}
