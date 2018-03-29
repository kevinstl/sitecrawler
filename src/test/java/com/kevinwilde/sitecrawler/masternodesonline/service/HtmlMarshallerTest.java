package com.kevinwilde.sitecrawler.masternodesonline.service;

import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.model.MasternodesOnlineSupplement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class HtmlMarshallerTest {


    @InjectMocks
    private HtmlMarshaller classUnderTest;

    private String masternodeTableRowXap =
            "<tr> \n" +
            " <td class=\"control\" style=\"width:1px\"></td> \n" +
            " <td style=\"width:4px;\"><img src=\"coin_image/XAP.png?v=1\" height=\"16\" title=\"Apollon Coin masternode\" alt=\"Apollon Coin masternode\">&nbsp;&nbsp;&nbsp;</td> \n" +
            " <td style=\"width:100px\"><strong><a href=\"/currencies/XAP/\" title=\"Apollon Coin masternode detailed stats\">Apollon Coin (XAP)</a></strong></td> \n" +
            " <td style=\"width:145px\"><span title=\"0.103719\"></span>$0.1037</td> \n" +
            " <td style=\"width:70px\"><span class=\"text-danger\">-99.97 %</span></td> \n" +
            " <td style=\"width:120px\"><span title=\"3128.43\"></span>$3,128</td> \n" +
            " <td style=\"width:130px\"><span title=\"3452271\"></span>$3,452,271</td> \n" +
            " <td style=\"width:100px\"><strong><span title=\"1491.00\" class=\"text-info\">1491.00%</span></strong></td> \n" +
            " <td style=\"width:80px\"><span title=\"737\"></span>737</td> \n" +
            " <td style=\"width:100px\"><span title=\"25000\">25,000</span></td> \n" +
            " <td style=\"width:100px\"><span title=\"2592.975\"></span>$2,593</td> \n" +
            "</tr>";

    private String masternodeTableRowFrm =
            "<tr> \n"+
            " <td class=\"control\" style=\"width:1px\"></td> \n"+
            " <td style=\"width:4px;\"><img src=\"coin_image/FRM.png?v=1\" height=\"16\" title=\"Ferrum Coin masternode\" alt=\"Ferrum Coin masternode\">&nbsp;&nbsp;&nbsp;</td> \n"+
            " <td style=\"width:100px\"><strong><a href=\"/currencies/FRM/\" title=\"Ferrum Coin masternode detailed stats\">Ferrum Coin (FRM)</a></strong></td> \n"+
            " <td style=\"width:145px\"><span title=\"0.082955\"></span>$0.0830</td> \n"+
            " <td style=\"width:70px\"><span class=\"text-success\">159.02 %</span></td> \n"+
            " <td style=\"width:120px\"><span title=\"1947.96\"></span>$1,948</td> \n"+
            " <td style=\"width:130px\"><span title=\"105572\"></span>$105,572</td> \n"+
            " <td style=\"width:100px\"><strong><span title=\"965.26\" class=\"text-info\">965.26%</span></strong></td> \n"+
            " <td style=\"width:80px\"><span title=\"211\"></span>211</td> \n"+
            " <td style=\"width:100px\"><span title=\"2500\">2,500</span></td> \n"+
            " <td style=\"width:100px\"><span title=\"207.3875\"></span>$207</td> \n"+
            "</tr>";

    @Test
    public void masternodeRowToMasternodeOnlineSupplementObject(){

        Document doc = Jsoup.parse(masternodeTableRowXap, "", Parser.xmlParser());
        Element element = doc;


        MasternodesOnlineSupplement masternodesOnlineSupplement = classUnderTest.masternodeRowToMasternodeOnlineSupplementObject(element);

        System.out.println(masternodesOnlineSupplement);

        assertNotNull(masternodesOnlineSupplement);
        assertNotNull(masternodesOnlineSupplement.getCoin());
        assertEquals("Apollon Coin (XAP)", masternodesOnlineSupplement.getCoin());
        assertNotNull(masternodesOnlineSupplement.getPrice());
        assertEquals("$0.1037", masternodesOnlineSupplement.getPrice());
        assertNotNull(masternodesOnlineSupplement.getChange());
        assertEquals("-99.97 %", masternodesOnlineSupplement.getChange());
        assertNotNull(masternodesOnlineSupplement.getVolume());
        assertEquals("$3,128", masternodesOnlineSupplement.getVolume());
        assertNotNull(masternodesOnlineSupplement.getMarketcap());
        assertEquals("$3,452,271", masternodesOnlineSupplement.getMarketcap());
        assertNotNull(masternodesOnlineSupplement.getRoi());
        assertEquals("1491.00%", masternodesOnlineSupplement.getRoi());
        assertNotNull(masternodesOnlineSupplement.getNodes());
        assertEquals("737", masternodesOnlineSupplement.getNodes());
        assertNotNull(masternodesOnlineSupplement.getNumberRequired());
        assertEquals("25,000", masternodesOnlineSupplement.getNumberRequired());
        assertNotNull(masternodesOnlineSupplement.getMinimumWorth());
        assertEquals("$2,593", masternodesOnlineSupplement.getMinimumWorth());

    }




}
