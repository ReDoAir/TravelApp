package com.realdolmen.course.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Passenger implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    @Column(updatable = false)
    private String ssn;
    @Column(length = 55)
    private String firstName;
    @Column(length = 55)
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Transient
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    private PassengerType passengerType;

    private Integer frequentFlyerMiles;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastFlight;

    @Lob
    private byte[] picture;

    @ElementCollection
    @CollectionTable(name = "passenger_pref")
    @Column(name = "preferences")
    private List<String> preferences = new ArrayList<>();

    @Embedded
    private Address address;

    @Embedded
    private CreditCard creditCard;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "passenger")
    private List<Ticket> tickets = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    public Passenger(String ssn, String firstName, String lastName, Date dateOfBirth, PassengerType passengerType, Integer frequentFlyerMiles, Date lastFlight, List<String> preferences, Address address, CreditCard creditCard) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        setDateOfBirth(dateOfBirth);
        this.passengerType = passengerType;
        this.frequentFlyerMiles = frequentFlyerMiles;
        this.lastFlight = lastFlight;
        this.preferences = preferences;
        this.address = address;
        this.creditCard = creditCard;
    }

    public Passenger(String ssn, String firstName, String lastName, Date dateOfBirth, PassengerType passengerType, Integer frequentFlyerMiles) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.passengerType = passengerType;
        this.frequentFlyerMiles = frequentFlyerMiles;
    }

    public Passenger() {
    }

    @PostLoad
    @PostUpdate
    @PostPersist
    private void calculateAge(){
        /*if(dateOfBirth != null){
            LocalDate now = LocalDate.now();
            LocalDate birth = ((java.sql.Date)dateOfBirth).toLocalDate();
            age = Period.between(now,birth).getYears();
        }*/
    }

    @PrePersist
    @PreUpdate
    public void changeLastUpdated(){
        lastUpdated = Date.from(Instant.now());
    }

    public Integer getId() {
        return id;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(PassengerType passengerType) {
        this.passengerType = passengerType;
    }

    public Date getLastFlight() {
        return lastFlight;
    }

    public void setLastFlight(Date lastFlight) {
        this.lastFlight = lastFlight;
    }

    public Integer getFrequentFlyerMiles() {
        return frequentFlyerMiles;
    }

    public void setFrequentFlyerMiles(Integer frequentFlyerMiles) {
        this.frequentFlyerMiles = frequentFlyerMiles;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void addTicket(Ticket ticket){
        if(ticket != null)
        tickets.add(ticket);
    }
}
