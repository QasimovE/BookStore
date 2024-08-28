package org.example.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.example.bookstore.dto.AuthorDto;
import org.example.bookstore.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/createAuthor")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.createAuthor(authorDto);
    }

    @GetMapping("getAllAuthor")
    public List<AuthorDto> getAllAuthor() {
        return authorService.getAllAuthor();
    }

    @GetMapping("getAuthorByID")
    public AuthorDto getAuthorByID(@RequestParam Long id) {
        return authorService.getAuthorByID(id);
    }

    @PutMapping("updateAuthor")
    public AuthorDto updateAuthor(@RequestParam Long id, @RequestBody AuthorDto authorDto) {
        return authorService.updateAuthor(id, authorDto);
    }

    @DeleteMapping("deleteAuthor")
    public AuthorDto deleteAuthor(@RequestParam Long id) {
        return authorService.deleteAuthor(id);
    }


    @GetMapping("getAuthorByBook")
    public AuthorDto getAuthorByBook(@RequestParam String title) {
        return authorService.getAuthorByBook(title);
    }


}
