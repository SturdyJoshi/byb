package com.byb.bookYourBook.serviceImpl;

import com.byb.bookYourBook.entity.*;
import com.byb.bookYourBook.payload.BookListingDto;
import com.byb.bookYourBook.payload.HomeDto;
import com.byb.bookYourBook.repository.BookGenreRepository;
import com.byb.bookYourBook.repository.BookListingRepository;
import com.byb.bookYourBook.repository.GenreRepository;
import com.byb.bookYourBook.repository.PurchasedRepository;
import com.byb.bookYourBook.service.AuthService;
import com.byb.bookYourBook.service.BookListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookListingServiceImpl implements BookListingService {

    private final String IMGUR_API_URL = "https://api.imgur.com/3/upload";
    private final String CLIENT_ID = "6d5e67de1054cb4";


    @Autowired
    private AuthService authService;

    @Autowired
    private BookListingRepository bookListingRepository;

    @Autowired
    private PurchasedRepository purchasedRepository;

    @Autowired
    private BookGenreRepository bookGenreRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<BookListingDto> getAllTheBooks() {
        List<BookListing> allBooks = bookListingRepository.findAll();
        List<BookListingDto> result = new ArrayList<BookListingDto>();
        for(BookListing book : allBooks){
            BookListingDto bookListingDto = BookListingDto.builder()
                    .id(book.getId()).name(book.getName())
                    .mrp(book.getMrp()).editionYear(book.getEditionYear())
                    .description(book.getDescription()).offerPrice(book.getOfferPrice())
                    .status(book.getStatus()).publishedDate(book.getPublishedDate()).build();

            List<String> genres = new ArrayList<>();
            for(BookGenre bookGenre: book.getBookGenres()){
                genres.add(bookGenre.getId().getGenre().getName());
            }
            bookListingDto.setGenres(genres);
            result.add(bookListingDto);
        }
        return result;
    }

    @Override
    public BookListingDto getBookById(int id) {
        Optional<BookListing> bookListingOptional = bookListingRepository.findById(id);
        if(bookListingOptional.isPresent()){
            BookListing bookListing = bookListingOptional.get();
            BookListingDto bookListingDto = BookListingDto.builder().id(bookListing.getId())
                    .name(bookListing.getName()).mrp(bookListing.getMrp())
                    .editionYear(bookListing.getEditionYear()).description(bookListing.getDescription())
                    .offerPrice(bookListing.getOfferPrice()).status(bookListing.getStatus())
                    .publishedDate(bookListing.getPublishedDate()).build();

            List<BookGenre> bookGenreList = bookGenreRepository.findById_BookListing(bookListing);
            ArrayList<String> strings = new ArrayList<>();
            for (BookGenre bookGenre : bookGenreList){
                strings.add(bookGenre.getId().getGenre().getName());
            }
            bookListingDto.setGenres(strings);
            return bookListingDto;
        }
        else {
            return null;
        }
    }

    @Override
    public BookListingDto createBookListing(BookListingDto bookListingDto, byte[] imageBytes) throws IOException {
        User currentUser = authService.getCurrentUser();
        BookListing bookListing = new BookListing(bookListingDto.getName(), bookListingDto.getMrp(),
                bookListingDto.getEditionYear(), bookListingDto.getDescription(), bookListingDto.getOfferPrice(),
                bookListingDto.getPublishedDate(), "AVAILABLE");

        if(imageBytes != null){
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.set("Authorization", "Client-ID " + CLIENT_ID);

            HttpEntity<byte[]> requestEntity = new HttpEntity<>(imageBytes, headers);

            ResponseEntity<Object> responseEntity = restTemplate.exchange(
                    "https://api.imgur.com/3/image",
                    HttpMethod.POST,
                    requestEntity,
                    Object.class
            );

            if (responseEntity.getStatusCode() == HttpStatus.OK) {

                String responseBody = responseEntity.getBody().toString();
                System.out.println(responseEntity.getBody().toString());

                int start = responseBody.indexOf("link") + 5;
                int end = responseBody.indexOf("}", start);
                bookListing.setImageData(responseBody.substring(start, end));
            } else {
                throw new RuntimeException("Failed to upload image to Imgur. Status code: " + responseEntity.getStatusCode());
            }
        }
        bookListing.setUser(currentUser);
        bookListing = bookListingRepository.save(bookListing);
        List<Genre> genreList = new ArrayList<>();
        for(String genre : bookListingDto.getGenres()){
            Optional<Genre> genreOptional = genreRepository.findByName(genre);
            Genre genre1 = new Genre();
            if(genreOptional.isPresent()){
                genre1 = genreRepository.save(genreOptional.get());
                genreList.add(genre1);
            }
            else{
                genre1.setName(genre);
                genreList.add(genreRepository.save(genre1));
            }
            bookGenreRepository.save(new BookGenre(new BookGenrePrimaryKey(bookListing,genre1)));
        }
        bookListingDto.setImageData(bookListing.getImageData());
        return bookListingDto;
    }

    @Override
    public String deleteBookListing(int id) {
        bookListingRepository.deleteById(id);
        return "Deleted";
    }

    @Override
    public BookListingDto updateBookListing(BookListingDto bookListingDto) {
        Optional<BookListing> bookOpt = bookListingRepository.findById(bookListingDto.getId());
        if(bookOpt.isPresent()){
            BookListing bookListing = bookOpt.get();
            bookListing.setName(bookListingDto.getName());
            bookListing.setMrp(bookListingDto.getMrp());
            bookListing.setEditionYear(bookListingDto.getEditionYear());
            bookListing.setDescription(bookListingDto.getDescription());
            bookListing.setOfferPrice(bookListingDto.getOfferPrice());
            bookListing.setStatus(bookListing.getStatus());
//            bookListing.setImageData(bookListingDto.getImageData());
            List<Genre> genreList = new ArrayList<>();
            for(String genre : bookListingDto.getGenres()){
                Optional<Genre> genreOptional = genreRepository.findByName(genre);
                Genre genre1 = new Genre();
                if(genreOptional.isPresent()){
                    genre1 = genreRepository.save(genreOptional.get());
                    genreList.add(genre1);
                }
                else{
                    genre1.setName(genre);
                    genreList.add(genreRepository.save(genre1));
                }
                bookGenreRepository.save(new BookGenre(new BookGenrePrimaryKey(bookListing,genre1)));
            }
            return bookListingDto;
        }
        else{
            return null;
        }
    }

    @Override
    public List<BookListingDto> getBookListByGenres(String genre) {
        List<BookListing> allBooks = bookListingRepository.findByBookGenres_Id_Genre_Name(genre);
        System.out.println(allBooks.size());
        List<BookListingDto> result = new ArrayList<BookListingDto>();
        for(BookListing book : allBooks){
            BookListingDto bookListingDto = BookListingDto.builder()
                    .id(book.getId()).name(book.getName())
                    .mrp(book.getMrp()).editionYear(book.getEditionYear())
                    .description(book.getDescription()).offerPrice(book.getOfferPrice())
                    .status(book.getStatus()).publishedDate(book.getPublishedDate()).build();

            List<String> genres = new ArrayList<>();
            for(BookGenre bookGenre: book.getBookGenres()){
                genres.add(bookGenre.getId().getGenre().getName());
            }
            bookListingDto.setGenres(genres);
            result.add(bookListingDto);
        }
        return result;
    }

    @Override
    public List<HomeDto> get10BookListEvery() {
        List<Genre> allGenres = genreRepository.findAll();
        List<HomeDto> homeDtoList = new ArrayList<>();
        for(Genre genre : allGenres){
            HomeDto homeDto = new HomeDto();
            homeDto.setGenreName(genre.getName());
            List<BookListing> allBooks = bookListingRepository.findTop10ByBookGenres_Id_Genre_NameAndStatus(genre.getName(),"AVAILABLE");
            List<BookListingDto> result = new ArrayList<BookListingDto>();
            for(BookListing book : allBooks){
                BookListingDto bookListingDto = BookListingDto.builder()
                        .id(book.getId()).name(book.getName())
                        .mrp(book.getMrp()).editionYear(book.getEditionYear())
                        .description(book.getDescription()).offerPrice(book.getOfferPrice())
                        .status(book.getStatus()).publishedDate(book.getPublishedDate()).build();

                List<String> genres = new ArrayList<>();
                for(BookGenre bookGenre: book.getBookGenres()){
                    genres.add(bookGenre.getId().getGenre().getName());
                }
                bookListingDto.setGenres(genres);
                result.add(bookListingDto);
            }
            homeDto.setBookListingsList(result);
            homeDtoList.add(homeDto);
        }
        return homeDtoList;
    }

    @Override
    public Boolean purchaseBook(int id) {
        Optional<BookListing> bookListingOptional = bookListingRepository.findById(id);
        if(bookListingOptional.isPresent()){
            BookListing bookListing = bookListingOptional.get();
            bookListing.setStatus("SOLD");
            User currentUser = authService.getCurrentUser();
            PurchasedHistory purchasedHistory = new PurchasedHistory();
            purchasedHistory.setPurchasedBy(currentUser);
            purchasedHistory.setBookListing(bookListing);
            bookListingRepository.save(bookListing);
            PurchasedHistory save = purchasedRepository.save(purchasedHistory);
            return true;
        }
        else{
            return false;
        }
    }
}
