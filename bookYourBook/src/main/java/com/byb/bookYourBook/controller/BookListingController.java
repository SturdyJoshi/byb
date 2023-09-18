package com.byb.bookYourBook.controller;

import com.byb.bookYourBook.payload.BookListingDto;
import com.byb.bookYourBook.payload.HomeDto;
import com.byb.bookYourBook.service.BookListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookListingController {

    @Autowired
    private BookListingService bookListingService;

    @PostMapping("/add/listing")
    public ResponseEntity<BookListingDto> createListing( @RequestParam(value = "file", required = false) MultipartFile file,
                                                         @RequestPart("data") BookListingDto bookListingDto ) throws IOException {
        System.out.println(bookListingDto.toString());
        if (!file.isEmpty()) {
            System.out.println(bookListingDto.toString());
            byte[] imageBytes = file.getBytes();
            return ResponseEntity.ok().body(bookListingService.createBookListing(bookListingDto, imageBytes));
        } else {
            return ResponseEntity.ok().body(bookListingService.createBookListing(bookListingDto,null));
        }
    }
    @GetMapping("/listing/{id}")
    public ResponseEntity<BookListingDto> getBookListingId(@PathVariable int id){
        return ResponseEntity.ok().body(bookListingService.getBookById(id));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<BookListingDto>> getBookListingByGenre(@PathVariable String genre){
        return ResponseEntity.ok().body(bookListingService.getBookListByGenres(genre));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookListingDto>> getAllBookListing(){
        return ResponseEntity.ok().body(bookListingService.getAllTheBooks());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBookListing(@PathVariable int id){
        return ResponseEntity.ok().body(bookListingService.deleteBookListing(id));
    }

    @PutMapping("/update")
    public ResponseEntity<BookListingDto> updateBookListing(@RequestBody BookListingDto bookListingDto){
        return ResponseEntity.ok().body(bookListingService.updateBookListing(bookListingDto));
    }

    @GetMapping("/home")
    private ResponseEntity<List<HomeDto>> getBookListing(){
        return ResponseEntity.ok().body(bookListingService.get10BookListEvery());
    }

    @PostMapping("/buy/{id}")
    private ResponseEntity<Boolean> buyBook(@PathVariable int id){
        return ResponseEntity.ok().body(bookListingService.purchaseBook(id));
    }
}

