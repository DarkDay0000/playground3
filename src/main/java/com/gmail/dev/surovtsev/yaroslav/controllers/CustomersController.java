package com.gmail.dev.surovtsev.yaroslav.controllers;

import com.gmail.dev.surovtsev.yaroslav.dao.CustomerDAO;
import com.gmail.dev.surovtsev.yaroslav.models.Customer;
import com.gmail.dev.surovtsev.yaroslav.models.CustomerType;
import com.gmail.dev.surovtsev.yaroslav.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomersController {

    private final CustomerService customerService;
    private final CustomerDAO customerDAO;

    @Autowired
    public CustomersController(CustomerService customerService, CustomerDAO customerDAO) {
        this.customerService = customerService;
        this.customerDAO = customerDAO;
    }

    @GetMapping
    public String index(Model model) {
        //model.addAttribute("customers", customerService.findAll());
        //customerDAO.testGetAndLoad();
        customerDAO.deleteTestData();
        customerDAO.createTestData();
        List<Customer> customers = customerService.findAllWithPagination(6, 10);
        customers.get(0).calcItemsCount();
        System.out.println("======= " + customers.get(0).getItemsCount());
        model.addAttribute("customers", customers);
        //customerDAO.testNPlus1();
        return "customers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "customers/show";
    }

    @GetMapping("/new")
    public String newCustomer(@ModelAttribute("customer") Customer customer) {
        return "customers/new";
    }

    @PostMapping
    public String save(@ModelAttribute("customer") @Valid Customer customer,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customers/new";
        }
        customer.setCustomerType(CustomerType.RETAIL);
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "customers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("customer") Customer updatedCustomer,
                         @PathVariable("id") Integer id) {
        customerService.update(id, updatedCustomer);
        return "redirect:/customers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        customerService.deleteById(id);
        return "redirect:/customers";
    }
}
