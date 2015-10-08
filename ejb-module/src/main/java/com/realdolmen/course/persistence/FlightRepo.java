package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Flight;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;


@Stateless
@LocalBean
public class FlightRepo {

    @PersistenceContext
    EntityManager em;

    public List<Flight> getAllFlights()
    {
        return em.createQuery("select f from Flight f", Flight.class).getResultList();
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
