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
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class HtmlMarshallerTest {


    @InjectMocks
    private HtmlMarshaller classUnderTest;

    private String masternodeTableRow =
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
//    @Mock
//    private Element element = Element.html


    @Test
    public void masternodeRowToMasternodeOnlineSupplementObject(){

//        Document document = Jsoup.parseBodyFragment(masternodeTableRow);

//        element.html(masternodeTableRow);


        Document doc = Jsoup.parse(masternodeTableRow, "", Parser.xmlParser());
        Element element = doc;


//        element.text("");


        MasternodesOnlineSupplement masternodesOnlineSupplement = classUnderTest.masternodeRowToMasternodeOnlineSupplementObject(element);

        System.out.println(masternodesOnlineSupplement);

        assertNotNull(masternodesOnlineSupplement);
        assertNotNull(masternodesOnlineSupplement.getCoin());
        assertEquals("Apollon Coin (XAP)", masternodesOnlineSupplement.getCoin());

    }

}
