package com.kevinwilde.sitecrawler.masternodesonline.service;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ExtractMasternodeListService {

    @Autowired
    private DocumentFactory documentFactory;

    public void hello(){
        System.out.println("hello");
    }

    public Elements extractMasternodeList() throws IOException {
        Document masternodeHomeHtml = documentFactory.getDocumentBasedOnUrl("https://masternodes.online/");
//        Elements masternodePageLinks = document.select("table#masternodes_table > tbody > tr > td > strong > a");
        Elements masternodeList = masternodeHomeHtml.select("table#masternodes_table > tbody > tr");


//        System.out.println(masternodeHomeHtml);
        System.out.println(masternodeList);

//        masternodeList.forEach(item->showPageLinkContent(item));

        return masternodeList;
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

        if(doc != null){
            System.out.println(doc.select("li.commits > a > span").text());
        }

    }
}
