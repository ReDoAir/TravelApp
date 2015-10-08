package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Country;
import com.realdolmen.course.domain.Trip;
import com.realdolmen.course.persistence.CountryRepo;
import com.realdolmen.course.persistence.TripRepo;
import org.omnifaces.util.Faces;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class SearchController implements Serializable{

    private List<Trip> trips = new ArrayList<>();

    @Inject
    private TripRepo tripRepo;
    @Inject
    private CountryRepo countryRepo;
    private List<String> destinations;

    private String countryName;
    private int count;
    private String destination;

    public void tripsToDestination(){
        trips = tripRepo.getTripsByDestinationWithEnoughPlaces(count);
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<String> getDestinations() {
        return tripRepo.getDestinations();
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

    public void search() throws IOException {
        tripsToDestination();
        Faces.redirect("/web-module/app/cust/booktrip.faces");
    }

    public void countryChanged(){
        if(countryName != null && !countryName.equals("")) {
            List<Trip> trips = tripRepo.getTripsByCountry(countryName);
            destinations.addAll(trips.stream().map(Trip::getDestination).collect(Collectors.toList()));
        }
    }
}
