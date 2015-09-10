package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Passenger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class PassengerRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Passenger> findAll(){
        return entityManager.createQuery("select p from Passenger p",Passenger.class).getResultList();
    }

    public void remove(int id) {
        entityManager.remove(entityManager.getReference(Passenger.class, id));
    }
}
