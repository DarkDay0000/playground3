package com.gmail.dev.surovtsev.yaroslav.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LastPageController {

    @GetMapping("/last")
    public String goLastPage() {
        return "last";
    }
}
