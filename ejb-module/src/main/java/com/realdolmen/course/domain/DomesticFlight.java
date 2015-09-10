package com.realdolmen.course.domain;

import com.realdolmen.course.domain.Flight;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
//@PrimaryKeyJoinColumn(name = "domesticFlighId")
public class DomesticFlight extends Flight {
    private String airlineCompany;

    @ElementCollection
    @CollectionTable(name = "flight_ref")
    @Column(name = "flt_references")
    private List<String> references = new ArrayList<>();

    public DomesticFlight(String number,Date departureDate ,Date arrivalDate ,String airlineCompany, List<String> references) {
        super(number,arrivalDate,departureDate);
        this.airlineCompany = airlineCompany;
        this.references = references;
    }

    public DomesticFlight() {
    }

    public String getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(String airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public List<String> getReferences() {
        return references;
    }

    public void setReferences(ArrayList<String> references) {
        this.references = references;
    }
}
