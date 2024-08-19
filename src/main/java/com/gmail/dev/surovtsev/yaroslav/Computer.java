package com.gmail.dev.surovtsev.yaroslav;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component
//@Scope("prototype")
public class Computer {
    private int id;
    private Printer printer;

    //@Autowired
    public Computer(Printer printer) {
        this.id = 1;
        this.printer = printer;
    }

    public void printMessage() {
        System.out.println("*************************************");
        System.out.println("Computer id: " + id);
        System.out.println("Printer SerialNumber: " + printer.getSerialNumber());
        System.out.println("Printer Name: " + printer.getName());
        System.out.println("Printer Message: " + printer.getMessage());
    }

    @PostConstruct
    public void init() {
        System.out.println("bean init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("bean destroy");
    }
}
