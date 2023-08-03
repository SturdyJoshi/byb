package com.byb.bookYourBook.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@NotEmpty
public class BookListingDto {

    private String name;

    private double mrp;

    private int editionYear;
    private String description;
    private double offerPrice;
//    private String status;
    private byte[] imageData;
}
