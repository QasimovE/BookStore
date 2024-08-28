package org.example.bookstore.dto.goodReadsDto;

import lombok.Data;

import java.util.ArrayList;
@Data
public class GoodReadsResponse {
    private String imageUrl;
    private String bookId;
    private String workId;
    private String bookUrl;
    private String title;
    private ArrayList<Author> author;
    private String rank;
    private String rating;
    private String totalRatings;
    private String publishedYear;
    private String totalEditions;
}
