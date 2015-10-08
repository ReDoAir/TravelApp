package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Country;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.*;
import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public class CountryRepo implements Serializable{

    @PersistenceContext
    EntityManager em;

    public List<Country> getAllCountries()
    {
        return em.createQuery("select c from Country c", Country.class).getResultList();
    }

    public List<Country> getAllCountriesWithTrips(){
        return em.createQuery("select a.country from Trip t JOIN t.fromFlight.departAirport a where t.destination = a.city", Country.class).getResultList();
    }

    //returns list of countries based on name
    //when search string is not complete, multiple countries will fill the list
    //for example "Bel" => Belgium, Belarus
    public List<Country> getCountriesByName(String name)
    {
        return em.createQuery("select c from Country c where c.name like :name", Country.class).setParameter("name", "%" + name + "%").getResultList();
    }

    public void addCountry(Country country)
    {
        em.persist(country);
        em.flush();
    }


}
