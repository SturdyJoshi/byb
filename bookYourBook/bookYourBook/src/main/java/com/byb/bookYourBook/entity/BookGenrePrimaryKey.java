package com.byb.bookYourBook.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Book;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookGenrePrimaryKey {

    @ManyToOne
    private BookListing bookListing;
    @ManyToOne
    private Genre genre;
}
