package com.realdolmen.course.services;

import com.realdolmen.course.domain.Airline;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.persistence.AirportRepo;
import com.realdolmen.course.persistence.FlightRepo;
import com.realdolmen.course.persistence.PartnerRepo;
import com.realdolmen.course.persistence.PlaneRepo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Stateless
public class FlightService implements Serializable {

    @Inject
    private FlightRepo flightRepo;
    @Inject
    private PartnerRepo partnerRepo;
    @Inject
    private PlaneRepo planeRepo;
    @Inject
    private AirportRepo airportRepo;

    public void createFlight(String username, String flightCode, Date departureDate, Date arrivalDate, Double price, String departureAirport, String arrivalAirport, String plane){
        Flight flight = new Flight();

        Airline airline = partnerRepo.findPartner(username).getAirline();

        flight.setAirline(airline);
        flight.setFlightCode(flightCode);
        flight.setDepartureDate(departureDate);
        flight.setArrivalDate(arrivalDate);
        flight.setPrice(price);
        flight.setDepartAirport(airportRepo.getAirportByCode(departureAirport));
        flight.setArrivalAirport(airportRepo.getAirportByCode(arrivalAirport));
        flight.setPlane(planeRepo.getPlaneByCode(plane));

        flightRepo.addFlight(flight);
    }


    public List<Flight> getAllFlights() {
        return flightRepo.getAllFlights();
    }
}
