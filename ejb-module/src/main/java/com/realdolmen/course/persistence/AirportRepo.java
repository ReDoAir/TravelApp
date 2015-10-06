package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Airport;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class AirportRepo {

    @PersistenceContext
    EntityManager em;

    private List<Airport> getAllAirports()
    {
        return em.createQuery("select a from Airport a", Airport.class).getResultList();
    }

    //return list of airports because name of airport is not unique for example
    //Brussels => Brussels Central, Brussels South, Brussels North
    public List<Airport> getAirportsByName(String airportName)
    {
        return em.createQuery("select a from Airport a where a.name like :airportName",Airport.class).setParameter("airportName", "%" + airportName + "%").getResultList();
    }

    //return list of airports based on aiportCode
    //list contains one entity when aiportCode is correct and found 1
    //list contains more entitys when airportCode is only half correct : BE1 will return BE11,BE12,...
    public List<Airport> getAirportsByCode(String airportCode)
    {
        return em.createQuery("select a from Airport  a where a.airportCode like :airportCode").setParameter("airportCode","%"+airportCode+"%").getResultList();
    }

    public Airport getAirportById(int id)
    {
        return em.find(Airport.class, id);
    }

    public void addAirport(Airport airport)
    {
        em.persist(airport);
    }





}
