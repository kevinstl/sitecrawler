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
        List<Element> masternodePageLinks = doc.select("table#masternodes_table > tbody > tr > td > strong > a");

        masternodePageLinks.forEach(item->showPageLinkContent(item));

        System.out.println();
    }

    private void showPageLinkContent(Element masternodePageLink) {

        System.out.println(masternodePageLink.text());
//        System.out.println(masternodePageLink.attr("href"));

        Document doc = null;
        try {
            doc = Jsoup.connect("https://masternodes.online/" + masternodePageLink.attr("href")).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Element> githubLinks = doc.select("a:contains(github)");
        githubLinks.forEach(item->showGithubPageContent(item));

    }

    private void showGithubPageContent(Element githubPageLink){
        Document doc = null;
        try {
            doc = Jsoup.connect(githubPageLink.attr("href")).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(doc.select("li.commits > a > span").text());

    }
}
