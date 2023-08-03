package com.byb.bookYourBook.service;

import com.byb.bookYourBook.entity.BookListing;
import com.byb.bookYourBook.payload.BookListingDto;

import java.util.List;

public interface BookListingService {

    List<BookListing> getAllTheBooks();
    BookListing getBookById(int id);
    BookListingDto createBookListing(BookListingDto bookListingDto);
    void deleteBookListing(int id);
    BookListingDto updateBookListing(BookListingDto bookListingDto, int id);
//    public
}
