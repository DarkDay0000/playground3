package com.gmail.dev.surovtsev.yaroslav;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.gmail.dev.surovtsev.yaroslav")
@PropertySource("printer.properties")
public class SpringConfig {
    @Bean
    public EmailMessage emailMessage() {
        return new EmailMessage();
    }

    @Bean
    public SMSMessage smsMessage() {
        return new SMSMessage();
    }

    @Bean
    public Printer printer() {
        return new Printer(smsMessage(), emailMessage());
    }

    @Bean
    public Computer computer() {
        return new Computer(printer());
    }
}
