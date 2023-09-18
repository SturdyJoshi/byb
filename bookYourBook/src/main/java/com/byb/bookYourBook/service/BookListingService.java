package com.byb.bookYourBook.service;

import com.byb.bookYourBook.payload.BookListingDto;
import com.byb.bookYourBook.payload.HomeDto;

import java.io.IOException;
import java.util.List;

public interface BookListingService {

    List<BookListingDto> getAllTheBooks();
    BookListingDto getBookById(int id);
    BookListingDto createBookListing(BookListingDto bookListingDto, byte[] imageBytes) throws IOException;

    String deleteBookListing(int id);
    BookListingDto updateBookListing(BookListingDto bookListingDto);
    List<BookListingDto> getBookListByGenres(String genre);
    List<HomeDto> get10BookListEvery();

    Boolean purchaseBook(int id);
}
