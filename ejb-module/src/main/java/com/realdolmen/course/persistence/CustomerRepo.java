package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.auth.Customer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class CustomerRepo {

    @PersistenceContext
    private EntityManager em;

    public Customer findCustomer(String username) {
        return em.createQuery("SELECT c FROM Customer c WHERE c.username = :username", Customer.class).setParameter("username", username).getSingleResult();
    }
}
