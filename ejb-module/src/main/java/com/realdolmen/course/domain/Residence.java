package com.realdolmen.course.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Residence implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Period period;

    @Basic(optional = false)
    private Double priceByDay;

    public Residence(Period period, Double priceByDay) {
        this.period = period;
        this.priceByDay = priceByDay;
    }

    public Residence() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getTotalPrice()
    {
        int aantalDagen = period.getDepartureDate().getDate() - period.getReturnDate().getDate();
        return aantalDagen*priceByDay;
    }
}
