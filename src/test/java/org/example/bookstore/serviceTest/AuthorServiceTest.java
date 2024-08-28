package org.example.bookstore.serviceTest;

import org.example.bookstore.dto.AuthorDto;
import org.example.bookstore.model.Author;
import org.example.bookstore.model.Book;
import org.example.bookstore.repository.AuthorRepository;
import org.example.bookstore.repository.BookRepository;
import org.example.bookstore.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
    @InjectMocks
    private AuthorService authorService;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private BookRepository bookRepository;

    private Author author;
    private Author author1;
    private Author author2;
    private Author author3;
    private AuthorDto authorDto;
    private AuthorDto authorDto1;
    private AuthorDto authorDto2;

    private Book book;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void init(){
        authorDto=new AuthorDto("Inal");
        book=new Book(1L,"title",9.99,1,author);
        book1=new Book(2L,"title1",9.99,2,author);
        book2=new Book(3L,"title2",9.99,3,author2);
        book3=new Book(4L,"title3",9.99,4,author2);
        author=new Author(1L,"Inal",new ArrayList<>(List.of(book,book1)));
        author1=new Author();
        authorDto1=new AuthorDto();
        author2=new Author(2L,"Sevinc",new ArrayList<>(List.of(book2,book3)));
        author3=new Author(1L,"Cefa",new ArrayList<>(List.of(book,book2)));
        authorDto2=new AuthorDto("Cefa");

    }

    @Test
    public void createAuthorTest(){
        when(modelMapper.map(authorDto, Author.class)).thenReturn(author);
        when(authorRepository.findByFullName(author.getFullName())).thenReturn(author);
        when(modelMapper.map(author,AuthorDto.class)).thenReturn(authorDto);
        AuthorDto serviceAuthorDto = authorService.createAuthor(authorDto);
        Assertions.assertNotNull(authorDto);
        Assertions.assertEquals(authorDto.getFullName(),serviceAuthorDto.getFullName());
    }

    @Test
    public void createNewAuthorTest(){
        when(modelMapper.map(authorDto1, Author.class)).thenReturn(author1);
        when(authorRepository.save(author1)).thenReturn(author);
        when(modelMapper.map(author, AuthorDto.class)).thenReturn(authorDto);
        AuthorDto serviceAuthorDto = authorService.createAuthor(authorDto1);
        Assertions.assertEquals(authorDto,serviceAuthorDto);
    }

    @Test
    public void getAllAuthorTest(){
        List<AuthorDto> authorDtos=new ArrayList<>();
        List<Author> authors=new ArrayList<>(List.of(author,author2));
        when(authorRepository.findAll()).thenReturn(authors);
        for (Author a:authors){
            AuthorDto map = modelMapper.map(a, AuthorDto.class);
            authorDtos.add(map);
        }
        List<AuthorDto> allAuthor = authorService.getAllAuthor();
        Assertions.assertEquals(authorDtos.size(),allAuthor.size());
    }

    @Test
    public void getAuthorByIDTest(){
        when(modelMapper.map(authorRepository.findById(1L),AuthorDto.class)).thenReturn((authorDto));
        AuthorDto authorByID = authorService.getAuthorByID(1L);
        Assertions.assertEquals(authorDto,authorByID);
    }

    @Test
    public void getAuthorByBookTest(){
        String title="title";
        when(bookRepository.findByTitle(title)).thenReturn(book);
        when(modelMapper.map(book.getAuthor(), AuthorDto.class)).thenReturn(authorDto);
        AuthorDto authorByBook = authorService.getAuthorByBook(title);
        Assertions.assertEquals(authorDto,authorByBook);
    }


    @Test
    public void updateAuthorTest(){
        when(authorRepository.findById(1L)).thenReturn(Optional.ofNullable(author));
        when(modelMapper.map(authorDto, Author.class)).thenReturn(author);
        author.setId(1L);
        when(authorRepository.save(author)).thenReturn(author3);
        when(modelMapper.map(author3, AuthorDto.class)).thenReturn(authorDto2);
        AuthorDto serviceAuthorDto = authorService.updateAuthor(1L, authorDto);
        Assertions.assertEquals(authorDto2,serviceAuthorDto);
    }

    @Test
    public void updateAuthorIfNullTest(){
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());
        AuthorDto serviceAuthorDto = authorService.updateAuthor(1L, authorDto);
        Assertions.assertEquals(serviceAuthorDto,new AuthorDto());
    }


    @Test
    public void deleteAuthorTest(){
        when(authorRepository.findById(1L)).thenReturn(Optional.ofNullable(author));
        authorRepository.deleteById(author.getId());
        when(modelMapper.map(author, AuthorDto.class)).thenReturn(authorDto);
        AuthorDto serviceAuthorDto = authorService.deleteAuthor(1L);
        Assertions.assertEquals(authorDto,serviceAuthorDto);
    }
}
