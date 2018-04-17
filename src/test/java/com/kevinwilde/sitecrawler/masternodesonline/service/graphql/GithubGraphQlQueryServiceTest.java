package com.kevinwilde.sitecrawler.masternodesonline.service.graphql;

import com.kevinwilde.graphqljavaclient.GraphQlClient;
import com.kevinwilde.sitecrawler.masternodesonline.domain.githubInforesponse.GithubInfoResponse;
import com.kevinwilde.sitecrawler.masternodesonline.factory.GithubGraphQlQueryFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GithubGraphQlQueryServiceTest {

    @InjectMocks
    private GithubGraphQlQueryService classUnderTest;

    @Mock
    private GithubGraphQlQueryFactory githubGraphQlQueryFactory;

    @Mock
    private GraphQlClient graphQlClient;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private GithubInfoResponse githubInfoResponse;

    @Test
    public void retrieveMasternodeGithubTotalCommits_retrievesMasternodeGithubTotalCommits(){
        String repositoryOwner = "repositoryOwner";
        String repositoryName = "repositoryName";

        String query = "query";

        when(githubGraphQlQueryFactory.buildCommitsQuery(repositoryOwner, repositoryName)).thenReturn(query);
        when(graphQlClient.execute(GithubGraphQlQueryService.HTTPS_API_GITHUB_COM_GRAPHQL,
                query,
                GithubInfoResponse.class)).thenReturn(githubInfoResponse);


        Integer totalCommits = classUnderTest.retrieveMasternodeGithubTotalCommits(repositoryOwner, repositoryName);


        assertNotNull(totalCommits);

        verify(githubGraphQlQueryFactory).buildCommitsQuery(repositoryOwner, repositoryName);
        verify(graphQlClient).execute(GithubGraphQlQueryService.HTTPS_API_GITHUB_COM_GRAPHQL,
                query,
                GithubInfoResponse.class);
    }
}
