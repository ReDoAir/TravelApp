package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Airline;
import com.realdolmen.course.domain.Airport;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.utils.DateUtil;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

import static com.realdolmen.course.domain.Flight.*;


@Stateless
@LocalBean
public class FlightRepo {

    @PersistenceContext
    public
    EntityManager em;

    public List<Flight> getAllFlights()
    {
        return em.createNamedQuery(Flight.FIND_ALL_FLIGHTS, Flight.class).getResultList();
    }

    public List<Flight>getAllFlightsByFlightCode(String flightCode)
    {
        return em.createNamedQuery(Flight.FIND_ALL_FLIGHTS_BY_FLIGHT_CODE, Flight.class).setParameter("flightCode","%"+flightCode+"%").getResultList();
    }

    //date is the startdate, it should come as a day with our 00:00:00 => endDay + 1day
    //this way the user can get a list of flights for that day
    //for what it's worth
    public List<Flight> getAllFlightsByDepartureDate(Date date)
    {
        Date endDate = DateUtil.addAFewDays(date, 1);
        return em.createNamedQuery(FIND_ALL_FLIGHTS_BY_DEPARTUREDATE,Flight.class).setParameter("startDate", date, TemporalType.TIMESTAMP).setParameter("endDate",endDate,TemporalType.TIMESTAMP).getResultList();
    }


    public List<Flight>getAllFlightsByArrivalDate(Date date)
    {
        Date endDate = DateUtil.addAFewDays(date, 1);
        return em.createNamedQuery(FIND_ALL_FLIGHTS_BY_ARRIVALDATE,Flight.class).setParameter("startDate",date).setParameter("endDate",endDate).getResultList();
    }

    public List<Flight>getAllFlightsByDepartureAirport(Airport airport)
    {
        return em.createNamedQuery(FIND_ALL_FLIGHTS_BY_DEPARTURE_AIRPORT,Flight.class).setParameter("departAirport",airport).getResultList();
    }

    public List<Flight>getAllFlightsByArrivalAirport(Airport airport)
    {
        return em.createNamedQuery(FIND_ALL_FLIGHTS_BY_ARRIVAL_AIRPORT,Flight.class).setParameter("arrivalAirport",airport).getResultList();
    }
    public List<Flight>getAllFlightsByDepartureAirportWithAvailableSeats(Airport airport)
    {
        return em.createNamedQuery(FIND_ALL_FLIGHTS_BY_DEPARTAIRPORT_WITH_AVIALABLESEATS,Flight.class).setParameter("departAirport",airport).getResultList();
    }

    public List<Flight>getAllFlightsByDepartureDateWithAvailableSeats(Date date)
    {
        Date endDate = DateUtil.addAFewDays(date, 1);
        return em.createNamedQuery(FIND_ALL_FLIGHTS_BY_DEPARTUREDATE_WITH_AVAILABLESEATS,Flight.class).setParameter("startDate",date).setParameter("endDate",endDate).getResultList();
    }
    public List<Flight>getAllFlightsByDestinationAndDepartureDateBetween(Airport departAirport,Date startDate,Date endDate)
    {
        return em.createNamedQuery(FIND_ALL_FLIGHTS_BY_DESTINATION_AND_ARRIVALDATE_BETWEEN,Flight.class)
                .setParameter("airport",departAirport)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
    public List<Flight>getAllFlightsByAirline(Airline airline)
    {
        return em.createNamedQuery(FIND_ALL_FLIGHTS_BY_AIRLINE,Flight.class).setParameter("airline",airline).getResultList();
    }


    public void addFlight(Flight flight)
    {
        em.persist(flight);
    }

    public Flight getFlightById(Integer flightId) {
        return em.find(Flight.class, flightId);
    }

    //still need more operations, we will do them later
}
