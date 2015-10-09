package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Trip;
import com.realdolmen.course.services.BookingService;
import com.realdolmen.course.services.TripService;
import org.apache.shiro.SecurityUtils;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Named
@SessionScoped
public class BookingController implements Serializable {
    @Inject
    private BookingService bookingService;
    @Inject
    private TripService tripService;
    @Inject
    private SearchController searchController;

    private List<Trip> addedTrips;
    private double totalPrice = 0;
    private int count;

    @PostConstruct
    public void init(){
        addedTrips = new ArrayList<>();
        count = searchController.getCount();
    }

    public void createBooking() {
        if(addedTrips.size() > 1) {
            String userName = (String) SecurityUtils.getSubject().getSession().getAttribute("userName");
            bookingService.createBooking(count, userName, addedTrips, totalPrice);
        }else {
            Messages.addGlobalError( "You did not select any trips yet");
        }
    }

    public void addTrip(Integer tripId){

        Trip trip = tripService.getTripsById(tripId);

        if(alreadyAdded(trip)) {
            Messages.addGlobalError("You have already added this trip");
        }else {
            addedTrips.add(trip);
            totalPrice += (trip.getPrice() * count);
        }

    }

    public void delete(Integer tripId){
        if(tripId != null){
            for(int i = 0; i < addedTrips.size(); i++){
                if(Objects.equals(addedTrips.get(i).getId(), tripId)){
                    addedTrips.remove(i);
                    totalPrice -= (addedTrips.get(i).getPrice() * count);
                }
            }
        }
    }

    public List<Trip> getAddedTrips() {
        return addedTrips;
    }

    public void setAddedTrips(List<Trip> addedTrips) {
        this.addedTrips = addedTrips;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean alreadyAdded(Trip trip){

        for(Trip t : addedTrips){
            if(t.getId().equals(trip.getId())){
                return true;
            }
        }

        return false;
    }
}
