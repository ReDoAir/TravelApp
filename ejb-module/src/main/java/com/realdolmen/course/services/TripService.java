package com.realdolmen.course.services;

import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Residence;
import com.realdolmen.course.domain.Trip;
import com.realdolmen.course.persistence.FlightRepo;
import com.realdolmen.course.persistence.PeriodRepo;
import com.realdolmen.course.persistence.ResidenceRepo;
import com.realdolmen.course.persistence.TripRepo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class TripService implements Serializable {

    @Inject
    private TripRepo tripRepo;
    @Inject
    private PeriodRepo periodRepo;
    @Inject
    private FlightRepo flightRepo;
    @Inject
    private ResidenceRepo residenceRepo;

    public void createTrip(String name, Integer periodId, Integer depFlightId, Integer returnFlightId){
        Trip trip = new Trip();

        trip.setTripName(name);

        trip.setPeriod(periodRepo.getPeriodById(periodId));

        Flight depFight = flightRepo.getFlightById(depFlightId);
        trip.setToFlight(depFight);

        Flight retTrip = flightRepo.getFlightById(returnFlightId);
        trip.setFromFlight(retTrip);

        List<Residence> residences = residenceRepo.getResidencesByPeriod(periodId);
        if(residences == null){
            residences = new ArrayList<>();
            Residence residence = new Residence(periodRepo.getPeriodById(periodId),50.);
            residenceRepo.addResidence(residence);
            residences.add(residence);
        }

        residences.forEach(trip::addResidence);

        calculatePriceForATrip(trip);

        tripRepo.addTrip(trip);
    }

    public List<Trip> getTripsByDestinationWithEnoughPlaces(int count) {
        return tripRepo.getTripsByDestinationWithEnoughPlaces(count);
    }

    public List<Trip> getTripsByPeriodStartDate(Date from, Date to)
    {
        return tripRepo.getTripsByStartDateBetween(from, to);
    }

    public List<Trip>getTripsByPeriodEndDate(Date from, Date to)
    {
        return tripRepo.getTripsByEndDateBetween(from,to);
    }

    public List<String> getDestinations() {
        return tripRepo.getDestinations();
    }

    public List<Trip> getTripsByCountry(String countryName) {
        return tripRepo.getTripsByCountry(countryName);
    }

    public void calculatePriceForATrip(Trip trip){
        if(trip == null) throw new IllegalArgumentException("Trip is not yet defined");

        double tripPrice = 0;

        //Calculate price
        for(Residence r : trip.getResidences()){
            tripPrice +=  (trip.getFromFlight().getPrice()*1.05)+(trip.getToFlight().getPrice()*1.05)+(r.getTotalPrice());
        }

        trip.setPrice(tripPrice);
    }

    public Trip getTripsById(Integer tripId) {
        return tripRepo.getTripsById(tripId);
    }

    public List<Trip> getAllTrips() {
        return tripRepo.getAllTrips();
    }

    public void updateTripPrice(Trip trip, double price) {
        trip.setPrice(price);
        tripRepo.updateTrip(trip);
    }
}
