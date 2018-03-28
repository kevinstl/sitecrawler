package com.kevinwilde.sitecrawler.masternodesonline.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PopulateMasternodesOnlineSupplementDocumentTest {

    @InjectMocks
    private PopulateMasternodesOnlineSupplementDocument classUnderTest;

    @Mock
    private ExtractMasternodeListService extractMasternodeListService;

    @Test
    public void populateExtractsList() throws IOException {
        classUnderTest.populate();

        verify(extractMasternodeListService).extractMasternodeList();
    }

}
