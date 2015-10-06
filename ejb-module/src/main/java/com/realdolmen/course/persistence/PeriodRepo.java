package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Period;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class PeriodRepo {

    @PersistenceContext
    EntityManager em;


    public List<Period> getAllPeriods()
    {
        return em.createQuery("select p from Period p", Period.class).getResultList();
    }

    //still needs more, but the shit is not clear yet

}
