package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Airline;
import com.realdolmen.course.domain.Airport;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Plane;
import com.realdolmen.course.persistence.AirportRepo;
import com.realdolmen.course.persistence.FlightRepo;
import com.realdolmen.course.persistence.PartnerRepo;
import com.realdolmen.course.persistence.PlaneRepo;
import com.realdolmen.course.services.FlightService;
import org.apache.shiro.SecurityUtils;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Named
@RequestScoped
public class FlightController {

    @Inject
    private FlightService flightService;

    private Date arrivalDate;
    private Date departureDate;
    private String flightCode;
    private Double price;
    private String arrivalAirport;
    private String departureAirport;
    private String plane;

    public void createFlight(){
        String username = (String) SecurityUtils.getSubject().getSession().getAttribute("userName");
        flightService.createFlight(username,flightCode,departureDate,arrivalDate,price,departureAirport,arrivalAirport,plane);
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public Double getPrice() {
        return price;
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

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }


    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getPlane() {
        return plane;
    }

    public List<Flight> getFlights(){
        return flightService.getAllFlights();
    }
}
