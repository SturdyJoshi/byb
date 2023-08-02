package com.byb.bookYourBook.repository;

import com.byb.bookYourBook.entity.BookListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookListingRepository extends JpaRepository<BookListing, Integer> {
}
