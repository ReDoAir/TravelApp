package com.realdolmen.course.services;

import com.realdolmen.course.domain.Airline;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.exceptions.ArrivalCannotBeBeforeDepartException;
import com.realdolmen.course.domain.exceptions.DepartAndArrivalAreTheSameException;
import com.realdolmen.course.persistence.AirportRepo;
import com.realdolmen.course.persistence.FlightRepo;
import com.realdolmen.course.persistence.PartnerRepo;
import com.realdolmen.course.persistence.PlaneRepo;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Stateless
public class FlightService implements Serializable {

    private static final Logger logger = Logger.getLogger(FlightService.class);

    @Inject
    private FlightRepo flightRepo;
    @Inject
    private PartnerRepo partnerRepo;
    @Inject
    private PlaneRepo planeRepo;
    @Inject
    private AirportRepo airportRepo;

    public int createFlight(String username, String flightCode, Date departureDate, Date arrivalDate, Double price, String departureAirport, String arrivalAirport, String plane){
        try {
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

            return 0;
        } catch (ArrivalCannotBeBeforeDepartException e) {
            logger.warn(e.getMessage());
           return -1;
        }catch (DepartAndArrivalAreTheSameException e){
            logger.warn(e.getMessage());
            return -2;
        }
    }


    public List<Flight> getAllFlights() {
        return flightRepo.getAllFlights();
    }

    public Flight getFlightById(Integer flightId) {
        return flightRepo.getFlightById(flightId);
    }

    public void updateAvailableSpotsOnFlight(Flight flight, int count) {
        int newAmount = flight.getAvailablePlaces() - count;
        if(newAmount > 0) {
            flight.setAvailablePlaces(newAmount);
            flightRepo.updateFlight(flight);
        } else{
            throw new IllegalArgumentException("Available places cannot be less than 0");
        }
    }

    public List<Flight> getAllFlightsByAirline(Airline airline) {
        return flightRepo.getAllFlightsByAirline(airline);
    }
}
