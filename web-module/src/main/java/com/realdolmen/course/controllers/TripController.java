package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Residence;
import com.realdolmen.course.domain.Trip;
import com.realdolmen.course.persistence.FlightRepo;
import com.realdolmen.course.persistence.PeriodRepo;
import com.realdolmen.course.persistence.ResidenceRepo;
import com.realdolmen.course.persistence.TripRepo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class TripController {

    @Inject
    private TripRepo tripRepo;
    @Inject
    private PeriodRepo periodRepo;
    @Inject
    private FlightRepo flightRepo;
    @Inject
    private ResidenceRepo residenceRepo;

    private String name;
    private Integer periodId;
    private Integer depFlightId;
    private Integer returnFlightId;

    public void createTrip(){
        Trip trip = new Trip();

        trip.setTripName(name);
        trip.setPeriod(periodRepo.getPeriodById(periodId));
        Flight depfight = flightRepo.getFlightById(depFlightId);
        trip.setToFlight(depfight);
        Flight retTrip = flightRepo.getFlightById(returnFlightId);
        trip.setFromFlight(retTrip);
        Residence residence = residenceRepo.getRepoWithPeriodId(periodId);

        if(residence == null){
            residenceRepo.addResidence(new Residence(periodRepo.getPeriodById(periodId),50.));
        }

        trip.addResidence(residence);

        tripRepo.addTrip(trip);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    public Integer getDepFlightId() {
        return depFlightId;
    }

    public void setDepFlightId(Integer depFlightId) {
        this.depFlightId = depFlightId;
    }

    public Integer getReturnFlightId() {
        return returnFlightId;
    }

    public void setReturnFlightId(Integer returnFlightId) {
        this.returnFlightId = returnFlightId;
    }
}
