package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Trip;

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
public class TripRepo {

    @Inject
    EntityManager em;

    public List<Trip> getAllTrips()
    {
        return em.createQuery("select t from Trip t").getResultList();

    }

    public List<Trip> getTripsByName(String name)
    {
        return em.createQuery("select t from Trip t where t.tripName like :name").setParameter("name", "%" + name + "%").getResultList();
    }

    public void addTrip(Trip trip)
    {
        em.persist(trip);
        em.flush();
    }
}
