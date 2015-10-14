package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Trip;
import com.realdolmen.course.persistence.CountryRepo;
import com.realdolmen.course.services.TripService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.omnifaces.util.Faces;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class SearchController implements Serializable{

    private int count;
    private String destination;
    private String country;
    private List<Trip> trips = new ArrayList<>();

    private Trip trip;

    @Inject
    private TripService tripService;
    @Inject
    private CountryRepo countryRepo;

    public void tripsToDestination(){
        trips = tripService.getTripsByDestinationWithEnoughPlaces(count);
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<String> getDestinations() {
        return tripService.getDestinations();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void search() throws IOException {
        Subject currentUser = SecurityUtils.getSubject();
        tripsToDestination();

        if(currentUser.hasRole("CUSTOMER")) {
            Faces.redirect("/web-module/app/cust/booktrip.faces");
        }else{
            Faces.redirect("/web-module/app/anon/trips.faces");
        }

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> destinations(){
        return countryRepo.getDestinationsByCountry(country);
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

}
