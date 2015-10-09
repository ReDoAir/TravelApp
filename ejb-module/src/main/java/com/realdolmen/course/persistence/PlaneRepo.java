package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Plane;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

import static com.realdolmen.course.domain.Plane.FIND_ALL_PLANES;
import static com.realdolmen.course.domain.Plane.FIND_ALL_PLANES_WITH_NUMBER_OF_SEATS_HIGHER_THAN;
import static com.realdolmen.course.domain.Plane.FIND_PLANE_WITH_CODE;

@Stateless
@LocalBean
public class PlaneRepo {

    @PersistenceContext
    public
    EntityManager em;

    /*
        public static final String FIND_ALL_PLANES="Plane.findAllPlanes",
    FIND_PLANE_WITH_CODE="Plane.findPlaneWithCode",
    FIND_ALL_PLANES_WITH_NUMBER_OF_SEATS_HIGHER_THAN="Plane.findAllPlanesWithNumberOfSeatsHigherThan";
     */
    public List<Plane> getAllPlanes()
    {

        return em.createNamedQuery(FIND_ALL_PLANES,Plane.class).getResultList();
    }

    //returns a list of planes where a piece of the param code was found
    //for example Plane code : E342EES, can return E342EES1,E342EES2,E342EES3,....
    public List<Plane> getPlanesByPlaneCode(String code)
    {
        return em.createNamedQuery(FIND_PLANE_WITH_CODE, Plane.class).setParameter("planeCode", "%" + code + "%").getResultList();
    }

    public List<Plane>getPlanesWithMoreSeatsThan(int number)
    {
        return em.createNamedQuery(FIND_ALL_PLANES_WITH_NUMBER_OF_SEATS_HIGHER_THAN,Plane.class).setParameter("number", number).getResultList();
    }


    public Plane getPlaneById(int id)
    {
        return em.find(Plane.class, id);
    }

    public void addPlane(Plane plane)
    {
        em.persist(plane);
    }
    //planes are re-usable, but if you want we can delete them
    public void removePlane(Plane plane)
    {
        em.remove(plane);
    }

    public Plane getPlaneByCode(String planeCode) {
        return em.createQuery("SELECT p FROM Plane p WHERE p.planeCode = :code", Plane.class).setParameter("code",planeCode).getSingleResult();
    }
}
