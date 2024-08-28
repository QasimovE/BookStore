package org.example.bookstore.config.clients;

import org.example.bookstore.dto.goodReadsDto.GoodReadsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "GoodReads", url = "https://goodreads12.p.rapidapi.com")
public interface GoodReadsClient {
    @GetMapping("/searchBooks")
    List<GoodReadsResponse> searchBook(
          @RequestHeader(name = "x-rapidapi-host") String host,
          @RequestHeader(name = "x-rapidapi-key") String key,
          @RequestParam(name = "keyword") String keyword,
          @RequestParam(name = "page") String page);
}
