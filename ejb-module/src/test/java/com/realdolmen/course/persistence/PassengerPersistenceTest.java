package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Book;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.PersistenceException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class PassengerPersistenceTest extends DataSetPersistenceTest {
    @Rule
    public ExpectedException expector = ExpectedException.none();
//new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(String.valueOf(Instant.now()).replace("T"," "))
    @Test
    public void passengerCanBePersisted() throws Exception {
        Passenger passenger = new Passenger("6666","Leo", "Ole",new SimpleDateFormat("yyyy-mm-dd").parse(String.valueOf(LocalDate.of(1993, 12, 31))) ,PassengerType.OCCASIONAL,5555, new Date());
        entityManager().persist(passenger);
        assertNotNull(passenger.getId());
    }

    @Test(expected = PersistenceException.class)
    public void passengerCanNotBePersistedWithoutSnn() throws Exception {
        Passenger passenger = new Passenger(null,"Leo", "Ole",new SimpleDateFormat("dd-mm-yyyy").parse(String.valueOf(LocalDate.of(1993, 12, 31))) ,PassengerType.OCCASIONAL,5555, new Date());
        entityManager().persist(passenger);
    }

    @Test
    public void passengerCanBeRetrievedById() throws Exception {
        assertEquals("1111", entityManager().find(Passenger.class, 1000).getSsn());
    }
}
