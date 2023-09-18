package com.byb.bookYourBook.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookGenrePrimaryKey {

    @ManyToOne
    @JoinColumn(name = "bookListing", referencedColumnName = "id")
    private BookListing bookListing;
    @ManyToOne
    private Genre genre;
}
