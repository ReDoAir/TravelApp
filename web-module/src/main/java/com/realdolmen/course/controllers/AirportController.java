package com.realdolmen.course.controllers;


import com.realdolmen.course.domain.Airport;
import com.realdolmen.course.persistence.AirportRepo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AirportController {

    @Inject
    private AirportRepo airportRepo;

    public List<Airport> getAirports(){
        return airportRepo.getAllAirports();
    }
}

/*
*
    public List<String> completeText(String query) {
        List<String> results = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }

        return results;
    }

*/