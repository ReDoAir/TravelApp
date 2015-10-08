package com.realdolmen.course.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQueries(
        {
                @NamedQuery(name="Period.findAll",query = "select p from Period p"),
                @NamedQuery(name="Period.findAllFromDepartureDate",query = "select p from Period p where p.departureDate between :startDate and :endDate"),
                @NamedQuery(name="Period.findAllFromArrivalDate", query = "select p from Period p where p.returnDate between :startDate and :endDate")
        }
)
public class Period implements Serializable
{

    public static final String FIND_ALL_PERIODS = "Period.findAll",
    FIND_ALL_PERIODS_FROM_DEPATUREDATE=  "Period.findAllFromDepartureDate",
    FIND_ALL_PERIODS_FROM_ARRIVAL_DATE= "Period.findAllFromArrivalDate";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;

    public Period(Date departureDate, Date returnDate) {
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }

    public Period() {
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
