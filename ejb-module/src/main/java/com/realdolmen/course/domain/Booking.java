package com.realdolmen.course.domain;

import com.realdolmen.course.domain.auth.Customer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries(
        {
                @NamedQuery(name="Booking.findAll",query = "select b from Booking b"),
                @NamedQuery(name="Booking.findByBookingDate",query = "select b from Booking b where b.bookingDate = :bookingDate"),
                @NamedQuery(name="Booking.findByCustomer",query = "select b from Booking b where b.customer = :customer")
        }
)
public class Booking implements Serializable {

    public static final String FIND_ALL_BOOKIGS = "Booking.findAll",
    FIND_ALL_BY_BOOKINGDATE = "Booking.findByBookingDate",
    FIND_BOOKINGS_BY_CUSTOMER = "Booking.findByCustomer";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @NotNull
    private Customer customer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingDate;

    //customer included
    @Basic(optional = false)
    private int count;

    private double price;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name="trip_booking",
            joinColumns={@JoinColumn(name="trip", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="booking", referencedColumnName="id")}
    )
    private List<Trip> trips;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
