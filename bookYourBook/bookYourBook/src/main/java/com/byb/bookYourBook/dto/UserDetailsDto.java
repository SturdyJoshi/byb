package com.byb.bookYourBook.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailsDto {
    String userName;
    String email;
    long phoneNumber;
    String addressLine1;
    String addressLine2;
    String city;
    String state;
    int pinCode;
}
