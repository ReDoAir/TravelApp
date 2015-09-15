package com.realdolmen.course.integration;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.persistence.DataSetPersistenceTest;
import com.realdolmen.course.persistence.RemotePaymentEJB;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class PaymentEJBTest extends RemoteIntegrationTest {

    private RemotePaymentEJB remotePaymentEJB;

    @Before
    public void initialize() throws Exception {
        remotePaymentEJB = lookup("ear-module-1.1/ejb-module-1.1/PaymentEJB!com.realdolmen.course.persistence.RemotePaymentEJB");
    }

    @Test
    public void testPaymentByCreditCard() throws ExecutionException, InterruptedException {
        Future<String> result = remotePaymentEJB.payByCreditCard(new Passenger());
        assertEquals("Successful!", result.get());
    }
}
