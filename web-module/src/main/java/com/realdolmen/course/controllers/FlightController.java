package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Airport;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Plane;
import com.realdolmen.course.persistence.AirportRepo;
import com.realdolmen.course.persistence.FlightRepo;
import com.realdolmen.course.persistence.PartnerRepo;
import com.realdolmen.course.persistence.PlaneRepo;
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
    private String arrivalAirport;
    private String departureAirport;
    private String plane;

    @EJB
    private FlightRepo flightRepo;
    @EJB
    private PartnerRepo partnerRepo;
    @EJB
    private PlaneRepo planeRepo;
    @EJB
    private AirportRepo airportRepo;

    public void createFlight(){
        Flight flight = new Flight();

        flight.setAirline(partnerRepo.findPartner((String) SecurityUtils.getSubject().getSession().getAttribute("userName")).getAirline());
        flight.setFlightCode(flightCode);
        flight.setArrivalDate(arrivalDate);
        flight.setDepartureDate(departureDate);
        flight.setPrice(price);
        flight.setDepartAirport(airportRepo.getAirportByCode(departureAirport));
        flight.setArrivalAirport(airportRepo.getAirportByCode(arrivalAirport));
        flight.setPlane(planeRepo.getPlaneByCode(plane));

        flightRepo.addFlight(flight);
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
}
