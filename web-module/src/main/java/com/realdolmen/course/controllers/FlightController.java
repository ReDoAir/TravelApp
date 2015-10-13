package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.auth.Partner;
import com.realdolmen.course.persistence.PartnerRepo;
import com.realdolmen.course.services.FlightService;
import org.apache.shiro.SecurityUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class FlightController {

    @Inject
    private FlightService flightService;
    @Inject
    private PartnerRepo partnerRepo;

    private Date arrivalDate;
    private Date departureDate;
    private String flightCode;
    private Double price;
    private String arrivalAirport;
    private String departureAirport;
    private String plane;
    private String userName;

    public void createFlight(){
        userName = (String) SecurityUtils.getSubject().getSession().getAttribute("userName");
        flightService.createFlight(userName,flightCode,departureDate,arrivalDate,price,departureAirport,arrivalAirport,plane);
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public List<Flight> getFlights(){
        return flightService.getAllFlights();
    }

    public List<Flight> getAllFlightsOfAirline(){
        Partner partner = partnerRepo.findPartner(userName);

        return flightService.getAllFlightsByAirline(partner.getAirline());
    }
}
