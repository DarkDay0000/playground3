package com.gmail.dev.surovtsev.yaroslav.dao;

import com.gmail.dev.surovtsev.yaroslav.models.Customer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDAO {
    private int CUSTOMER_COUNT;
    private List<Customer> customers;

    {
        customers = new ArrayList<>();
        customers.add(new Customer(++CUSTOMER_COUNT, "Billy", "Dragon"));
        customers.add(new Customer(++CUSTOMER_COUNT, "Jimmy", "Dragon"));
        customers.add(new Customer(++CUSTOMER_COUNT, "Toad", "Frog"));
    }

    public List<Customer> index() {
        return customers;
    }

    public Customer show(Integer id) {
        return customers.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    public void save(Customer customer) {
        customer.setId(++CUSTOMER_COUNT);
        customers.add(customer);
    }

    public void update(Integer id, Customer updatedCustomer) {
        Customer customerToBeUpdated = show(id);
        customerToBeUpdated.setFirstName(updatedCustomer.getFirstName());
        customerToBeUpdated.setLastName(updatedCustomer.getLastName());
    }

    public void delete(Integer id) {
        customers.removeIf(c -> c.getId().equals(id));
    }
}
