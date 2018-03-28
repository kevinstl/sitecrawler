package com.kevinwilde.sitecrawler.masternodesonline.service;

import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.model.MasternodesOnlineSupplement;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HtmlMarshaller {


    public MasternodesOnlineSupplement masternodeRowToMasternodeOnlineSupplementObject(Element element) {

        Elements tds = element.select("tr > td");

        System.out.println(tds);

        MasternodesOnlineSupplement masternodesOnlineSupplement = new MasternodesOnlineSupplement();

        masternodesOnlineSupplement.setCoin(tds.get(2).text());
        masternodesOnlineSupplement.setPrice(tds.get(3).text());
        masternodesOnlineSupplement.setChange(tds.get(4).text());
        masternodesOnlineSupplement.setVolume(tds.get(5).text());
        masternodesOnlineSupplement.setMarketcap(tds.get(6).text());
        masternodesOnlineSupplement.setRoi(tds.get(7).text());
        masternodesOnlineSupplement.setNodes(tds.get(8).text());
        masternodesOnlineSupplement.setNumberRequired(tds.get(9).text());
        masternodesOnlineSupplement.setMinimumWorth(tds.get(10).text());

        return masternodesOnlineSupplement;
    }

    public List<MasternodesOnlineSupplement> masternodeRowsToMasternodeOnlineSupplements(Elements masternodeTrs) {

        List<MasternodesOnlineSupplement> masternodesOnlineSupplements = new ArrayList<>();

        masternodeTrs.forEach(item-> masternodesOnlineSupplements.add(masternodeRowToMasternodeOnlineSupplementObject(item)));

        return masternodesOnlineSupplements;
    }
}
