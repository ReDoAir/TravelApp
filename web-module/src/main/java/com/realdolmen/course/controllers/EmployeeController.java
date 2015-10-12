package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Trip;
import com.realdolmen.course.services.TripService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class EmployeeController {

    @Inject
    private TripService tripService;
    private double price;

    public List<Trip> getAllTrips(){
        return tripService.getAllTrips();
    }

    public void changePrice(Trip trip){
        tripService.updateTripPrice(trip, price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
