package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Booking;
import com.realdolmen.course.domain.Trip;
import com.realdolmen.course.persistence.BookingRepo;
import com.realdolmen.course.persistence.CustomerRepo;
import com.realdolmen.course.persistence.TripRepo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class BookingController {

    @Inject
    private BookingRepo bookingRepo;
    @Inject
    private CustomerRepo customerRepo;
    @Inject
    private TripRepo tripRepo;

    private Booking booking;

    private List<Trip> addedTrips;

    @Inject
    private SearchController searchController;

    @PostConstruct
    public void init(){
        addedTrips = new ArrayList<>();
    }

    public void createBooking(String customerName) {
        Booking booking = new Booking();
        booking.setCount(searchController.getCount() + 1);
        booking.setCustomer(customerRepo.findCustomerByName(customerName));
        booking.setTrips(addedTrips);
        booking.setBookingDate(Date.from(Instant.now()));
        bookingRepo.addBooking(booking);
    }

    public void addTrip(Integer tripId){
        addedTrips.add(tripRepo.getTripsById(tripId));
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public List<Trip> getAddedTrips() {
        return addedTrips;
    }

    public void setAddedTrips(List<Trip> addedTrips) {
        this.addedTrips = addedTrips;
    }
}
