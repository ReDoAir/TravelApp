package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Passenger;

import javax.ejb.Remote;
import java.util.concurrent.Future;

@Remote
public interface RemotePaymentEJB {
    Future<String> payByCreditCard(Passenger passenger);
}
