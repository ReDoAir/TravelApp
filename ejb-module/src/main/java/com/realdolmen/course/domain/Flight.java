package com.realdolmen.course.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@NamedQueries({
        @NamedQuery(name="Flight.findAll",query = "select f from Flight f"),
        @NamedQuery(name="Flight.findAllByFlightCode",query = "select f from Flight f where f.flightCode like :flightCode"),
        @NamedQuery(name="Flight.findAllByDepartureDate",query="select f from Flight f where f.departureDate between :startDate and :endDate"),
        @NamedQuery(name="Flight.findAllByArrivalDate",query = "select f from Flight f where f.arrivalDate between :startDate and :endDate"),
        @NamedQuery(name="Flight.findAllByDestinationAndDepartureDateBetween",query = "select f from Flight f where f.departAirport = :airport and f.departureDate between :startDate and :endDate"),
        @NamedQuery(name="Flight.findAllByArrivalAirport",query = "select f from Flight f where f.arrivalAirport = :arrivalAirport"),
        @NamedQuery(name="Flight.findAllByDepartureAirport",query = "select f from Flight f where f.departAirport = :departAirport"),
        @NamedQuery(name="Flight.findAllByDepartureDateWithAvailableSeats",query ="select f from Flight f where f.departureDate between :startDate and :endDate and f.availablePlaces > 0" ),
        @NamedQuery(name="Flight.findAllByDepartAirportWithAvailableSeats",query ="select f from Flight f where f.departAirport = :departureDate and f.availablePlaces > 0" ),
        @NamedQuery(name="Flight.findAllByAirline",query = "select f from Flight f where f.airline = :airline")
        //more of this please, but we will handle them when they occur
})
public class Flight implements Serializable{

    public static final String FIND_ALL_FLIGHTS ="Flight.findAll",
    FIND_ALL_FLIGHTS_BY_FLIGHT_CODE = "Flight.findAllByFlightCode",
    FIND_ALL_FLIGHTS_BY_DEPARTUREDATE= "Flight.findAllByDepartureDate",
    FIND_ALL_FLIGHTS_BY_ARRIVALDATE="Flight.findAllByArrivalDate",
    FIND_ALL_FLIGHTS_BY_DEPARTURE_AIRPORT="Flight.findAllByDepartureAirport",
    FIND_ALL_FLIGHTS_BY_ARRIVAL_AIRPORT ="Flight.findAllByArrivalAirport",
    FIND_ALL_FLIGHTS_BY_DEPARTUREDATE_WITH_AVAILABLESEATS= "Flight.findAllByDepartureDateWithAvailableSeats",
    FIND_ALL_FLIGHTS_BY_DESTINATION_AND_ARRIVALDATE_BETWEEN ="Flight.findAllByDestinationAndArivalDateBetween",
    FIND_ALL_FLIGHTS_BY_DEPARTAIRPORT_WITH_AVIALABLESEATS="Flight.findAllByDepartAirportWithAvailableSeats",
    FIND_ALL_FLIGHTS_BY_AIRLINE="Flight.findAllByAirline";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String flightCode;

    private Double price;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalDate;

    private int availablePlaces;

    @ManyToOne
    private Airport arrivalAirport;

    @ManyToOne
    private Airline airline;

    @ManyToOne
    private Airport departAirport;


    //A plane can fly a flight, but it will not disappear when the flight is over, probably it will disappear during the flight => set boolean LOST on true
    //But this is not LOST nor the Malysian airlines => we have banned that airline company!!!! so a plane can be re-used later on! that is why this is ManyToOne
    @ManyToOne
    private Plane plane;


    public Flight(String flightCode, Date departureDate, Date arrivalDate,Double price,Airport arrivalAirport, Airport departAirport, Airline airline) {
        this.flightCode = flightCode;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.price = price;
        this.departAirport = departAirport;
        this.arrivalAirport = arrivalAirport;
        this.airline = airline;
    }

    public Flight() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Double getPrice() {
        return price;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
        setAvailablePlaces(plane.getNumberOfSeats());
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        if(!arrivalAirport.equals(departAirport)) {
            this.arrivalAirport = arrivalAirport;
        }else{
            throw new DepartAndArrivalAreTheSameException("");
        }
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Airport getDepartAirport() {
        return departAirport;
    }

    public void setDepartAirport(Airport departAirport) {
        this.departAirport = departAirport;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }
}
