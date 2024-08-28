package org.example.bookstore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bookstore.dto.BookDto;
import org.example.bookstore.model.Book;
import org.example.bookstore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private Book recBook;

    @Scheduled(fixedRate = 5000)
    public void recommendationBook(){
        List<Book> all = bookRepository.findAll();
        if(!all.isEmpty()){
            Random random=new Random();
            int randomIndex = random.nextInt(0, all.size());
            recBook = all.get(randomIndex);
            log.info("Send recommendate book");
        } else {
            recBook=new Book();
            log.info("New book");
        }


    }

    public BookDto sendBook(){
        log.info("Send book");
        return modelMapper.map(recBook,BookDto.class);
    }

}
