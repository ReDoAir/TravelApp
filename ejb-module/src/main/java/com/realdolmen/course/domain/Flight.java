package com.realdolmen.course.domain;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Flight {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "flight")
    private List<Ticket> tickets;

    public Flight(String number, Date departureTime, Date arrivalTime) {
        this.number = number;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Flight() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getFormattedDepTime(){
        return new SimpleDateFormat("dd-MM-yyyy hh:mm").format(departureTime);
    }

    public String getFormattedArrTime(){
        return new SimpleDateFormat("dd-MM-yyyy hh:mm").format(arrivalTime);
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

}
