package com.kevinwilde.sitecrawler.masternodesonline.service.graphql;

import com.kevinwilde.graphqljavaclient.Caller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GithubGraphQlServiceTest {

//    @InjectMocks
//    private GithubGraphQlServiceTest

    @Mock
    private Caller caller;

    @Test
    public void retrieveMasternodeGithubTotalCommits_retrievesMasternodeGithubTotalCommits(){
        String repositoryOwner = "repositoryOwner";
        String repositoryName = "repositoryName";

//        Integer totalCommits = classUnderTest.retrieveMasternodeGithubTotalCommits(repositoryOwner, repositoryName);

//        verify(caller).execute();
    }
}
