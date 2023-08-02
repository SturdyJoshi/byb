package com.byb.bookYourBook.dto;

import com.byb.bookYourBook.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
