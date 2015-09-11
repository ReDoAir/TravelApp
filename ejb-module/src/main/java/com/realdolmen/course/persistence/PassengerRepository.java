package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.Ticket;

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

    //QUERIES

    public List<Passenger> findAllPassengers(){
        return entityManager.createQuery("SELECT p FROM Passenger p",Passenger.class).getResultList();
    }

    public List<String> findAllLastNames(){
        return entityManager.createQuery("SELECT p.lastName FROM Passenger p", String.class).getResultList();
    }

    public int totalFrequentFlyerMiles(){
        return entityManager.createQuery("SELECT SUM(p.frequentFlyerMiles) FROM Passenger p",Integer.class).getSingleResult();
    }

    public List<Ticket> findTicketsByPassId(int id){
        return entityManager.createQuery("SELECT t FROM Ticket t WHERE t.passenger.id = :passId",Ticket.class).setParameter("passId",id).getResultList();
    }

    public void deleteAll(){
        entityManager.createQuery("DELETE FROM Passenger p");
    }
}
