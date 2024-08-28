package org.example.bookstore.serviceTest;

import org.example.bookstore.dto.AuthorDto;
import org.example.bookstore.dto.BookDto;
import org.example.bookstore.model.Author;
import org.example.bookstore.model.Book;
import org.example.bookstore.repository.BookRepository;
import org.example.bookstore.service.RecommendationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecommendationServiceTest {
    @InjectMocks
    private  RecommendationService recommendationService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private Book book;
    @Mock
    private BookDto bookDto;
    @Mock
    private Author author;
    @Mock
    private AuthorDto authorDto;


    @BeforeEach
    void init(){
        book=new Book(1L,"title",9.99,1,author);
        bookDto=new BookDto("title",authorDto,9.99,1);
        author=new Author(1L,"Inal",new ArrayList<>(List.of(book)));
        authorDto=new AuthorDto("Inal");

    }

    @Test
    public void recommendationBookIfEmptyTest(){
        List<Book> all = bookRepository.findAll();
        when(bookRepository.findAll()).thenReturn(all);
        if(all.isEmpty()){
            Book b=new Book();
        }
        recommendationService.recommendationBook();
    }

    @Test
    public void recommendationBookTest(){
        List<Book> all = new ArrayList<>(List.of(new Book()));
        when(bookRepository.findAll()).thenReturn(all);
        if(!all.isEmpty()){
            recommendationService.recommendationBook();
        }
    }

    @Test
    public void sendBookTest(){
        modelMapper.map(book, BookDto.class);
        recommendationService.sendBook();
    }

}
