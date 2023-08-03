package com.byb.bookYourBook.dto;

import lombok.Data;

@Data
public class SignInDto {
    String userName;
    String password;
    String email;
    long phoneNumber;
    String addressLine1;
    String addressLine2;
    String city;
    String state;
    int pinCode;
}
