package com.kevinwilde.sitecrawler.masternodesonline.service;

import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MasternodesOnlineSupplementDocumentServiceTest {

    @InjectMocks
    private MasternodesOnlineSupplementDocumentService classUnderTest;

    @Mock
    private ExtractMasternodeListService extractMasternodeListService;

    @Mock
    private HtmlMarshaller htmlMarshaller;

    @Mock
    private Elements masternodeList;

    @Test
    public void populateExtractsList() throws IOException {

        when(extractMasternodeListService.extractMasternodeList()).thenReturn(masternodeList);

        classUnderTest.populate();

        verify(extractMasternodeListService).extractMasternodeList();
        verify(htmlMarshaller).masternodeRowsToMasternodeOnlineSupplements(masternodeList);
    }

}
