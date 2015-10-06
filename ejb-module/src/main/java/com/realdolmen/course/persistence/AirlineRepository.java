package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Airline;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by SDOAX36 on 5/10/2015.
 */
@Stateless
@LocalBean
public class AirlineRepository {


    @Inject
    EntityManager em;

    public List<Airline> findAllAirlines()
    {
        return em.createQuery("select a from Airline a", Airline.class).getResultList();
    }

    public List<Airline> findAirlinesByName(String airlineName)
    {
        return em.createQuery("select a from Airline a where a.name LIKE :airlineName", Airline.class).setParameter("airlineName","%" + airlineName + "%").getResultList();
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
