package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Booking;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by SDOAX36 on 5/10/2015.
 */
@Stateless
@LocalBean
public class BookingRepo {


    @Inject
    EntityManager em;

    public List<Booking> findAllBookings()
    {
        return em.createQuery("select b from Booking b").getResultList();
    }

    public void addBooking(Booking booking)
    {
        em.persist(booking);
    }


}
