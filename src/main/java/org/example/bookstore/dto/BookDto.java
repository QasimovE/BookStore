package org.example.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bookstore.model.Author;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String title;
    private AuthorDto author;
    private double price;
    private int isbn;
}
