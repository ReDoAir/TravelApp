package com.realdolmen.course.domain;

import javax.lang.model.element.Name;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name="Trip.findAll",query ="select t from Trip t"),
        @NamedQuery(name="Trip.findAllByToFlight",query = "select t from Trip t where t.toFlight = :toFlight"),
        @NamedQuery(name = "Trip.findAllByFromFlight",query = "select t from Trip t where t.fromFlight = :fromFlight"),
        @NamedQuery(name="Trip.findAllByPeriod", query = "select t from Trip t where t.period = :period"),
        @NamedQuery(name="Trip.findAllByDestination", query = "select t from Trip t where t.destination like :destination")
})
public class Trip implements Serializable{

    public static final String FIND_ALL_TRIPS = "Trip.findAll",
    FIND_ALL_TRIPS_BY_TO_FLIGHTS = "Trip.findAllByToFlight",
    FIND_ALL_TRIPS_BY_FROM_FLIGHTS = "Trip.findAllByFromFlight",
    FIND_ALL_TRIPS_BY_PERIODS = "Trip.findAllByPeriod",
    FIND_ALL_TRIPS_BY_DESTINATION = "Trip.findAllByDestination";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Flight toFlight;

    @ManyToOne
    private Flight fromFlight;

    @ManyToOne
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
