package com.kevinwilde.sitecrawler.masternodesonline.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class ExtractMasterNodeListServiceTest {

    @Mock
    private ExtractMasterNodeListService classUnderTest;

    @Test
    public void showList() throws IOException {
        classUnderTest.showList();
    }

}
