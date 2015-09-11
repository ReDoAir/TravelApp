package com.realdolmen.course.persistence;
import com.realdolmen.course.domain.Ticket;
import com.sun.xml.internal.messaging.saaj.util.TeeInputStream;
import org.junit.Before;
import org.junit.Test;

public class TicketRepositoryTest extends DataSetPersistenceTest {

    private TicketRepository repository;

    private Ticket findTicket(int id){
       return entityManager().find(Ticket.class,id);
    }

    @Before
    public void initializeRepository() {
        repository = new TicketRepository();
        repository.entityManager = entityManager();
    }

    @Test
    public void testCreateTicket(){
        Ticket t = new Ticket(10);
        repository.create(t);
        assertNotNull(findTicket(1001));
    }

    @Test
    public void testRetrieveById(){
        assertNotNull(repository.getById(1001));
    }

    @Test
    public void testUpdateTicket(){
        Ticket t = findTicket(1001);
        t.setPrice(15);
        repository.update(t);
        assertTrue(findTicket(1001).getPrice() == 15);
    }

    @Test
    public void testDeleteTicket(){
        repository.delete(1001);
        assertNull(entityManager().find(Ticket.class, 1001));
    }

    @Test
    public void testRefreshTicket(){
        Ticket t = findTicket(1001);
        t.setPrice(15);
        t = repository.refresh(t);
        assertTrue(t.getPrice() != 15);
    }


}
