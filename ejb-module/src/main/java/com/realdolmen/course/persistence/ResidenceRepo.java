package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Period;
import com.realdolmen.course.domain.Residence;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.realdolmen.course.domain.Residence.FIND_ALL_RESIDENCE;
import static com.realdolmen.course.domain.Residence.FIND_ALL_RESIDENCE_BY_PERIOD;

@Stateless
@LocalBean
public class ResidenceRepo {

    @PersistenceContext
    EntityManager em;


    /*
        public static final String FIND_ALL_RESIDENCE= "Residence.findAll",
    FIND_ALL_RESIDENCE_BY_PERIOD="Residence.findAllByPeriod";

     */
    public List<Residence> getAllResidence()
    {
        return em.createNamedQuery(FIND_ALL_RESIDENCE, Residence.class).getResultList();
    }

    public List<Residence>getAllResidencesByPeriod(Period period)
    {
        return em.createNamedQuery(FIND_ALL_RESIDENCE_BY_PERIOD,Residence.class).getResultList();
    }

    public void addResidence(Residence r)
    {
        em.persist(r);
        em.flush();
    }

    //More implementation is needed to complete these tasks

}
