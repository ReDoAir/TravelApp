package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Booking;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class BookingRepo {


    @PersistenceContext
    EntityManager em;

    public List<Booking> findAllBookings()
    {
        return em.createQuery("select b from Booking b",Booking.class).getResultList();
    }

    public void addBooking(Booking booking)
    {
        em.persist(booking);
    }


}
