package com.kevinwilde.sitecrawler.masternodesonline.service;

import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.api.MasternodesOnlineSupplementApiClient;
import com.cryptocurrencyservices.masternodessuplement.api.client.master_node_online_supplement.model.MasternodesOnlineSupplement;
import com.kevinwilde.sitecrawler.masternodesonline.domain.GithubInfo;
import com.kevinwilde.sitecrawler.masternodesonline.domain.githubInforesponse.Repository;
import com.kevinwilde.sitecrawler.masternodesonline.domain.githubInforesponse.RepositoryInfoResponse;
import com.kevinwilde.sitecrawler.masternodesonline.factory.DocumentFactory;
import com.kevinwilde.sitecrawler.masternodesonline.service.graphql.GithubGraphQlQueryService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.StringWriter;
import java.time.OffsetDateTime;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
    private OffsetDateTime createdAt = OffsetDateTime.parse("2018-03-06T20:42:58Z");
    private OffsetDateTime pushedAt = OffsetDateTime.parse("2018-04-18T21:36:33Z");

    @Before
    public void setup(){
        properties.setProperty("resource.loader", "file");
        properties.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init(properties);
    }

    @InjectMocks
    private MasternodeGithubService classUnderTest;

    @Mock
    private GithubGraphQlQueryService githubGraphQlQueryService;

    private String expectedRepositoryOwner = "apollondeveloper";
    private String expectedRepositoryName = "ApollonCoin";
    private Integer expectedTotalCommits = Integer.valueOf(5);

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private RepositoryInfoResponse repositoryInfoResponse;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Repository repository;

    @Test
    public void extractMasternodeGithubLink_extractsMasternodeGithubLink() {

        Document masternodeProfileXapDocument = buildMasternodeProfile();

        String masternodeGithubLink = classUnderTest.extractMasternodeGithubUrl(masternodeProfileXapDocument);

        assertNotNull(masternodeGithubLink);
        assertEquals(HTTPS_GITHUB_COM_APOLLONDEVELOPER_APOLLON_COIN, masternodeGithubLink);
    }

    @Test
    public void extractMasternodeGithubInfo_extractsMasternodeGithubInfo(){
        Document masternodeProfileXapDocument = buildMasternodeProfile();

        GithubInfo githubInfo = classUnderTest.extractMasternodeGithubInfo(masternodeProfileXapDocument);

        //https://github.com/apollondeveloper/ApollonCoin/

        assertNotNull(githubInfo);
        assertNotNull(githubInfo.getRepositoryOwner());

        assertEquals(expectedRepositoryOwner, githubInfo.getRepositoryOwner());
        assertNotNull(githubInfo.getRepositoryName());

        assertEquals(expectedRepositoryName, githubInfo.getRepositoryName());
    }

    private Document buildMasternodeProfile() {
        Template masternodeProfileXapTemplate = velocityEngine.getTemplate("templates/masternodeProfileXap.vm");

        masternodeProfileXapTemplate.merge( velocityContext, stringWriter );
        String masternodeProfileXapHtml = stringWriter.toString();

        return Jsoup.parse(masternodeProfileXapHtml, "", Parser.xmlParser());
    }

    @Test
    public void extractMasternodeGithubContent_extractsMasternodeGithubContent() {

        when(repositoryInfoResponse.getData().getRepository()).thenReturn(repository);
        when(repository.getDefaultBranchRef().getTarget().getHistory().getTotalCount()).thenReturn(expectedTotalCommits);
        when(repository.getCreatedAt()).thenReturn(createdAt);
        when(repository.getPushedAt()).thenReturn(pushedAt);

        Document masternodeProfile = buildMasternodeProfile();

        MasternodesOnlineSupplement existingMasternodesOnlineSupplement = new MasternodesOnlineSupplement();



        when(githubGraphQlQueryService.
                retrieveRepositoryInfoResponse(expectedRepositoryOwner, expectedRepositoryName)).
                thenReturn(repositoryInfoResponse);


        MasternodesOnlineSupplement masternodesOnlineSupplement =
                classUnderTest.extractMasternodeGithubContent(
                        masternodeProfile,
                        existingMasternodesOnlineSupplement
                );


        assertNotNull(masternodesOnlineSupplement);
        assertNotNull(masternodesOnlineSupplement.getGithubCommits());
        assertEquals(expectedTotalCommits, masternodesOnlineSupplement.getGithubCommits());

        assertNotNull(masternodesOnlineSupplement.getCreatedAt());
        assertEquals(createdAt, masternodesOnlineSupplement.getCreatedAt());
        assertNotNull(masternodesOnlineSupplement.getPushedAt());
        assertEquals(pushedAt, masternodesOnlineSupplement.getPushedAt());

//        verify(masternodesOnlineSupplementApiClient).createMasternodesOnlineSupplementUsingPOST("", masternodesOnlineSupplement);
    }


    @Test
    public void extractMasternodeGithubContent_handlesNullRepositoryInfoResponse() {


        Document masternodeProfile = buildMasternodeProfile();

        MasternodesOnlineSupplement existingMasternodesOnlineSupplement = new MasternodesOnlineSupplement();



        when(githubGraphQlQueryService.
                retrieveRepositoryInfoResponse(expectedRepositoryOwner, expectedRepositoryName)).
                thenReturn(null);


        MasternodesOnlineSupplement masternodesOnlineSupplement =
                classUnderTest.extractMasternodeGithubContent(
                        masternodeProfile,
                        existingMasternodesOnlineSupplement
                );


        assertNull(masternodesOnlineSupplement.getGithubCommits());

//        verify(masternodesOnlineSupplementApiClient).createMasternodesOnlineSupplementUsingPOST("", masternodesOnlineSupplement);
    }



}
