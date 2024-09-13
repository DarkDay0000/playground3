package com.gmail.dev.surovtsev.yaroslav.services;

import com.gmail.dev.surovtsev.yaroslav.models.Customer;
import com.gmail.dev.surovtsev.yaroslav.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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

    public List<Customer> findAllWithPagination(int pageNumber, int size) {
        Page<Customer> page = customersRepository.findAll(PageRequest.of(pageNumber, size, Sort.by("firstName")));
        return page.getContent();
    }

    public Customer findById(int id) {
        return customersRepository.findById(id).orElse(null);
    }

    @Transactional
    public Customer save(Customer customer) {
        customer.setCreatedAt(new Date());
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

    public Customer findByEmail(String email) {
        return customersRepository.findByEmail(email);
    }
}
