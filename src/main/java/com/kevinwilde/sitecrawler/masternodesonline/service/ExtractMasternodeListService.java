package com.kevinwilde.sitecrawler.masternodesonline.service;

import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.model.MasternodesOnlineSupplement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
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

//        masternodeList.forEach(item-> extractMasternodeProfile(item));

        return masternodeList;
    }

    public List<MasternodesOnlineSupplement> extractMasternodeProfilesToMasternodeOnlineSupplements(Elements masternodeTrs) {



        List<MasternodesOnlineSupplement> masternodesOnlineSupplements = new ArrayList<>();

//        masternodeTrs.forEach(item-> masternodesOnlineSupplements.add(masternodeRowToMasternodeOnlineSupplementObject(item)));
        masternodeTrs.forEach(item-> masternodesOnlineSupplements.add(extractMasternodeProfile(item)));
//        masternodeTrs.forEach(item-> extractMasternodeProfile(item));

        return masternodesOnlineSupplements;
    }

    public MasternodesOnlineSupplement extractMasternodeProfile(Element masternodeTr) {

        Elements masternodePageLinks = masternodeTr.select("tr > td > strong > a");

        System.out.println(masternodeTr.text());
//        System.out.println(masternodePageLink.attr("href"));

        Document doc = null;
        try {
            String profileHref = masternodePageLinks.attr("href");
            doc = Jsoup.connect("https://masternodes.online/" + profileHref).get();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new MasternodesOnlineSupplement();
    }

    public String extractMasternodeGithubUrl(Document masternodeProfile){
        Elements githubLinks = masternodeProfile.select("a:contains(github)");
        String githubLink = githubLinks.get(0).attr("href");
        return githubLink;
    }

    public MasternodesOnlineSupplement extractMasternodeGithubContent(String masternodeGithubUrl){
        Document masternodeGithubUrlDocument = documentFactory.getDocumentBasedOnUrl(masternodeGithubUrl);

        String githubCommitsText = null;
        Integer githubCommits = null;

        MasternodesOnlineSupplement masternodesOnlineSupplement = new MasternodesOnlineSupplement();

        if(masternodeGithubUrlDocument != null){
            githubCommitsText = masternodeGithubUrlDocument.select("li.commits > a > span").text();
            System.out.println(githubCommits);
        }
        if(githubCommitsText != null){
            githubCommits = new Integer(githubCommitsText);
            masternodesOnlineSupplement.setGithubCommits(githubCommits);
        }
        return masternodesOnlineSupplement;
    }
}
