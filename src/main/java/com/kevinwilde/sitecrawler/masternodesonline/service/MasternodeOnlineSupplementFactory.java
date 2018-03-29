package com.kevinwilde.sitecrawler.masternodesonline.service;

import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.model.MasternodesOnlineSupplement;
import org.springframework.stereotype.Component;

@Component
public class MasternodeOnlineSupplementFactory {

    public MasternodesOnlineSupplement build(){
        return new MasternodesOnlineSupplement();
    }
}
