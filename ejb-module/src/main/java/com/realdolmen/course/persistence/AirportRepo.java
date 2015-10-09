package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Airport;
import com.realdolmen.course.domain.Country;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.realdolmen.course.domain.Airport.*;

@Stateless
@LocalBean
public class AirportRepo {

    @PersistenceContext
    public
    EntityManager em;

    public List<Airport> getAllAirports()
    {
        return em.createNamedQuery(FIND_ALL_AIRPORTS_Q, Airport.class).getResultList();
    }

    //return list of airports because name of airport is not unique for example
    //Brussels => Brussels Central, Brussels South, Brussels North
    public List<Airport> getAirportsByName(String airportName)
    {
        return em.createNamedQuery(FIND_ALL_AIRPORTS_BY_NAME,Airport.class).setParameter("airportName", "%" + airportName + "%").getResultList();
    }

    //return list of airports based on aiportCode
    //list contains one entity when aiportCode is correct and found 1
    //list contains more entitys when airportCode is only half correct : BE1 will return BE11,BE12,...
    public List<Airport> getAirportsByCode(String airportCode)
    {
        return em.createNamedQuery(FIND_AIRPORT_BY_CODE_Q, Airport.class).setParameter("airportCode", "%" + airportCode + "%").getResultList();
    }

    public List<Airport> getAirportsByCountry(Country country)
    {
        return em.createNamedQuery(FIND_ALL_AIRPORTS_BY_COUNTRY_Q,Airport.class).setParameter("country",country).getResultList();
    }
    //returns a list becaust Londen for example has like 5 airports, them stupid motherfuckers live on an Island why do they need 5 aiports?
    public List<Airport> getAirportsByCity(String city)
    {
        return em.createNamedQuery(FIND_ALL_BY_CITY_Q, Airport.class).setParameter("city","%"+city+"%").getResultList();
    }

    public Airport getAirportById(int id)
    {
        return em.find(Airport.class, id);
    }

    public void addAirport(Airport airport)
    {
        em.persist(airport);
    }

    public Airport getAirportByCode(String airportCode) {
        return em.createQuery("SELECT a FROM Airport a WHERE a.airportCode = :code", Airport.class).setParameter("code", airportCode).getSingleResult();
    }
}
