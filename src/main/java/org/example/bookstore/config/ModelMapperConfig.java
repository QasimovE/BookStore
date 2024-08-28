package org.example.bookstore.config;

import org.example.bookstore.dto.BookDto;
import org.example.bookstore.model.Author;
import org.example.bookstore.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper (){
        return new ModelMapper();
    }
}
