package com.realdolmen.course.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.realdolmen.course.domain.auth.Customer;
import com.realdolmen.course.domain.auth.User;

@Stateless
public class UserRepo {

    @PersistenceContext
    private EntityManager em;

    public User find(Integer id) {
        return em.find(User.class, id);
    }

    public User find(String username, String password) {
        List<User> found = em.createNamedQuery("User.find", User.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();
        return found.isEmpty() ? null : found.get(0);
    }

    @Produces
    @Named("users")
    @RequestScoped
    public List<User> list() {
        return em.createNamedQuery("User.list", User.class).getResultList();
    }

    public Integer create(User user) {
        em.persist(user);
        return user.getId();
    }

    public void update(User user) {
        em.merge(user);
    }

    public void delete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

}
