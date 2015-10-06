package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Periode;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class PeriodeRepo {

    @PersistenceContext
    EntityManager em;


    public List<Periode> getAllPeriodes()
    {
        return em.createQuery("select p from Periode p", Periode.class).getResultList();
    }

    //still needs more, but the shit is not clear yet

}
