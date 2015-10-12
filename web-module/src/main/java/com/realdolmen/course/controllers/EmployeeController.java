package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Trip;
import com.realdolmen.course.services.AirlineService;
import com.realdolmen.course.services.TripService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class EmployeeController {

    @Inject
    private TripService tripService;

    @Inject
    private AirlineService airlineService;

    private Date fromStart;
    private Date toStart;

    private Date fromEnd;
    private Date toEnd;

    private double price;

    private List<Trip>filteredTrips;

    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }


    public void changePrice(Trip trip) {
        tripService.updateTripPrice(trip, price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getAllAirlineNames()
    {
        return airlineService.getAllAirlineNames();
    }

    public List<Trip> getFilteredTrips() {
        return filteredTrips;
    }

    public void setFilteredTrips(List<Trip> filteredTrips) {
        this.filteredTrips = filteredTrips;
    }

    public void filterByBeginTripDate()
    {
        if(fromStart != null && toStart != null)
        {
            filteredTrips = tripService.getTripsByPeriodStartDate(fromStart,toStart);
        }
    }
    public void filteredByEndTripDate()
    {
        if(fromEnd != null && toEnd != null)
        {
            filteredTrips = tripService.getTripsByPeriodEndDate(fromEnd,toEnd);
        }
    }

    public Date getFromStart() {
        return fromStart;
    }

    public void setFromStart(Date fromStart) {
        this.fromStart = fromStart;
    }

    public Date getToStart() {
        return toStart;
    }

    public void setToStart(Date toStart) {
        this.toStart = toStart;
    }

    public Date getFromEnd() {
        return fromEnd;
    }

    public void setFromEnd(Date fromEnd) {
        this.fromEnd = fromEnd;
    }

    public Date getToEnd() {
        return toEnd;
    }

    public void setToEnd(Date toEnd) {
        this.toEnd = toEnd;
    }
}
