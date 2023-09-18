package com.byb.bookYourBook.repository;

import com.byb.bookYourBook.entity.BookListing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookListingRepository extends JpaRepository<BookListing, Integer> {
    List<BookListing> findByBookGenres_Id_Genre_Name(String name);
    List<BookListing> findTop10ByBookGenres_Id_Genre_NameAndStatus(String name, String status);
}
