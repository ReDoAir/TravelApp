package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Ticket;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.ToDoubleBiFunction;

/**
 * Created by SSTAX38 on 10/09/2015.
 */
public class TicketTest extends DataSetPersistenceTest {

    @Test
    public void testTicketCanBePersisted(){
        Ticket ticket = new Ticket(10);
        entityManager().persist(ticket);
        assertNotNull(ticket.getId());
    }

    @Test
    public void ticketCanBeReceivedFromDatabase(){
        Ticket t = entityManager().find(Ticket.class,1001);
        assertTrue(1001 == t.getId());
    }

    @Test
    public void flightCanBeAssignedToTicket(){

    }

    @Test
    public void passengerCanBeAssignedToTicket(){

    }
}
