package com.byb.bookYourBook.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.ImageInputStreamImpl;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NotEmpty
@Builder
public class BookListingDto {
    private int id;
    private String name;
    private double mrp;
    private int editionYear;
    private String description;
    private double offerPrice;
    private String status;
    private String imageData;
    List<String> genres;
    private Timestamp publishedDate;
}
