package com.gmail.dev.surovtsev.yaroslav.dao;

import com.gmail.dev.surovtsev.yaroslav.models.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CustomerDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Customer> index() {
        Session session = sessionFactory.getCurrentSession();
        List<Customer> customers = session.createQuery("select c from Customer c").getResultList();

        return customers;
    }

    @Transactional(readOnly = true)
    public Customer show(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Customer.class, id);
    }

    @Transactional
    public void save(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.save(customer);
    }

    @Transactional
    public void update(Integer id, Customer updatedCustomer) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class, id);

        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setAddress(updatedCustomer.getAddress());
    }

    @Transactional
    public void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Customer.class, id));
    }
}
