package org.example.bookstore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bookstore.config.clients.GoodReadsClient;
import org.example.bookstore.dto.AuthorDto;
import org.example.bookstore.dto.BookDto;
import org.example.bookstore.dto.goodReadsDto.GoodReadsResponse;
import org.example.bookstore.model.Author;
import org.example.bookstore.model.Book;
import org.example.bookstore.repository.AuthorRepository;
import org.example.bookstore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final AuthorRepository authorRepository;
    private final EmailService emailService;
    private final GoodReadsClient goodReadsClient;
    @Value("${host}")
    private  String host;
    @Value("${key}")
    private  String key;

    public BookDto createBook(BookDto bookDto) {
        Author author = getOrAddAuthor(bookDto);
        Book book = bookRepository.findByIsbn(bookDto.getIsbn());
        if (book == null) {
            book = modelMapper.map(bookDto, Book.class);
            book.setAuthor(author);
            book = bookRepository.save(book);
            log.info("Create book {}" ,bookDto);
        }
        if(bookDto.getTitle().equals("Inal")){
            emailService.sendEmail();
            log.info("Send mail {}",emailService);
        }
        return modelMapper.map(book, BookDto.class);
    }

    public Author   getOrAddAuthor(BookDto bookDto) {
        AuthorDto authorDto = bookDto.getAuthor();
        if (authorDto == null) return null;
        Author author = authorRepository.findByFullName(authorDto.getFullName());
        if (author == null) {
            author = modelMapper.map(authorDto, Author.class);
            author = authorRepository.save(author);
        }
        return author;
    }

    public List<BookDto> getAllBooks() {
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book b : bookRepository.findAll()) {
            BookDto map = modelMapper.map(b, BookDto.class);
            bookDtos.add(map);
        }
        log.info("Gat all books {}", bookDtos);
        return bookDtos;
    }

    public BookDto getBookByID(Long id) {
        log.info("Get book by id {}",id);
        return modelMapper.map(bookRepository.findById(id), BookDto.class);
    }

    public List<BookDto> getBooksByAuthor(String authorName) {
        Author author = authorRepository.findByFullName(authorName);
        List<Book> books = author.getBooks();
        log.info("Get books by author {}", authorName);
        return listMapping(books, BookDto.class);
    }

    public <D, S> List<D> listMapping(List<S> model, Class<D> dto) {
        return model.stream().map(m -> modelMapper.map(m, dto)).toList();
    }

    public BookDto updateBook(Long id, BookDto bookDto) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Author author = getOrAddAuthor(bookDto);
            Book mapped = modelMapper.map(bookDto, Book.class);
            mapped.setId(id);
            mapped.setAuthor(author);
            log.info("Update book {} {}",id,bookDto);
            return modelMapper.map(bookRepository.save(mapped), BookDto.class);
        } else {
            log.info("Not found");
            return new BookDto();
        }
    }

    public BookDto deleteBook(Long id) {
        Book byId = bookRepository.findById(id).orElse(new Book());
        bookRepository.deleteById(byId.getId());
        log.info("Delete book {}",id);
        return modelMapper.map(byId, BookDto.class);
    }

    public String searchWithKeyWord(String input){
        List<GoodReadsResponse> roots = goodReadsClient.searchBook(host, key, input, "1");
       return roots.getFirst().getTotalRatings();
    }
}