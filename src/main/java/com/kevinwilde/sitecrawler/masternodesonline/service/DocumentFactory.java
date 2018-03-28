package com.kevinwilde.sitecrawler.masternodesonline.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DocumentFactory {

    public Document getDocumentBasedOnUrl(String url){

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }
}
