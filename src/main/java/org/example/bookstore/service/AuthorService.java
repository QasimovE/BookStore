package org.example.bookstore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bookstore.dto.AuthorDto;
import org.example.bookstore.model.Author;
import org.example.bookstore.model.Book;
import org.example.bookstore.repository.AuthorRepository;
import org.example.bookstore.repository.BookRepository;
import org.example.bookstore.config.clients.GoodReadsClient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
     public AuthorDto createAuthor(AuthorDto authorDto){
         Author map = modelMapper.map(authorDto, Author.class);
         Author byFullName = authorRepository.findByFullName(map.getFullName());
         if(byFullName!=null){
             log.info("Already created author {}",authorDto);
             return modelMapper.map(byFullName,AuthorDto.class);
         }
         log.info("Create new author {}",authorDto);
         return modelMapper.map(authorRepository.save(map),AuthorDto.class);
     }

    public List<AuthorDto> getAllAuthor(){
         List<AuthorDto> authorDtos=new ArrayList<>();
         for (Author a:authorRepository.findAll()){
             AuthorDto map = modelMapper.map(a, AuthorDto.class);
             authorDtos.add(map);
         }
         log.info("Get all author {}",authorDtos);
         return authorDtos;
    }

    public AuthorDto getAuthorByID(Long id){
         log.info("Get author by id {}",id);
       return   modelMapper.map(authorRepository.findById(id),AuthorDto.class);
    }

    public AuthorDto getAuthorByBook(String title){
        Book book = bookRepository.findByTitle(title);
        log.info("Get author by book {}",title);
        return modelMapper.map(book.getAuthor(), AuthorDto.class);
    }

    public AuthorDto updateAuthor(Long id, AuthorDto authorDto){
        Optional<Author> byId = authorRepository.findById(id);
        if(byId.isPresent()){
            Author author = modelMapper.map(authorDto, Author.class);
            author.setId(id);
            log.info("Update author {} {}", id,authorDto);
            return modelMapper.map(authorRepository.save(author), AuthorDto.class);

        }
        return new AuthorDto();
    }

    public AuthorDto deleteAuthor(Long id){
        Author author = authorRepository.findById(id).orElseThrow();
        authorRepository.deleteById(author.getId());
        log.info("Delete author {}",id);
        return modelMapper.map(author, AuthorDto.class);
    }

}
