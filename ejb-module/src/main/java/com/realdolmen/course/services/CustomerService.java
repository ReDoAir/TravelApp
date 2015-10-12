package com.realdolmen.course.services;

import com.realdolmen.course.domain.auth.Customer;
import com.realdolmen.course.domain.payment.CreditCard;
import com.realdolmen.course.persistence.CustomerRepo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;

@Stateless
public class CustomerService implements Serializable{
    @Inject
    private CustomerRepo customerRepo;

    public void addCreditCard(String username, CreditCard creditCard){
        Customer customer = customerRepo.findCustomerByName(username);
        customer.addPaymentMethod(creditCard);
        customerRepo.updateCustomer(customer);
    }
}
