package com.kevinwilde.sitecrawler.masternodesonline.factory;

import org.springframework.stereotype.Component;

@Component
public class GithubGraphQlQueryFactory {
    public String buildCommitsQuery(String repositoryOwner, String repositoryName) {
        return "{\n" +
                "  repository(name: \"" + repositoryName + "\", owner: \"" + repositoryOwner + "\"){\n" +
                "    defaultBranchRef{\n" +
                "      target{\n" +
                "        ... on Commit{\n" +
                "          history{\n" +
                "            totalCount\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "    createdAt\n" +
                "    pushedAt\n" +
                "  }\n" +
                "}";
    }
}
