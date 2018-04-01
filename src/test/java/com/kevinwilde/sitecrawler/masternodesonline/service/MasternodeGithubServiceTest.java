package com.kevinwilde.sitecrawler.masternodesonline.service;

import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.api.MasternodesOnlineSupplementApiClient;
import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.model.MasternodesOnlineSupplement;
import com.kevinwilde.graphqljavaclient.Caller;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.StringWriter;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MasternodeGithubServiceTest {


    public static final String HTTPS_MASTERNODES_ONLINE_CURRENCIES_XAP = "https://masternodes.online//currencies/XAP/";
    public static final String HTTPS_GITHUB_COM_APOLLONDEVELOPER_APOLLON_COIN = "https://github.com/apollondeveloper/ApollonCoin/";

    @Mock
    private DocumentFactory documentFactory;

    @Mock
    private MasternodesOnlineSupplementApiClient masternodesOnlineSupplementApiClient;

    private VelocityEngine velocityEngine = new VelocityEngine();
    private VelocityContext velocityContext = new VelocityContext();
    private StringWriter stringWriter = new StringWriter();
    private Properties properties = new Properties();

    @Before
    public void setup(){
        properties.setProperty("resource.loader", "file");
        properties.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init(properties);
    }

    @InjectMocks
    private MasternodeGithubService classUnderTest;

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
    public void extractMasternodeGithubLink_extractsMasternodeGithubLink() {

        Document masternodeProfileXapDocument = buildMasternodeProfile();

        String masternodeGithubLink = classUnderTest.extractMasternodeGithubUrl(masternodeProfileXapDocument);

        assertNotNull(masternodeGithubLink);
        assertEquals(HTTPS_GITHUB_COM_APOLLONDEVELOPER_APOLLON_COIN, masternodeGithubLink);
    }

    private Document buildMasternodeProfile() {
        Template masternodeProfileXapTemplate = velocityEngine.getTemplate("templates/masternodeProfileXap.vm");

        masternodeProfileXapTemplate.merge( velocityContext, stringWriter );
        String masternodeProfileXapHtml = stringWriter.toString();

        return Jsoup.parse(masternodeProfileXapHtml, "", Parser.xmlParser());
    }

    @Test
    public void extractMasternodeGithubContent_extractsMasternodeGithubContent() {

        MasternodesOnlineSupplement existingMasternodesOnlineSupplement = new MasternodesOnlineSupplement();

        Template masternodeProfileXapTemplate = velocityEngine.getTemplate("templates/masternodeGithubContentXap.vm");

        masternodeProfileXapTemplate.merge( velocityContext, stringWriter );
        String masternodeGithubContentXapHtml = stringWriter.toString();
        Document masternodeProfileXapDocument = Jsoup.parse(masternodeGithubContentXapHtml, "", Parser.xmlParser());

        when(documentFactory.getDocumentBasedOnUrl(anyString())).thenReturn(masternodeProfileXapDocument);


        MasternodesOnlineSupplement masternodesOnlineSupplement =
                classUnderTest.extractMasternodeGithubContent(existingMasternodesOnlineSupplement, HTTPS_GITHUB_COM_APOLLONDEVELOPER_APOLLON_COIN);


        assertNotNull(masternodesOnlineSupplement);
        assertNotNull(masternodesOnlineSupplement.getGithubCommits());
        Integer expectedGithubCommits = 5;
        assertEquals(expectedGithubCommits, masternodesOnlineSupplement.getGithubCommits());

        verify(masternodesOnlineSupplementApiClient).createMasternodesOnlineSupplementUsingPOST("", masternodesOnlineSupplement);
    }



}
