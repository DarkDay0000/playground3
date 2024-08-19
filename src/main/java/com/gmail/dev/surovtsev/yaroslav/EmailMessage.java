package com.gmail.dev.surovtsev.yaroslav;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

//@Component
public class EmailMessage implements Messages {
    private Type type;

    public EmailMessage() {
        this.type = Type.EMAIL;
    }

    @Override
    public List<String> getTexts() {
        return Arrays.asList("Email Hello World!", "Email Hello Java!", "Email Hello Spring!");
    }
}
