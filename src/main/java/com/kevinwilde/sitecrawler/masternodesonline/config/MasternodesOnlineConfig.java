package com.kevinwilde.sitecrawler.masternodesonline.config;

import com.kevinwilde.sitecrawler.masternodesonline.service.ExtractMasterNodeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages = "com.kevinwilde.sitecrawler.masternodesonline")
public class MasternodesOnlineConfig {

    @Autowired
    private ExtractMasterNodeListService extractMasterNodeListService;

    public void start() throws IOException {
        extractMasterNodeListService.showList();
    }

}
