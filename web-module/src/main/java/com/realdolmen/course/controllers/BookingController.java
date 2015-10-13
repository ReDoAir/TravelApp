package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Booking;
import com.realdolmen.course.domain.Trip;
import com.realdolmen.course.domain.payment.CreditCard;
import com.realdolmen.course.services.BookingService;
import com.realdolmen.course.services.CustomerService;
import com.realdolmen.course.services.TripService;
import org.apache.shiro.SecurityUtils;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
    @Inject
    private CustomerService customerService;

    private List<Trip> addedTrips;
    private double totalPrice = 0;
    private int count;
    private CreditCard creditCard;
    //4388576018410707 -> example ccn
    private String number;
    private Date expire;

    @PostConstruct
    public void init(){
        addedTrips = new ArrayList<>();
        count = searchController.getCount();
    }

    public String createBooking() {
        if (addedTrips.size() > 0) {
            if(creditCard != null) {
                String userName = (String) SecurityUtils.getSubject().getSession().getAttribute("userName");
                customerService.addCreditCard(userName,creditCard);
                int result = bookingService.createBooking(count, userName, addedTrips, totalPrice);
                    if (result == -1) {
                        Messages.addGlobalError("Number of passengers cannot be 0");
                    }else if(result == -2){
                        Messages.addGlobalError("Trip is not available anymore");
                    } else{
                        return "thankyou";
                    }
            }else {
                Messages.addGlobalError("You need to add a credit card");
            }
        } else {
            Messages.addGlobalError("You did not select any trips yet");
        }

        return "booktrip";
    }

    public void addTrip(Integer tripId){

        Trip trip = tripService.getTripsById(tripId);

        if(alreadyAdded(trip)) {
            Messages.addGlobalError("You have already added this trip");
        }else {
            addedTrips.add(trip);
            totalPrice += (trip.getPrice() * count);
            System.out.println(addedTrips.get(0).getPrice());
        }

    }

    public void delete(Integer tripId){
        if(tripId != null){
            for(int i = 0; i < addedTrips.size(); i++){
                if(Objects.equals(addedTrips.get(i).getId(), tripId)){
                    totalPrice -= (addedTrips.get(i).getPrice() * count);
                    addedTrips.remove(i);
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

    public List<Booking> getBookings() {
        String userName = (String) SecurityUtils.getSubject().getSession().getAttribute("userName");
        return bookingService.getAllBookingByUser(userName);
    }

    public void deleteBooking(Booking booking) {
        bookingService.deleteBooking(booking);
    }

    public void validateAndCreateCreditCard(){
        if(number != null && !number.equals("") && expire != null){
            if(isValid(number) && expire.after(Date.from(Instant.now()))) {
                creditCard = new CreditCard(number,expire);
                Messages.addInfo("payment", "Credit card is valid");
            }else{
                Messages.addError("payment", "Credit card is invalid");
            }
        }else {
            Messages.addError("payment", "Please fill in your credit card number and expiry date");
        }

    }

    private boolean isValid(String number) {

        int sum = 0;
        boolean alternate = false;

        for (int i = number.length() - 1; i >= 0; i--)
        {
            int n = Integer.parseInt(number.substring(i, i + 1));
            if (alternate)
            {
                n *= 2;
                if (n > 9)
                {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }

        return (sum % 10 == 0);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }
}
