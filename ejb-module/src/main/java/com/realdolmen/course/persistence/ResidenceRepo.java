package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Residence;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by SDOAX36 on 5/10/2015.
 */

@Stateless
@LocalBean
public class ResidenceRepo {

    @Inject
    EntityManager em;

    public List<Residence> getAllResidence()
    {
        return em.createQuery("select r from Residence r").getResultList();
    }

    public void addResidence(Residence r)
    {
        em.persist(r);
        em.flush();
    }

    //More implementation is needed to complete these tasks

}
