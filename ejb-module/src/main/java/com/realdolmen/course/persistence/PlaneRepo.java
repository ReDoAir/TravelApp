package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Plane;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless
@LocalBean
public class PlaneRepo {

    @PersistenceContext
    EntityManager em;

    public List<Plane>getAllPlanes()
    {
        return em.createQuery("select p from Plane p", Plane.class).getResultList();
    }

    //returns a list of planes where a piece of the param code was found
    //for example Plane code : E342EES, can return E342EES1,E342EES2,E342EES3,....
    public List<Plane> getPlanesByPlaneCode(String code)
    {
        return em.createQuery("select p from Plane p where p.planeCode like :code", Plane.class).setParameter("code", "%" + code + "%").getResultList();
    }

    public Plane getPlaneById(int id)
    {
        return em.find(Plane.class,id);
    }


}
