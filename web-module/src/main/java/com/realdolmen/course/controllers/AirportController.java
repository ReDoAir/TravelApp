package com.realdolmen.course.controllers;


import com.realdolmen.course.domain.Airport;
import com.realdolmen.course.persistence.AirportRepo;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AirportController {

    @EJB
    private AirportRepo airportRepo;

    public List<Airport> getAirports(){
        return airportRepo.getAllAirports();
    }
}
