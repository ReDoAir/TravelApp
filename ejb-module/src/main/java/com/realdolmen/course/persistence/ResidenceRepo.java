package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Residence;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class ResidenceRepo {

    @PersistenceContext
    EntityManager em;

    public List<Residence> getAllResidence()
    {
        return em.createQuery("select r from Residence r", Residence.class).getResultList();
    }

    public void addResidence(Residence r)
    {
        em.persist(r);
        em.flush();
    }

    public Residence getRepoWithPeriodId(Integer periodId) {
        return em.createQuery("SELECT r FROM Residence r WHERE r.period.id = :periodId", Residence.class).setParameter("periodId", periodId).getSingleResult();
    }

    //More implementation is needed to complete these tasks

}
