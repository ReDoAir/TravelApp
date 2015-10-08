package com.realdolmen.course.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name="Residence.findAll",query="select r from Residence r"),
        @NamedQuery(name="Residence.findAllByPeriod", query = "select r from Residence r where r.periode = :period")
})
public class Residence implements Serializable{

    public static final String FIND_ALL_RESIDENCE= "Residence.findAll",
    FIND_ALL_RESIDENCE_BY_PERIOD="Residence.findAllByPeriod";


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
