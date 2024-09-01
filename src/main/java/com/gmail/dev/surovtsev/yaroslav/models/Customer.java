package com.gmail.dev.surovtsev.yaroslav.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Integer id;
    @NotEmpty(message = "first name should not be empty")
    @Size(min = 2, max = 50, message = "first name should be between 2 and 50 characters")
    private String firstName;
    @NotEmpty(message = "last name should not be empty")
    @Size(min = 2, max = 50, message = "last name should be between 2 and 50 characters")
    private String lastName;
    @NotEmpty(message = "email should not be empty")
    @Email
    private String email;
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{5}", message = "Your address should be in this format: Country, City, Postal Code (5 digits)")
    private String address;
}
