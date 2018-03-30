package com.kevinwilde.sitecrawler.masternodesonline.service;

import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.api.MasternodesOnlineSupplementApiClient;
import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.model.MasternodesOnlineSupplement;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class MasternodeGithubService {

    @Autowired
    private DocumentFactory documentFactory;

    @Autowired
    private MasternodesOnlineSupplementApiClient masternodesOnlineSupplementApiClient;

    public String extractMasternodeGithubUrl(Document masternodeProfile){
        Elements githubLinks = masternodeProfile.select("a:contains(github)");
        String githubLink = null;
        if(!CollectionUtils.isEmpty(githubLinks)){
            githubLink = githubLinks.get(0).attr("href");
        }
        return githubLink;
    }

    public MasternodesOnlineSupplement extractMasternodeGithubContent(MasternodesOnlineSupplement masternodesOnlineSupplement, String masternodeGithubUrl){

        if(StringUtils.isEmpty(masternodeGithubUrl)){
            return masternodesOnlineSupplement;
        }

        Document masternodeGithubUrlDocument = documentFactory.getDocumentBasedOnUrl(masternodeGithubUrl);

        String githubCommitsText = null;
        Integer githubCommits = null;

        if(masternodeGithubUrlDocument != null){
            githubCommitsText = masternodeGithubUrlDocument.select("li.commits > a > span").text();
//            System.out.println(githubCommits);
        }
        if(!StringUtils.isEmpty(githubCommitsText)){
            githubCommitsText = githubCommitsText.replace(",", "");
            githubCommits = new Integer(githubCommitsText);
            masternodesOnlineSupplement.setGithubCommits(githubCommits);
        }

//        System.out.println("masternodeGithubUrlDocument: " + masternodeGithubUrlDocument);
        System.out.println("masternodesOnlineSupplement: " + masternodesOnlineSupplement);

        String bearerToken = System.getenv("BEARER_TOKEN");
        masternodesOnlineSupplementApiClient.createMasternodesOnlineSupplementUsingPOST(
                bearerToken,
                masternodesOnlineSupplement);

        return masternodesOnlineSupplement;
    }
}
