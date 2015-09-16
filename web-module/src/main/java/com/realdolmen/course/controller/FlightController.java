package com.realdolmen.course.controller;

import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.persistence.FlightRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@Named
@RequestScoped
public class FlightController {

    private List<Flight> flights;

    @Inject
    private FlightRepository flightRepository;

    public List<Flight> getFlights() {
        flights = flightRepository.findAllFlights();
        return flights;
    }
}
