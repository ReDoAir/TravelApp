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

    public void create(Passenger p){
        entityManager.persist(p);
    }

    public Passenger getById(int id){
        return entityManager.find(Passenger.class, id);
    }

    public void update(Passenger p){
        entityManager.merge(p);
    }

    public void delete(int id) {
        entityManager.remove(entityManager.getReference(Passenger.class, id));
    }

    public Passenger refresh(Passenger p){
        entityManager.refresh(p);
        return p;
    }
}
