package com.byb.bookYourBook.payload;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookListingDto {

//    private int id;
    private String name;
    private double mrp;
    private int editionYear;
    private String description;
    private double offerPrice;
    private LocalDateTime publishedDate;
    private String status;
    private byte[] imageData;
}
