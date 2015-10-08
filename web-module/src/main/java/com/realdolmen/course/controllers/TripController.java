package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Trip;
import com.realdolmen.course.persistence.FlightRepo;
import com.realdolmen.course.persistence.PeriodRepo;
import com.realdolmen.course.persistence.TripRepo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class TripController {

    private List<Trip> trips = new ArrayList<>();

    @Inject
    private TripRepo tripRepo;
    @Inject
    private PeriodRepo periodRepo;
    @Inject
    private FlightRepo flightRepo;

    private String name;
    private Integer periodId;
    private Integer depFlightId;
    private Integer returnFlightId;

    public void tripsToDestination(int count){
        trips = tripRepo.getTripsByDestinationWithEnoughPlaces(count);
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public void createTrip(){
        Trip trip = new Trip();

        trip.setTripName(name);
        trip.setPeriod(periodRepo.getPeriodById(periodId));
        trip.setToFlight(flightRepo.getFlightById(depFlightId));
        trip.setFromFlight(flightRepo.getFlightById(returnFlightId));

        tripRepo.addTrip(trip);
        //trip.setDestination();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    public Integer getDepFlightId() {
        return depFlightId;
    }

    public void setDepFlightId(Integer depFlightId) {
        this.depFlightId = depFlightId;
    }

    public Integer getReturnFlightId() {
        return returnFlightId;
    }

    public void setReturnFlightId(Integer returnFlightId) {
        this.returnFlightId = returnFlightId;
    }
}
