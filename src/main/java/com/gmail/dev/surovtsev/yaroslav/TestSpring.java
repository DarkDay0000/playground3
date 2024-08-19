package com.gmail.dev.surovtsev.yaroslav;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Computer computer = context.getBean("computer", Computer.class);
        computer.printMessage();
        Computer computer2 = context.getBean("computer", Computer.class);
        computer2.printMessage();
        context.close();
    }
}
