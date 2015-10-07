package com.realdolmen.course.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SDOAX36 on 5/10/2015.
 */
@Entity
public class Trip implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Flight toFlight;

    @ManyToOne
    private Flight fromFlight;

    @ManyToOne
    private Period period;

    @ManyToMany
    @JoinTable(
            name="trip_res",
            joinColumns={@JoinColumn(name="trip", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="res", referencedColumnName="id")}
    )
    private List<Residence> residences;

    private String tripName;

    public Trip(Flight toFlight, Flight fromFlight, Period periode, String tripName) {
        this.toFlight = toFlight;
        this.fromFlight = fromFlight;
        this.period = periode;
        this.tripName = tripName;
        residences = new ArrayList<>();
    }

    public Trip() {
    }

    public void addResidence(Residence residence)
    {
        if(residences == null)
        {
            residences = new ArrayList<>();
        }
        residences.add(residence);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public Flight getToFlight() {
        return toFlight;
    }

    public void setToFlight(Flight toFlight) {
        this.toFlight = toFlight;
    }

    public Flight getFromFlight() {
        return fromFlight;
    }

    public List<Residence> getResidences() {
        return residences;
    }

    public void setFromFlight(Flight fromFlight) {
        this.fromFlight = fromFlight;
    }

    public Period getPeriode() {
        return period;
    }

    public void setPeriode(Period periode) {
        this.period = periode;
    }
// Price of a trip is calculated by flight costs + 5% each + residence costs+ maybe something for our company
    public double getTripPriceForResidence(Residence residence)
    {
        double res = (fromFlight.getPrice()*1.05)+(toFlight.getPrice()*1.05)+(residence.getTotalPrice());
        return res;
    }
}
