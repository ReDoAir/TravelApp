package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Passenger;
import javax.ejb.*;
import java.util.concurrent.Future;

@Stateless
@LocalBean
@Asynchronous
public class PaymentEJB implements RemotePaymentEJB{

    @Override
    public Future<String> payByCreditCard(Passenger p) {
        return new AsyncResult<>("Successful!");
    }
}
