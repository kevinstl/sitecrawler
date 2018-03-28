package com.kevinwilde.sitecrawler.masternodesonline.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExtractMasternodeListServiceTest {

    @InjectMocks
    private ExtractMasternodeListService classUnderTest;

    @Mock
    private DocumentFactory documentFactory;

    @Mock
    private Document document;

    @Mock
    private Element element1;

    @Test
    public void extractMasterNodeList_extractsMasterNodeList() throws IOException {

        Elements expectedMasternodePageLinks = new Elements();
        expectedMasternodePageLinks.add(element1);

        when(documentFactory.getDocumentBasedOnUrl(anyString())).thenReturn(document);
        when(document.select(anyString())).thenReturn(expectedMasternodePageLinks);

        Elements masternodePageLinks = classUnderTest.extractMasternodeList();

        assertNotNull(masternodePageLinks);
        assertEquals(expectedMasternodePageLinks, masternodePageLinks);
    }

}
