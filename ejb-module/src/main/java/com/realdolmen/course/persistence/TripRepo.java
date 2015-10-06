package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Trip;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class TripRepo {

    @PersistenceContext
    EntityManager em;

    public List<Trip> getAllTrips()
    {
        return em.createQuery("select t from Trip t", Trip.class).getResultList();

    }

    public List<Trip> getTripsByName(String name)
    {
        return em.createQuery("select t from Trip t where t.tripName like :name", Trip.class).setParameter("name", "%" + name + "%").getResultList();
    }

    public void addTrip(Trip trip)
    {
        em.persist(trip);
        em.flush();
    }
}
