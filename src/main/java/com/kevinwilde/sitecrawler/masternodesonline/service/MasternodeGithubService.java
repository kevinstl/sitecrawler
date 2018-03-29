package com.kevinwilde.sitecrawler.masternodesonline.service;

import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.model.MasternodesOnlineSupplement;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class MasternodeGithubService {

    @Autowired
    private DocumentFactory documentFactory;

    public String extractMasternodeGithubUrl(Document masternodeProfile){
        Elements githubLinks = masternodeProfile.select("a:contains(github)");
        String githubLink = null;
        if(!CollectionUtils.isEmpty(githubLinks)){
            githubLink = githubLinks.get(0).attr("href");
        }
        return githubLink;
    }

    public MasternodesOnlineSupplement extractMasternodeGithubContent(MasternodesOnlineSupplement masternodesOnlineSupplement, String masternodeGithubUrl){
        Document masternodeGithubUrlDocument = documentFactory.getDocumentBasedOnUrl(masternodeGithubUrl);

        String githubCommitsText = null;
        Integer githubCommits = null;

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
