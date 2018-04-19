package com.kevinwilde.sitecrawler.masternodesonline.service;

import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.model.MasternodesOnlineSupplement;
import com.kevinwilde.sitecrawler.masternodesonline.factory.DocumentFactory;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MasternodeProfileService {

    @Autowired
    private DocumentFactory documentFactory;

    @Autowired
    private MasternodeGithubService masternodeGithubService;

    public String extractMasternodeProfileUrl(Element masternodeTr){
        Elements masternodePageLinks = masternodeTr.select("tr > td > strong > a");
        String profileHref = masternodePageLinks.attr("href");
        String masternodeProfileUrl = "https://masternodes.online" + profileHref;
        return masternodeProfileUrl;
    }


    public MasternodesOnlineSupplement extractMasternodeProfileContent(
            MasternodesOnlineSupplement masternodesOnlineSupplement,
            String masternodeProfileUrl) {

        Document masternodeProfileContentDocument = documentFactory.getDocumentBasedOnUrl(masternodeProfileUrl);

        String masternodeGithubUrl = masternodeGithubService.extractMasternodeGithubUrl(masternodeProfileContentDocument);

        if(StringUtils.isNotBlank(masternodeGithubUrl)){
            masternodesOnlineSupplement.setGithubUrl(masternodeGithubUrl);
            masternodesOnlineSupplement = masternodeGithubService.extractMasternodeGithubContent(masternodeProfileContentDocument, masternodesOnlineSupplement);
        }

        return masternodesOnlineSupplement;
    }
}
