package com.byb.bookYourBook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

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
    @Lob
    @Column(name = "image")
    private byte[] imageData;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "bookId", nullable = false, referencedColumnName = "id")
//    ,inverseJoinColumns = @JoinColumn(name = "genreId", nullable = false, referencedColumnName = "id"))
//    private Set<Genre> genre;

}
