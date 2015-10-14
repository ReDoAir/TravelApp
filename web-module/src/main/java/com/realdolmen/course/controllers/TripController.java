package com.realdolmen.course.controllers;

import com.realdolmen.course.services.TripService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class TripController {

    @Inject
    private TripService tripService;
    private String name;
    private Integer periodId;
    private Integer depFlightId;
    private Integer returnFlightId;


    public void createTrip(){
        tripService.createTrip(name, periodId, depFlightId, returnFlightId);
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
