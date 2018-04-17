package com.kevinwilde.sitecrawler.masternodesonline.service;

import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.api.MasternodesOnlineSupplementApiClient;
import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.model.MasternodesOnlineSupplement;
import com.kevinwilde.sitecrawler.masternodesonline.domain.GithubInfo;
import com.kevinwilde.sitecrawler.masternodesonline.factory.DocumentFactory;
import com.kevinwilde.sitecrawler.masternodesonline.service.graphql.GithubGraphQlQueryService;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class MasternodeGithubService {

    public static final String HTTPS_GITHUB_COM = "https://github.com/";
    @Autowired
    private DocumentFactory documentFactory;

    @Autowired
    private GithubGraphQlQueryService githubGraphQlQueryService;

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


    public GithubInfo extractMasternodeGithubInfo(Document masternodeProfile) {
        String masternodeGithubUrl = extractMasternodeGithubUrl(masternodeProfile);



        int beginIndexRepositoryOwner = masternodeGithubUrl.lastIndexOf(HTTPS_GITHUB_COM) + HTTPS_GITHUB_COM.length();
        int endIndexRepositoryOwner = masternodeGithubUrl.indexOf("/", beginIndexRepositoryOwner);

        String repositoryOwner = masternodeGithubUrl.substring(beginIndexRepositoryOwner, endIndexRepositoryOwner);

        int beginIndexRepositoryName = endIndexRepositoryOwner + 1;
        int endIndexRepositoryName = masternodeGithubUrl.indexOf("/", beginIndexRepositoryName);

//        String repositoryName = masternodeGithubUrl.substring(beginIndexRepositoryName, endIndexRepositoryName);
        String repositoryName = masternodeGithubUrl.substring(beginIndexRepositoryName);
        repositoryName = repositoryName.replace("/", "");

        GithubInfo githubInfo = new GithubInfo();
        githubInfo.setRepositoryOwner(repositoryOwner);
        githubInfo.setRepositoryName(repositoryName);

        return githubInfo;
    }


    public MasternodesOnlineSupplement extractMasternodeGithubContent(Document masternodeProfile, MasternodesOnlineSupplement masternodesOnlineSupplement){

        GithubInfo githubInfo = extractMasternodeGithubInfo(masternodeProfile);


        Integer githubCommits = githubGraphQlQueryService.
                retrieveMasternodeGithubTotalCommits(githubInfo.getRepositoryOwner(), githubInfo.getRepositoryName());

        masternodesOnlineSupplement.setGithubCommits(githubCommits);

//        if(masternodeGithubUrlDocument != null){
//            githubCommitsText = masternodeGithubUrlDocument.select("li.commits > a > span").text();
////            System.out.println(githubCommits);
//        }
//        if(!StringUtils.isEmpty(githubCommitsText)){
//            githubCommitsText = githubCommitsText.replace(",", "");
//            githubCommits = new Integer(githubCommitsText);
//            masternodesOnlineSupplement.setGithubCommits(githubCommits);
//        }
//
////        System.out.println("masternodeGithubUrlDocument: " + masternodeGithubUrlDocument);
//        System.out.println("masternodesOnlineSupplement: " + masternodesOnlineSupplement);
//
//        String bearerToken = System.getenv("BEARER_TOKEN");
//        masternodesOnlineSupplementApiClient.createMasternodesOnlineSupplementUsingPOST(
//                bearerToken,
//                masternodesOnlineSupplement);

        return masternodesOnlineSupplement;
    }


}
