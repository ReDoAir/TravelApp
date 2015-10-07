package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Destination;
import com.realdolmen.course.domain.Trip;
import com.realdolmen.course.persistence.TripRepo;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class TripController {

    private List<Trip> trips = new ArrayList<>();
    private List<Destination> destinations = new ArrayList<>();

    @EJB
    private TripRepo tripRepo;
    private Destination destination;

    public void tripsToDestination(int count){
        trips = tripRepo.getTripsByDestinationWithEnoughPlaces(count);
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
