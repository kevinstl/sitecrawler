package com.kevinwilde.sitecrawler.masternodesonline;

import com.kevinwilde.sitecrawler.masternodesonline.config.MasternodesOnlineConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MasternodesOnlineMain {

    public static void main(String[] args) {

        ApplicationContext context
                = new AnnotationConfigApplicationContext(MasternodesOnlineConfig.class);

        MasternodesOnlineConfig masternodesOnlineConfig = context.getBean(MasternodesOnlineConfig.class);


        masternodesOnlineConfig.start();

    }
}
