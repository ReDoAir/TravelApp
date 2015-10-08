package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Airline;
import com.realdolmen.course.domain.Booking;
import com.realdolmen.course.domain.auth.Customer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.print.Book;
import java.util.Date;
import java.util.List;

import static com.realdolmen.course.domain.Booking.FIND_ALL_BOOKIGS;
import static com.realdolmen.course.domain.Booking.FIND_ALL_BY_BOOKINGDATE;
import static com.realdolmen.course.domain.Booking.FIND_BOOKINGS_BY_CUSTOMER;

@Stateless
@LocalBean
public class BookingRepo {


    @PersistenceContext
    public
    EntityManager em;

    public List<Booking> findAllBookings()
    {
        return em.createNamedQuery(FIND_ALL_BOOKIGS,Booking.class).getResultList();

    }

    public List<Booking> findAllBookingsByCustomer(Customer customer)
    {
        return em.createNamedQuery(FIND_BOOKINGS_BY_CUSTOMER,Booking.class).setParameter("customer", customer).getResultList();
    }

    public List<Booking> findAllBookingsByBookingDate(Date date)
    {
        return em.createNamedQuery(FIND_ALL_BY_BOOKINGDATE,Booking.class).setParameter("bookingDate",date).getResultList();
    }

    public Booking findBookingById(int id)
    {
        return em.find(Booking.class,id);
    }

    public void addBooking(Booking booking)
    {
        em.persist(booking);
    }


}
