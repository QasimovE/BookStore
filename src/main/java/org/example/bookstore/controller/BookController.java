package org.example.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Library;
import org.example.bookstore.dto.BookDto;
import org.example.bookstore.model.Author;
import org.example.bookstore.model.Book;
import org.example.bookstore.service.BookService;
import org.example.bookstore.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
public class BookController {
    private final BookService bookService;
    private final RecommendationService recommendationService;

    @PostMapping("/create")
    public BookDto createBook(@RequestBody BookDto bookDto){
        return bookService.createBook(bookDto);
    }

    @GetMapping("/getAllBooks")
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/getBookByID")
    public BookDto getBookByID(@RequestParam Long id){
       return bookService.getBookByID(id);
    }

    @GetMapping("/getBooksByAuthor")
    public List<BookDto> getBooksByAuthor(@RequestParam String author){
       return bookService.getBooksByAuthor(author);
    }

    @DeleteMapping("/delete")
    public BookDto deleteBook(@RequestParam Long id){
        return bookService.deleteBook(id);
    }

    @PutMapping("/updateBook")
    public BookDto updateBook(@RequestParam Long id, @RequestBody BookDto bookDto){
       return bookService.updateBook(id,bookDto);
    }

    @GetMapping("sendBook")
    public BookDto sendBook(){
        return recommendationService.sendBook();
    }
    @GetMapping("bookUrl")
    public String searchWithKeyWord(@RequestParam String input){
       return bookService.searchWithKeyWord(input);
    }

}
