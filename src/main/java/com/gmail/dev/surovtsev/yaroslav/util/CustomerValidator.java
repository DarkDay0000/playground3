package com.gmail.dev.surovtsev.yaroslav.util;

import com.gmail.dev.surovtsev.yaroslav.dao.CustomerDAO;
import com.gmail.dev.surovtsev.yaroslav.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CustomerValidator implements Validator {
    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerValidator(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;
        if (customer.getId() != null && customerDAO.show(customer.getEmail(), customer.getId()).isPresent()) {
            errors.rejectValue("email", "", "Email already exists");
        }

        if (!Character.isUpperCase(customer.getFirstName().charAt(0))) {
            errors.rejectValue("firstName", "", "First name should start with a capital letter");
        }

        if (!Character.isUpperCase(customer.getLastName().charAt(0))) {
            errors.rejectValue("lastName", "", "Last name should start with a capital letter");
        }
    }
}
