package com.gmail.dev.surovtsev.yaroslav.services;

import com.gmail.dev.surovtsev.yaroslav.models.Customer;
import com.gmail.dev.surovtsev.yaroslav.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomersRepository customersRepository;

    @Autowired
    public CustomerService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public List<Customer> findAll() {
        return customersRepository.findAll();
    }

    public Optional<Customer> findById(int id) {
        return customersRepository.findById(id);
    }

    @Transactional
    public Customer save(Customer customer) {
        return customersRepository.save(customer);
    }

    @Transactional
    public Customer update(int id, Customer updatedCustomer) {
        updatedCustomer.setId(id);
        return customersRepository.save(updatedCustomer);
    }

    @Transactional
    public void deleteById(int id) {
        customersRepository.deleteById(id);
    }
}
