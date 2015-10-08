package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Airline;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.realdolmen.course.domain.Airline.FIND_AIRLINE_BY_NAME_Q;
import static com.realdolmen.course.domain.Airline.FIND_ALL_AIRLINES_Q;

@Stateless
@LocalBean
public class AirlineRepository {


    @PersistenceContext
    public
    EntityManager em;

    /*

    public static final String FIND_ALL_AIRLINES_Q = "Airline.findAll";
    public static final String FIND_AIRLINE_BY_NAME_Q = "Airlines.findAirlineByName";
     */

    public List<Airline> findAllAirlines()
    {
        return em.createNamedQuery(FIND_ALL_AIRLINES_Q,Airline.class).getResultList();
    }

    public List<Airline> findAirlinesByName(String airlineName)
    {
        return em.createNamedQuery(FIND_AIRLINE_BY_NAME_Q,Airline.class).setParameter("airlineName", "%" + airlineName + "%").getResultList();
    }
    public Airline findAirlineById(int id)
    {
        return em.find(Airline.class,id);
    }

    public void addAirline(Airline airline)
    {
        em.persist(airline);
        flush();

    }

    public void removeAirline(Airline airline)
    {
        em.remove(airline);
        flush();
    }



    public void flush()
    {
        em.flush();
    }
}
