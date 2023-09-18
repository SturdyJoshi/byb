package com.byb.bookYourBook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Column(name = "MRP")
    private double mrp;
    private int editionYear;
    private String description;
    private double offerPrice;
    @CreationTimestamp
    private Timestamp publishedDate;
    private String status;

    @ManyToOne
    private User user;

    private String imageData;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "id.bookListing")
    private List<BookGenre> bookGenres;

    public BookListing(String name, double mrp, int editionYear, String description, double offerPrice, Timestamp publishedDate, String status) {
        this.name = name;
        this.mrp = mrp;
        this.editionYear = editionYear;
        this.description = description;
        this.offerPrice = offerPrice;
        this.publishedDate = publishedDate;
        this.status = status;
        this.imageData = imageData;
    }
}
