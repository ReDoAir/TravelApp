package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.Ticket;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Remote
public interface RemotePassengerBean {
    List<Passenger> findPassengers();
    Passenger findPassengerById(int id);
    void create(Passenger p);
    void update(Passenger p);
    void delete(Passenger p);
}