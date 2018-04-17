package com.kevinwilde.sitecrawler.masternodesonline.service.graphql;


import com.kevinwilde.graphqljavaclient.GraphQlClient;
import com.kevinwilde.sitecrawler.masternodesonline.domain.githubInforesponse.GithubInfoResponse;
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


    public Integer retrieveMasternodeGithubTotalCommits(String repositoryOwner, String repositoryName) {

        GithubInfoResponse response =
                graphQlClient.execute(
                        HTTPS_API_GITHUB_COM_GRAPHQL,
                        githubGraphQlQueryFactory.buildCommitsQuery(repositoryOwner, repositoryName),
                        GithubInfoResponse.class);

        System.out.println("retrieveMasternodeGithubTotalCommits: response: " + response);

        Integer totalCount = null;

        try{
            totalCount = response.getData().getRepository().getDefaultBranchRef().getTarget().getHistory().getTotalCount();
        }
        catch(NullPointerException e){
//            e.printStackTrace();
        }

        return totalCount;
    }

}
