package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Book;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by SSTAX38 on 9/09/2015.
 */
public class PassengerRepositoryTest extends DataSetPersistenceTest{
    private PassengerRepository repository;

    @Before
    public void initializeRepository() {
        repository = new PassengerRepository();
        repository.entityManager = entityManager();
    }

    @Test
    public void allPassengersCanBeRetrieved() throws Exception {
        assertEquals(3, repository.findAll().size());
    }

    @Test
    public void passengerCanBeRemovedById() throws Exception {
        repository.remove(1000);
        assertNull(entityManager().find(Passenger.class, 1000));
    }
}
