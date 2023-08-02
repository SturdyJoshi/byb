package com.byb.bookYourBook.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Data
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long addressId;

    private String addressLine1;
    private String addressLine2;

    private String city;

    private String state;

    @Min(value = 100000, message = "Pincode cant be less than six digits")
    @Max(value = 999999, message = "Pincode cant be less than six digits")
    private int pinCode;

    public Address() { super();}

    public Address( String addressLine1, String addressLine2, String city, String state, int pinCode) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }
}
