package com.gmail.dev.surovtsev.yaroslav.controllers;

import com.gmail.dev.surovtsev.yaroslav.dao.CustomerDAO;
import com.gmail.dev.surovtsev.yaroslav.models.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomersController {
    private CustomerDAO customerDAO;

    @Autowired
    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("customers", customerDAO.index());
        return "customers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("customer", customerDAO.show(id));
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
        customerDAO.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("customer", customerDAO.show(id));
        return "customers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("customer") @Valid Customer updatedCustomer,
                         BindingResult bindingResult,
                         @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "customers/edit";
        }
        customerDAO.update(id, updatedCustomer);
        return "redirect:/customers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        customerDAO.delete(id);
        return "redirect:/customers";
    }
}
