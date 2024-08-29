package org.example.bookstore.serviceTest;

import org.example.bookstore.config.clients.GoodReadsClient;
import org.example.bookstore.dto.AuthorDto;
import org.example.bookstore.dto.BookDto;
import org.example.bookstore.dto.goodReadsDto.GoodReadsResponse;
import org.example.bookstore.model.Author;
import org.example.bookstore.model.Book;
import org.example.bookstore.repository.AuthorRepository;
import org.example.bookstore.repository.BookRepository;
import org.example.bookstore.service.BookService;
import org.example.bookstore.service.EmailService;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private EmailService emailService;
    @Mock
    private GoodReadsClient goodReadsClient;


    private Author author;
    private Book book;
    private Book book1;
    private BookDto bookDto;
    private BookDto bookDto1;
    private AuthorDto authorDto;


    private Book book2;
    private Book book3;
    private BookDto bookDto2;
    private Author author2;
    private BookDto bookDto3;
    private Author author3;
    private Book book4;
    private BookDto bookDto4;
    private AuthorDto authorDto4;



    @BeforeEach
    void init(){
        book=new Book(1L,"title",9.99,1,author);
        bookDto=new BookDto("title",authorDto,9.99,1);
        book1=new Book(2L,"title1",9.99,2,author);
        bookDto1=new BookDto("title1",authorDto,9.99,2);
        authorDto=new AuthorDto("Inal");

        book2=new Book();
        book3=new Book(3L,"Inal",0.00,4,author2);

        author=new Author(1L,"Inal",new ArrayList<>(List.of(book,book1)));
        author2=new Author(2L,"Inal",new ArrayList<>(List.of(book3)));

        bookDto2=new BookDto();
        bookDto2.setTitle("title4");
        bookDto2.setAuthor(authorDto4);
        author3=new Author(3L,"Sefa",new ArrayList<>());
        book4=new Book(4L,"title4",12.2,5,author3);
        bookDto4=new BookDto("title4",authorDto4,12.2,5);
        authorDto4=new AuthorDto("Sefa");

        bookDto3=new BookDto("Inal", authorDto, 0.0, 4);
    }






    @Test
    public void createBookTest(){
        when(bookRepository.findByIsbn(bookDto.getIsbn())).thenReturn(book);
        when(modelMapper.map(book,BookDto.class)).thenReturn(bookDto);
        BookDto serviceBook = bookService.createBook(bookDto);
        Assertions.assertNotNull(serviceBook);
        Assertions.assertEquals(bookDto.getIsbn(),serviceBook.getIsbn());
    }

    @Test
    public void getAllBooksTest(){
        List<BookDto> bookDtos=new ArrayList<>();
        List<Book> books=new ArrayList<>(List.of(book,book1));
        when(bookRepository.findAll()).thenReturn(books);
        for (Book b:books){
            BookDto map = modelMapper.map(b, BookDto.class);
            bookDtos.add(map);
        }
        List<BookDto> allBooks = bookService.getAllBooks();
        Assertions.assertEquals(bookDtos.size(),allBooks.size());
    }

    @Test
    public void getBookByIDTest(){
        when(modelMapper.map(bookRepository.findById(1L),BookDto.class)).thenReturn(bookDto);
        BookDto bookByID = bookService.getBookByID(1L);
        Assertions.assertEquals(bookDto.getIsbn(),bookByID.getIsbn());
    }

    @Test
    public void  deleteBookTest(){
        when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(book));
        bookRepository.deleteById(book.getId());
        when(modelMapper.map(book,BookDto.class)).thenReturn(bookDto);
        BookDto deleteBook = bookService.deleteBook(1L);
        Assertions.assertEquals(bookDto,deleteBook);
    }


    @Test
    public void updateBookTest(){
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        BookDto bookDto4 = bookService.updateBook(1L, bookDto);
        Assertions.assertEquals(bookDto4,new BookDto());
    }

    @Test
    public void listMappingTest(){
        List<Book> books=new ArrayList<>(List.of(book,book1));
        List<BookDto> bookDtos=new ArrayList<>(List.of(bookDto,bookDto1));
        when(bookService.listMapping(books, BookDto.class)).thenReturn(bookDtos);
        List<BookDto> bookDtos1 = bookService.listMapping(books, BookDto.class);
        Assertions.assertEquals(bookDtos1.size(),bookDtos.size());
    }

    @Test
    public void updateBookFindBookTest(){
        when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(book));
        when(modelMapper.map(bookDto, Book.class)).thenReturn(book);
        book.setId(1L);
        book.setAuthor(author);
        when(bookRepository.save(book)).thenReturn(book);
        when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);
        BookDto bookDto4 = bookService.updateBook(1L, bookDto);
        Assertions.assertEquals(bookDto4,bookDto);
    }

    @Test
    public void createBookIfBookEqualsNullTest(){
        var spy=Mockito.spy(bookService);
        when(spy.getOrAddAuthor(bookDto2)).thenReturn(new Author());
        when(bookRepository.findByIsbn(bookDto2.getIsbn())).thenReturn(null);
        when(modelMapper.map(bookDto2,Book.class)).thenReturn(book2);

        book2.setAuthor(author3);
        when(bookRepository.save(book2)).thenReturn(book4);
        when(modelMapper.map(book4,BookDto.class)).thenReturn(bookDto4);
        BookDto book5 = spy.createBook(bookDto2);
        Assertions.assertNotNull(book4);
        Assertions.assertEquals(book5.getAuthor(),bookDto4.getAuthor());
    }

    @Test
    public void getBooksByAuthorTest(){
        when(authorRepository.findByFullName("Inal")).thenReturn(author);
        var spy = Mockito.spy(bookService);
        List<BookDto> dtos = List.of(bookDto, bookDto1);
        when(spy.listMapping(List.of(book, book1), BookDto.class)).thenReturn(dtos);
        var methodResponse = spy.getBooksByAuthor("Inal");

        Assertions.assertNotNull(methodResponse);
        Assertions.assertEquals(dtos, methodResponse);
    }



    @Test
    public void createBookSendEmailTest(){
        var spy = Mockito.spy(bookService);
        when(spy.getOrAddAuthor(bookDto3)).thenReturn(author2);
        when(bookRepository.findByIsbn(bookDto3.getIsbn())).thenReturn(book3);
        spy.createBook(bookDto3);
        verify(emailService).sendEmail();
    }
}
