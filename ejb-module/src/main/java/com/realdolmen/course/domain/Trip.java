package com.realdolmen.course.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Trip implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @NotNull
    private Flight toFlight;

    @ManyToOne
    @NotNull
    private Flight fromFlight;

    @ManyToOne
    @NotNull
    private Period period;

    @Basic(optional = false)
    private String destination;

    @ManyToMany
    @JoinTable(
            name="trip_res",
            joinColumns={@JoinColumn(name="trip", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="res", referencedColumnName="id")}
    )
    private List<Residence> residences;

    @Basic(optional = false)
    private String tripName;

    public Trip(Flight toFlight, Flight fromFlight, Period period, String tripName) {
        this.toFlight = toFlight;
        this.fromFlight = fromFlight;
        this.period = period;
        this.tripName = tripName;
        residences = new ArrayList<>();
    }

    public Trip(String tripName, String destination, Period period, Flight fromFlight, Flight toFlight) {
        this.tripName = tripName;
        this.destination = destination;
        this.period = period;
        this.fromFlight = fromFlight;
        this.toFlight = toFlight;
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
        if(toFlight != null && fromFlight != null && fromFlight.getId() != toFlight.getId()) {
            this.toFlight = toFlight;
        }
    }

    public Flight getFromFlight() {
        return fromFlight;
    }

    public List<Residence> getResidences() {
        return residences;
    }

    public void setFromFlight(Flight fromFlight) {
        if(fromFlight != null && toFlight != null && fromFlight.getId() != toFlight.getId()) {
            this.fromFlight = fromFlight;
            destination = fromFlight.getDepartAirport().getCity();
        }
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
// Price of a trip is calculated by flight costs + 5% each + residence costs+ maybe something for our company
    public double getTripPriceForResidence(Residence residence)
    {
        double res = (fromFlight.getPrice()*1.05)+(toFlight.getPrice()*1.05)+(residence.getTotalPrice());
        return res;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
