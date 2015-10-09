package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Plane;
import com.realdolmen.course.persistence.PlaneRepo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class PlaneController {

    @Inject
    private PlaneRepo planeRepo;

    public List<Plane> getPlanes(){
        return planeRepo.getAllPlanes();
    }
}