package org.example.bookstore.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class JSoupService {
    @Scheduled(fixedRate = 10000)
    public void getDetails() throws IOException {
        Document doc= Jsoup.connect("https://www.goodreads.com/book/show/39832183-the-guernsey-literary-and-potato-peel-pie-society").get();
        Elements select = doc.select("#__next > div.PageFrame.PageFrame--siteHeaderBanner > main > div.BookPage__gridContainer > div.BookPage__rightColumn > div.BookPage__mainContent > div.BookPageMetadataSection > div.BookDetails > div > span:nth-child(1) > span > div > p:nth-child(1)");
        log.info("{}", select.getFirst().html());
    }
}
