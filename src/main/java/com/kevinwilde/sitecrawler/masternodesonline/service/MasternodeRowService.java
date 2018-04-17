package com.kevinwilde.sitecrawler.masternodesonline.service;

import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.api.MasternodesOnlineSupplementApiClient;
import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.model.MasternodesOnlineSupplement;
import com.kevinwilde.sitecrawler.masternodesonline.factory.BearerTokenFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MasternodeRowService {

    @Autowired
    private MasternodeProfileService masternodeProfileService;

    @Autowired
    private MasternodeOnlineSupplementFactory masternodeOnlineSupplementFactory;

    @Autowired
    private MasternodesOnlineSupplementApiClient masternodesOnlineSupplementApiClient;

    @Autowired
    private BearerTokenFactory bearerTokenFactory;

    public MasternodesOnlineSupplement masternodeRowToMasternodeOnlineSupplementObject(Element masternodeTr) {

        Elements tds = masternodeTr.select("tr > td");

//        System.out.println(tds);

//        MasternodesOnlineSupplement masternodesOnlineSupplement = new MasternodesOnlineSupplement();
        MasternodesOnlineSupplement masternodesOnlineSupplement = masternodeOnlineSupplementFactory.build();

        masternodesOnlineSupplement.setCoin(tds.get(2).text());
        masternodesOnlineSupplement.setPrice(tds.get(3).text());
        masternodesOnlineSupplement.setChange(tds.get(4).text());
        masternodesOnlineSupplement.setVolume(tds.get(5).text());
        masternodesOnlineSupplement.setMarketcap(tds.get(6).text());
        masternodesOnlineSupplement.setRoi(tds.get(7).text());
        masternodesOnlineSupplement.setNodes(tds.get(8).text());
        masternodesOnlineSupplement.setNumberRequired(tds.get(9).text());
        masternodesOnlineSupplement.setMinimumWorth(tds.get(10).text());

        String masternodeProfileUrl = masternodeProfileService.extractMasternodeProfileUrl(masternodeTr);
        masternodesOnlineSupplement = masternodeProfileService.extractMasternodeProfileContent(masternodesOnlineSupplement, masternodeProfileUrl);

//        String bearerToken = System.getenv("BEARER_TOKEN");
        String bearerToken = bearerTokenFactory.build();
        masternodesOnlineSupplementApiClient.
                createMasternodesOnlineSupplementUsingPOST(bearerToken, masternodesOnlineSupplement);


        return masternodesOnlineSupplement;
    }





}
