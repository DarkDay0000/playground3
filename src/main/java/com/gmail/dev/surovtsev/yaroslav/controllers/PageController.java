package com.gmail.dev.surovtsev.yaroslav.controllers;

import com.gmail.dev.surovtsev.yaroslav.util.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Controller
public class PageController {

    @GetMapping("/page1")
    public String goPage1(@RequestParam(value = "message", required = false) String message,
                          Model model) {
        if (message != null) {
            System.out.println("message: " + message);
            model.addAttribute("message", message);
        }
        return "page1";
    }

    @GetMapping("/page2")
    public String goPage2() {
        return "page2";
    }

    @GetMapping("/calc")
    public String calc(@RequestParam(value = "arg1") BigDecimal arg1,
                       @RequestParam(value = "arg2") BigDecimal arg2,
                       @RequestParam(value = "operation") String operationStr,
                       Model model) {
        Operation operation = Operation.valueOf(operationStr);
        String operationSign = null;
        BigDecimal result = null;
        switch (operation) {
            case addition:
                operationSign = "+";
                result = arg1.add(arg2);
                break;
            case subtraction:
                operationSign = "-";
                result = arg1.subtract(arg2);
                break;
            case division:
                operationSign = "/";
                result = arg1.divide(arg2, 2, RoundingMode.HALF_UP);
                break;
            case multiplication:
                operationSign = "*";
                result = arg1.multiply(arg2);
            default:
        }
        model.addAttribute("arg1", arg1);
        model.addAttribute("arg2", arg2);
        model.addAttribute("operationSign", operationSign);
        model.addAttribute("result", result);
        return "calc";
    }
}
