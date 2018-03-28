package com.kevinwilde.sitecrawler.masternodesonline.service;

import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MasternodesOnlineSupplementDocumentService {

    @Autowired
    private ExtractMasternodeListService extractMasternodeListService;

    @Autowired
    private HtmlMarshaller htmlMarshaller;

    public void populate() throws IOException {
        Elements masternodeList = extractMasternodeListService.extractMasternodeList();

        System.out.println("masterNodeList: " + masternodeList);

        htmlMarshaller.masternodeRowsToMasternodeOnlineSupplements(masternodeList);
    }
}
