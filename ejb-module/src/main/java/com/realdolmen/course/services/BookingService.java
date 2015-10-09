package com.realdolmen.course.services;

import com.realdolmen.course.domain.Booking;
import com.realdolmen.course.domain.Trip;
import com.realdolmen.course.persistence.BookingRepo;
import com.realdolmen.course.persistence.CustomerRepo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Stateless
public class BookingService implements Serializable{

    @Inject
    private BookingRepo bookingRepo;
    @Inject
    private CustomerRepo customerRepo;

    public void createBooking(int count, String userName, List<Trip> addedTrips, double totalPrice){
        Booking booking = new Booking();
        booking.setCount(count);
        booking.setCustomer(customerRepo.findCustomerByName(userName));
        booking.setTrips(addedTrips);
        booking.setBookingDate(Date.from(Instant.now()));
        booking.setPrice(totalPrice);
        bookingRepo.addBooking(booking);
    }
}
