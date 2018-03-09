package com.kevinwilde.sitecrawler.masternodesonline.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ExtractMasterNodeListService {

    public void hello(){
        System.out.println("hello");
    }

    public void showList() throws IOException {
        Document doc = Jsoup.connect("https://masternodes.online/").get();
//        doc.select("table#masternodes_table > tbody > tr > td > strong > a").forEach(System.out::println);
//        doc.select("table#masternodes_table > tbody > tr > td > strong > a").
//                forEach(item->System.out.println(item.attr("href")));
        List<Element> masternodePageLinks = doc.select("table#masternodes_table > tbody > tr > td > strong > a");

        masternodePageLinks.forEach(item->showPageLinkContent(item));

        System.out.println();
    }

    private void showPageLinkContent(Element masternodePageLink) {

        System.out.println(masternodePageLink.attr("href"));

        Document doc = null;
        try {
            doc = Jsoup.connect("https://masternodes.online/" + masternodePageLink.attr("href")).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        doc.select("a:contains(github)").forEach(System.out::println);

    }
}
