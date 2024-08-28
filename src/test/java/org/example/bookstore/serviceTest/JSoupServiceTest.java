package org.example.bookstore.serviceTest;

import org.example.bookstore.service.JSoupService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class JSoupServiceTest {
//    @InjectMocks
//    private JSoupService jSoupService;
//    @Test
//    public void getDetailsTest() throws IOException {
//        var spy= Mockito.spy(jSoupService);
////        Document doc= (Document) Jsoup.connect(any());
////        Element select= doc.select((String) any()).getFirst();
//        verify(spy).getDetails();
////        verify(select).html();
//    }
}
