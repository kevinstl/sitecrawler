package com.kevinwilde.sitecrawler.masternodesonline.factory;

import org.springframework.stereotype.Component;

@Component
public class BearerTokenFactory {

    public String build(){
        return "Bearer " + System.getenv("BEARER_TOKEN");
    }

}
