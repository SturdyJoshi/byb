package com.byb.bookYourBook.repository;

import com.byb.bookYourBook.entity.BookGenre;
import com.byb.bookYourBook.entity.BookListing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookGenreRepository extends JpaRepository<BookGenre, Integer>{
    List<BookGenre> findById_BookListing(BookListing bookListing);
}
