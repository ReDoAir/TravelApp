package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.*;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import java.util.Date;

@Remote
public interface RemotePassengerEJB {
    void createPassenger(String ssn, String firstName, String lastName, Date dateOfBirth, PassengerType passengerType, Integer frequentFlyerMiles);
    void addAddress(String street1, String street2, String city, String zipCode, String country);
    void addCreditCard(String number, String expiryDate, Integer controlNumber, CreditCardType creditCardType);
    void addTicket(double price);
    void checkOut();
}
