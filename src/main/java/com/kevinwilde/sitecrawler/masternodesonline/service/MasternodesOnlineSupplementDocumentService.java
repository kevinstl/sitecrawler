package com.kevinwilde.sitecrawler.masternodesonline.service;

import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.model.MasternodesOnlineSupplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MasternodesOnlineSupplementDocumentService {

    @Autowired
    private MasternodeListService masternodeListService;

    @Autowired
    private MasternodeRowService masternodeRowService;

    public void populate() throws IOException {
        List<MasternodesOnlineSupplement> masternodesOnlineSupplements = masternodeListService.extractMasternodeProfilesToMasternodeOnlineSupplements();

        System.out.println("masternodesOnlineSupplements: " + masternodesOnlineSupplements);

//        htmlMarshaller.masternodeRowsToMasternodeOnlineSupplements(masternodeList);
    }
}
