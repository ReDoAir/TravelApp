package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Periode;

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
public class PeriodeRepo {

    @Inject
    EntityManager em;


    public List<Periode> getAllPeriodes()
    {
        return em.createQuery("select p from Periode p").getResultList();
    }

    //still needs more, but the shit is not clear yet

}
