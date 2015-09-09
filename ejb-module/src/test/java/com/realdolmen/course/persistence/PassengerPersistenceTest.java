package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Book;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.PersistenceException;

public class PassengerPersistenceTest extends DataSetPersistenceTest {
    @Rule
    public ExpectedException expector = ExpectedException.none();

    @Test
    public void passengerCanBePersisted() throws Exception {
        Passenger passenger = new Passenger("6666","Leo", "Ole",5555);
        entityManager().persist(passenger);
        assertNotNull(passenger.getId());
    }

    @Test(expected = PersistenceException.class)
    public void passengerCanNotBePersistedWithoutSnn() throws Exception {
        Passenger passenger = new Passenger(null,"Leo","Ole",5555);
        entityManager().persist(passenger);
    }

    @Test
    public void passengerCanBeRetrievedById() throws Exception {
        assertEquals("1111", entityManager().find(Passenger.class, 1000).getSsn());
    }
}
