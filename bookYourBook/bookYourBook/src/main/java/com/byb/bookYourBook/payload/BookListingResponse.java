package com.byb.bookYourBook.payload;

import java.util.List;

public class BookListingResponse {
    private List<BookListingDto> books;
    private int pageNo;
    private int pageSize;
    private long totalItems;
    private int totalPages;
    private boolean isLast;
}
