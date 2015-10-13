package com.realdolmen.course.services;

import com.realdolmen.course.domain.Booking;
import com.realdolmen.course.domain.Trip;
import com.realdolmen.course.persistence.BookingRepo;
import com.realdolmen.course.persistence.CustomerRepo;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Stateless
public class BookingService implements Serializable{

    private static final Logger logger = Logger.getLogger(BookingService.class);

    @Inject
    private BookingRepo bookingRepo;
    @Inject
    private CustomerRepo customerRepo;
    @Inject
    private TripService tripService;

    public int createBooking(int count, String userName, List<Trip> addedTrips, double totalPrice) {
        if (count > 0) {
            Booking booking = new Booking();
            booking.setCount(count);
            booking.setCustomer(customerRepo.findCustomerByName(userName));
            booking.setTrips(addedTrips);
            booking.setBookingDate(Date.from(Instant.now()));
            booking.setPrice(totalPrice);

            for(Trip t : addedTrips){
                try {
                    tripService.updateAvailableSpotsTrip(t, count);
                }catch(IllegalArgumentException e){
                    logger.warn(e.getMessage());
                    return -2;
                }
            }

            bookingRepo.addBooking(booking);

            return 0;
        } else {
            return -1;
        }
    }

    public List<Booking> getAllBookingByUser(String userName) {
        return bookingRepo.getAllBookingByUser(userName);
    }

    public void deleteBooking(Booking booking) {
        bookingRepo.deleteBooking(booking);
    }
}
