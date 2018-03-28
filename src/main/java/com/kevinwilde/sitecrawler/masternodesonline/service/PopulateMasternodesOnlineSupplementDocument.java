package com.kevinwilde.sitecrawler.masternodesonline.service;

import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PopulateMasternodesOnlineSupplementDocument {

    @Autowired
    private ExtractMasternodeListService extractMasternodeListService;

    public void populate() throws IOException {
        Elements masternodeList = extractMasternodeListService.extractMasternodeList();

        System.out.println("masterNodeList: " + masternodeList);
    }
}
