package com.gmail.dev.surovtsev.yaroslav;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TestBean testBean = context.getBean("testBean", TestBean.class);
        System.out.println(testBean.getMessage());
        testBean.setMessage("Hello Spring!");
        System.out.println(testBean.getMessage());
        context.close();
    }
}
