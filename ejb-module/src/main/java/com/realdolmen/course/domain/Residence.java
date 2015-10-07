package com.realdolmen.course.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Residence implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Period periode;

    @NotNull
    private Double priceByDay;

    public Residence(Period periode, Double priceByDay) {
        this.periode = periode;
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
        int aantalDagen = periode.getDepartureDate().getDate() - periode.getReturnDate().getDate();
        return aantalDagen*priceByDay;
    }
}
