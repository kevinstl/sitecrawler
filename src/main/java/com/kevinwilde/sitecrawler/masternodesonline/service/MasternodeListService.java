package com.kevinwilde.sitecrawler.masternodesonline.service;

import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.model.MasternodesOnlineSupplement;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MasternodeListService {

    public static final String HTTPS_MASTERNODES_ONLINE = "https://masternodes.online/";

    @Autowired
    private DocumentFactory documentFactory;

    @Autowired
    private MasternodeRowService masternodeRowService;

    public void hello(){
        System.out.println("hello");
    }


    public List<MasternodesOnlineSupplement> extractMasternodeProfilesToMasternodeOnlineSupplements() {

        Document masternodeHomeHtml = documentFactory.getDocumentBasedOnUrl(HTTPS_MASTERNODES_ONLINE);
        Elements masternodeTrs = masternodeHomeHtml.select("table#masternodes_table > tbody > tr");

        List<MasternodesOnlineSupplement> masternodesOnlineSupplements = new ArrayList<>();

        masternodeTrs.forEach(masternodeTr-> masternodesOnlineSupplements.add(masternodeRowService.masternodeRowToMasternodeOnlineSupplementObject(masternodeTr)));

        return masternodesOnlineSupplements;
    }




}
