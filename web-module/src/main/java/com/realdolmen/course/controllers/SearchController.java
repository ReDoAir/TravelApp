package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Trip;
import com.realdolmen.course.persistence.CountryRepo;
import com.realdolmen.course.services.TripService;
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
    private List<Trip> trips = new ArrayList<>();

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
        tripsToDestination();
        Faces.redirect("/web-module/app/cust/booktrip.faces");
    }

}


/*
    private List<String> destinations;
    private String countryName;

    public List<String> getCountries(){

        List<Country> countries = countryRepo.getAllCountriesWithTrips();
        List<String> names = new ArrayList<>();
        Collections.sort(countries);

        names.addAll(countries.stream().map(Country::getName).collect(Collectors.toList()));

        return names;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    public void countryChanged(){
        if(countryName != null && !countryName.equals("")) {
            List<Trip> trips = tripService.getTripsByCountry(countryName);
            destinations.addAll(trips.stream().map(Trip::getDestination).collect(Collectors.toList()));
        }
    }
* */