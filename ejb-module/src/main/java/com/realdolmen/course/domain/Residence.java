package com.realdolmen.course.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
