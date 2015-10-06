package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Country;

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
public class CountryRepo {

    @Inject
    EntityManager em;

    public List<Country> getAllCountries()
    {
        return em.createQuery("select c from Country c").getResultList();
    }

    //returns list of countries based on name
    //when search string is not complete, multiple countries will fill the list
    //for example "Bel" => Belgium, Belarus
    public List<Country> getCountriesByName(String name)
    {
        return em.createQuery("select c from Country c where c.name like :name").setParameter("name", "%" + name + "%").getResultList();
    }

    public void addCountry(Country country)
    {
        em.persist(country);
        em.flush();
    }


}
