package com.realdolmen.course.integration;
import com.realdolmen.course.domain.*;
import com.realdolmen.course.persistence.RemotePassengerBean;
import com.realdolmen.course.persistence.RemotePassengerEJB;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class PassengerRepositoryTest extends RemoteIntegrationTest {

    private RemotePassengerBean repository;
    private RemotePassengerEJB remotePassengerEJB;

    @Before
    public void initialize() throws Exception {
        repository = lookup("ear-module-1.1/ejb-module-1.1/PassengerRepository!com.realdolmen.course.persistence.RemotePassengerBean");
        remotePassengerEJB = lookup("ear-module-1.1/ejb-module-1.1/PassengerEJB!com.realdolmen.course.persistence.RemotePassengerEJB");
    }

    @Test
    public void findAllRetrievesAllPassengersRemotely() {
        assertEquals(2, repository.findPassengers().size());
    }

    @Test
    public void findByIdRetrievesPassengerRemotely() {
        assertEquals("Leo", repository.findPassengerById(9999).getFirstName());
    }

    @Test
    public void createPassenger() throws Exception {
        Passenger passenger = new Passenger("7777","Test", "TestAchternaam",new SimpleDateFormat("yyyy-mm-dd").parse(String.valueOf(LocalDate.of(1993, 12, 31))) , PassengerType.OCCASIONAL,5555, new Date(),new ArrayList<String>(),new Address("Irisstraat","","Boom","2850","Belgium"),new CreditCard("0123456789","12/18",123, CreditCardType.VISA));
        repository.create(passenger);
    }

    @Test
    public void updatePassenger(){
        Passenger p = repository.findPassengerById(9999);
        p.setLastName("Test");
        repository.update(p);

        assertEquals("Test", repository.findPassengerById(9999).getLastName());
    }

    @Test
    public void deletePassenger(){
        repository.delete(repository.findPassengerById(9999));
        assertEquals(null, repository.findPassengerById(9999));
    }

    @Test
    public void savePassengers() throws ParseException {
        //Create passenger and add stuff
        remotePassengerEJB.createPassenger("7777","Test", "TestAchternaam",new SimpleDateFormat("yyyy-mm-dd").parse(String.valueOf(LocalDate.of(1993, 12, 31))) , PassengerType.OCCASIONAL,5555);
        remotePassengerEJB.addAddress("Irisstraat", "", "Boom", "2850", "Belgium");
        remotePassengerEJB.addCreditCard("0123456789","12/18",123, CreditCardType.VISA);
        remotePassengerEJB.addTicket(10);
        remotePassengerEJB.checkOut();
    }
}
