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

    public Trip getTripsById(Integer tripId) {
        return em.find(Trip.class, tripId);
    }

    //not yet on point (need to know relation trip - flight)
    public List<Trip> getTripsByDestinationWithEnoughPlaces(int numberOfPass) {
        //return em.createQuery("SELECT t FROM Trip t WHERE t.flights IN (SELECT f FROM Flight f WHERE f.availablePlaces > :numberOfPass)", Trip.class).setParameter("numberOfPass", numberOfPass).getResultList();
        return null;
    }
}
