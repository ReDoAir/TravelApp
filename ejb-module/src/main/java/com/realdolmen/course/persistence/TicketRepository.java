package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.Ticket;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public class TicketRepository implements Serializable {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Ticket t){
        entityManager.persist(t);
    }

    public Ticket getById(int id){
        return entityManager.find(Ticket.class, id);
    }

    public void update(Ticket t){
        entityManager.merge(t);
    }

    public void delete(int id) {
        entityManager.remove(entityManager.getReference(Ticket.class, id));
    }

    public Ticket refresh(Ticket t){
        entityManager.refresh(t);
        return t;
    }

    public List<Ticket> getAllTickets() {
        return entityManager.createQuery("SELECT t from Ticket t", Ticket.class).getResultList();
    }
}
