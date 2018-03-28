package com.kevinwilde.sitecrawler.masternodesonline.service;

import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.model.MasternodesOnlineSupplement;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class HtmlMarshaller {


    public MasternodesOnlineSupplement masternodeRowToMasternodeOnlineSupplementObject(Element element) {

        Elements tds = element.select("tr > td");

        System.out.println(tds);

        MasternodesOnlineSupplement masternodesOnlineSupplement = new MasternodesOnlineSupplement();

        masternodesOnlineSupplement.setCoin(tds.get(2).text());

        return masternodesOnlineSupplement;
    }
}
