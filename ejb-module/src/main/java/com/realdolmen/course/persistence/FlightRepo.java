package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Flight;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

/**
 * Created by SDOAX36 on 5/10/2015.
 */
@Stateless
@LocalBean
public class FlightRepo {

    @Inject
    EntityManager em;

    public List<Flight> getAllFlights()
    {
        return em.createQuery("select f from Flight f").getResultList();
    }


    public void addFlight(Flight flight)
    {
        em.persist(flight);
    }

    //still need more operations, we will do them later
}
