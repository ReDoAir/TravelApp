package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Period;
import com.realdolmen.course.services.DateServices;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

import static com.realdolmen.course.domain.Period.FIND_ALL_PERIODS;
import static com.realdolmen.course.domain.Period.FIND_ALL_PERIODS_FROM_ARRIVAL_DATE;
import static com.realdolmen.course.domain.Period.FIND_ALL_PERIODS_FROM_DEPATUREDATE;

@Stateless
@LocalBean
public class PeriodRepo {

/*
    public static final String FIND_ALL_PERIODS = "Period.findAll",
            FIND_ALL_PERIODS_FROM_DEPATUREDATE=  "Period.findAllFromDepartureDate",
            FIND_ALL_PERIODS_FROM_ARRIVAL_DATE= "Period.findAllFromArrivalDate";
    */
    @PersistenceContext
    public EntityManager em;


    public List<Period> getAllPeriods()
    {
        return em.createNamedQuery(FIND_ALL_PERIODS,Period.class).getResultList();
    }

    public List<Period>getAllPeriodsFromDepartureDate(Date date)
    {
        Date endDate = DateServices.addAFewDays(date,1);
        return em.createNamedQuery(FIND_ALL_PERIODS_FROM_DEPATUREDATE,Period.class).setParameter("startDate",date).setParameter("endDate", endDate).getResultList();
    }

    public List<Period>getAllPeriodsFromArrivalDate(Date date)
    {
        Date endDate = DateServices.addAFewDays(date,1);
        return em.createNamedQuery(FIND_ALL_PERIODS_FROM_ARRIVAL_DATE,Period.class).setParameter("startDate",date).setParameter("endDate",endDate).getResultList();
    }

    public Period findPeriodById(int id)
    {
        return em.find(Period.class, id);
    }
    public void deletePeriod(Period period)
    {
        em.remove(period);
    }
    public void addPeriode(Period period)
    {
        em.persist(period);
    }

    //still needs more, but the shit is not clear yet

}
