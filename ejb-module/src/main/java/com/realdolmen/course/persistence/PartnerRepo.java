package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.auth.Partner;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class PartnerRepo {

    @PersistenceContext
    private EntityManager em;

    public Partner findPartner(String userName){
        return em.createQuery("SELECT p FROM Partner p WHERE p.username = :username", Partner.class).setParameter("username",userName).getSingleResult();
    }
}
