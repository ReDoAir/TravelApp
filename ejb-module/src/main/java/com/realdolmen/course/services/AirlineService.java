package com.realdolmen.course.services;

import com.realdolmen.course.domain.Airline;
import com.realdolmen.course.persistence.AirlineRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SDOAX36 on 12/10/2015.
 */
@Stateless
public class AirlineService implements Serializable{

    @Inject
    private AirlineRepository repo;


    public List<String> getAllAirlineNames()
    {
        List<String> strings = new ArrayList<>();
        List<Airline> airlines = repo.findAllAirlines();

        for(Airline airline : airlines)
        {
            strings.add(airline.getName());
        }
        return strings;
    }
}
