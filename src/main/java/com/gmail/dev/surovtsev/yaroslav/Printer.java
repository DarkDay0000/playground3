package com.gmail.dev.surovtsev.yaroslav;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Getter
@Setter
//@Component
public class Printer {
    @Value("${printer.serial_number}")
    private int serialNumber;
    @Value("${printer.name}")
    private String name;
    private Messages SMSMessage;
    private Messages EmailMessage;

    //@Autowired
    public Printer(@Qualifier("SMSMessage") Messages SMSMessage,
                   @Qualifier("emailMessage") Messages EmailMessage) {
        this.SMSMessage = SMSMessage;
        this.EmailMessage = EmailMessage;
    }

    public String getMessage() {
        Random rand = new Random();
        int typeIndex = rand.nextInt(2);
        int textIndex = rand.nextInt(3);
        Type type = Type.values()[typeIndex];
        return type == Type.SMS ? SMSMessage.getTexts().get(textIndex) : EmailMessage.getTexts().get(textIndex);
    }
}
