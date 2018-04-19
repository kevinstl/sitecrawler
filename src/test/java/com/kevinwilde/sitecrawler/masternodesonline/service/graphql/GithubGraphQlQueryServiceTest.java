package com.kevinwilde.sitecrawler.masternodesonline.service.graphql;

import com.kevinwilde.graphqljavaclient.GraphQlClient;
import com.kevinwilde.sitecrawler.masternodesonline.domain.githubInforesponse.RepositoryInfoResponse;
import com.kevinwilde.sitecrawler.masternodesonline.domain.githubInforesponse.Repository;
import com.kevinwilde.sitecrawler.masternodesonline.factory.GithubGraphQlQueryFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.OffsetDateTime;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
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
    private RepositoryInfoResponse repositoryInfoResponse;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Repository repository;

    private OffsetDateTime createdAt = OffsetDateTime.parse("2018-03-06T20:42:58Z");
    private OffsetDateTime pushedAt = OffsetDateTime.parse("2018-04-18T21:36:33Z");


    @Test
    public void retrieveRepositoryInfoResponse_retrievesRepositoryInfoResponse(){

        when(repositoryInfoResponse.getData().getRepository()).thenReturn(repository);
        when(repository.getCreatedAt()).thenReturn(createdAt);
        when(repository.getPushedAt()).thenReturn(pushedAt);

        String repositoryOwner = "repositoryOwner";
        String repositoryName = "repositoryName";

        String query = "query";

        when(githubGraphQlQueryFactory.buildCommitsQuery(repositoryOwner, repositoryName)).thenReturn(query);
        when(graphQlClient.execute(GithubGraphQlQueryService.HTTPS_API_GITHUB_COM_GRAPHQL,
                query,
                RepositoryInfoResponse.class)).thenReturn(repositoryInfoResponse);


        RepositoryInfoResponse repositoryInfoResponse = classUnderTest.retrieveRepositoryInfoResponse(repositoryOwner, repositoryName);


        assertNotNull(repositoryInfoResponse.getData().getRepository().getDefaultBranchRef().getTarget().getHistory().getTotalCount());
        assertNotNull(repositoryInfoResponse.getData().getRepository().getCreatedAt());
        assertEquals(createdAt, repositoryInfoResponse.getData().getRepository().getCreatedAt());
        assertNotNull(repositoryInfoResponse.getData().getRepository().getPushedAt());
        assertEquals(pushedAt, repositoryInfoResponse.getData().getRepository().getPushedAt());

        verify(githubGraphQlQueryFactory).buildCommitsQuery(repositoryOwner, repositoryName);
        verify(graphQlClient).execute(GithubGraphQlQueryService.HTTPS_API_GITHUB_COM_GRAPHQL,
                query,
                RepositoryInfoResponse.class);
    }


}
