package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PassengerRepositoryTest extends DataSetPersistenceTest{

    private PassengerRepository repository;

    @Before
    public void initializeRepository() {
        repository = new PassengerRepository();
        repository.entityManager = entityManager();
    }

    @Test
    public void testTicketsByPassId() throws Exception {
        List<Ticket> tickets = repository.findTicketsByPassId(1000);
        Passenger p = entityManager().find(Passenger.class,1000);
        assertTrue(p.getTickets().size() == tickets.size());
    }
}
