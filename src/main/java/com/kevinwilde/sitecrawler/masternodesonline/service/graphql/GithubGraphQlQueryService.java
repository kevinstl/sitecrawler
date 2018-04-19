package com.kevinwilde.sitecrawler.masternodesonline.service.graphql;


import com.kevinwilde.graphqljavaclient.GraphQlClient;
import com.kevinwilde.sitecrawler.masternodesonline.domain.githubInforesponse.RepositoryInfoResponse;
import com.kevinwilde.sitecrawler.masternodesonline.factory.GithubGraphQlQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GithubGraphQlQueryService {

    public static final String HTTPS_API_GITHUB_COM_GRAPHQL = "https://api.github.com/graphql";

    @Autowired
    private GraphQlClient graphQlClient;

    @Autowired
    private GithubGraphQlQueryFactory githubGraphQlQueryFactory;



    public RepositoryInfoResponse retrieveRepositoryInfoResponse(String repositoryOwner, String repositoryName) {

        RepositoryInfoResponse repositoryInfoResponse =
                graphQlClient.execute(
                        HTTPS_API_GITHUB_COM_GRAPHQL,
                        githubGraphQlQueryFactory.buildCommitsQuery(repositoryOwner, repositoryName),
                        RepositoryInfoResponse.class);

        return repositoryInfoResponse;

    }



}
