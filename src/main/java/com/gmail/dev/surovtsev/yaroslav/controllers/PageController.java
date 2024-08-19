package com.gmail.dev.surovtsev.yaroslav.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @GetMapping("/page1")
    public String goPage1() {
        return "page1";
    }

    @GetMapping("/page2")
    public String goPage2() {
        return "page2";
    }
}
