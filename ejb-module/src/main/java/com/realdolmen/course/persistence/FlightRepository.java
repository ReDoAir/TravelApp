package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Flight;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class FlightRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Flight findFlightById(int id) {
        return entityManager.find(Flight.class,id);
    }
}
