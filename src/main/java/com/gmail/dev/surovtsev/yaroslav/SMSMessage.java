package com.gmail.dev.surovtsev.yaroslav;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

//@Component
public class SMSMessage implements Messages {
    private Type type;

    public SMSMessage() {
        this.type = Type.SMS;
    }

    @Override
    public List<String> getTexts() {
        return Arrays.asList("SMS Hello World!", "SMS Hello Java!", "SMS Hello Spring!");
    }
}
