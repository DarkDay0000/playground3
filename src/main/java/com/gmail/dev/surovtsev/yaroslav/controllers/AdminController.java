package com.gmail.dev.surovtsev.yaroslav.controllers;

import com.gmail.dev.surovtsev.yaroslav.dao.CustomerDAO;
import com.gmail.dev.surovtsev.yaroslav.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CustomerDAO customerDAO;

    @Autowired
    public AdminController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping
    public String adminPage(Model model, @ModelAttribute("customer") Customer customer) {
        model.addAttribute("customers", customerDAO.index());
        return "adminPage";
    }

    @PatchMapping("/add")
    public String makeAdmin(@ModelAttribute("customer") Customer customer) {
        System.out.println("Admin id: " + customer.getId());
        return "redirect:/customers";
    }
}
