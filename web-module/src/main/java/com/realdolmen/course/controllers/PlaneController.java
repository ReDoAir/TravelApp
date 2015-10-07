package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Airport;
import com.realdolmen.course.domain.Plane;
import com.realdolmen.course.persistence.AirportRepo;
import com.realdolmen.course.persistence.PlaneRepo;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class PlaneController {

    @EJB
    private PlaneRepo planeRepo;

    public List<Plane> getPlanes(){
        List<Plane> planes =  planeRepo.getAllPlanes();
        return planes;
    }
}