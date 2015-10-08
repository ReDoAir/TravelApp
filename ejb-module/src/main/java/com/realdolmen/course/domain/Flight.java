package com.realdolmen.course.domain;

import com.realdolmen.course.domain.exceptions.ArrivalCannotBeBeforeDepartException;
import com.realdolmen.course.domain.exceptions.DepartAndArrivalAreTheSameException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Entity
public class Flight implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String flightCode;

    @Basic(optional = false)
    private Double price;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date departureDate;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date arrivalDate;

    @Basic(optional = false)
    private int availablePlaces;

    @ManyToOne
    @NotNull
    private Airport arrivalAirport;

    @ManyToOne
    @NotNull
    private Airline airline;

    @ManyToOne
    @NotNull
    private Airport departAirport;

    @ManyToOne
    @NotNull
    //A plane can fly a flight, but it will not disappear when the flight is over, probably it will disappear during the flight => set boolean LOST on true
    //But this is not LOST nor the Malysian airlines => we have banned that airline company!!!! so a plane can be re-used later on! that is why this is ManyToOne
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
        if(departureDate != null){
            if (departureDate.after(arrivalDate))
            {
                throw new ArrivalCannotBeBeforeDepartException();
            }

            this.arrivalDate = arrivalDate;
        }
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    @Override
    public String toString() {
        return String.format("Depart: %s - %s - Arrival: %s - %s",departureDate,departAirport.getCity(),arrivalDate, arrivalAirport.getCity());
    }
}
