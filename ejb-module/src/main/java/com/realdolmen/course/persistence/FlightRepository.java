package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Flight;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public class FlightRepository implements Serializable {

    @PersistenceContext
    EntityManager entityManager;

    public Flight findFlightById(int id) {
        return entityManager.find(Flight.class,id);
    }

    public List<Flight> findAllFlights() {
        return entityManager.createQuery("SELECT f from Flight f", Flight.class).getResultList();
    }
}
