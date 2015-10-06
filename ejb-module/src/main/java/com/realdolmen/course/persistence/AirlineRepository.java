package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Airline;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class AirlineRepository {


    @PersistenceContext
    EntityManager em;

    public List<Airline> findAllAirlines()
    {
        return em.createQuery("select a from Airline a", Airline.class).getResultList();
    }

    public List<Airline> findAirlinesByName(String airlineName)
    {
        return em.createQuery("select a from Airline a where a.name LIKE :airlineName", Airline.class).setParameter("airlineName", "%" + airlineName + "%").getResultList();
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
