package com.kevinwilde.sitecrawler.masternodesonline.config;

import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.ClientConfiguration;
import com.kevinwilde.graphqljavaclient.config.GraphqlJavaClientConfig;
import com.kevinwilde.sitecrawler.masternodesonline.service.MasternodeListService;
import com.kevinwilde.sitecrawler.masternodesonline.service.MasternodesOnlineSupplementDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages = "com.kevinwilde.sitecrawler.masternodesonline")
@Import({ClientConfiguration.class, GraphqlJavaClientConfig.class})
public class MasternodesOnlineConfig {

    @Autowired
    private MasternodeListService masternodeListService;

    @Autowired
    private MasternodesOnlineSupplementDocumentService masternodesOnlineSupplementDocumentService;

    public void start() throws IOException {
        masternodesOnlineSupplementDocumentService.populate();
    }

}
