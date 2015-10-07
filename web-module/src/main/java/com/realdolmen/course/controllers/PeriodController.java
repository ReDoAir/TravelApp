package com.realdolmen.course.controllers;

import com.realdolmen.course.domain.Period;
import com.realdolmen.course.persistence.PeriodRepo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class PeriodController {

    @Inject
    private PeriodRepo periodRepo;

    public List<Period> getPeriods(){
        return periodRepo.getAllPeriods();
    }
}
