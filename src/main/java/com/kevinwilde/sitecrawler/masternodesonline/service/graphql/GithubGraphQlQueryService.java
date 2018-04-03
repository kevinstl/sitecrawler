package com.kevinwilde.sitecrawler.masternodesonline.service.graphql;


import com.kevinwilde.graphqljavaclient.GraphQlClient;
import com.kevinwilde.sitecrawler.masternodesonline.domain.GithubInfoResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class GithubGraphQlQueryService {

    @Autowired
    private GraphQlClient caller;

//    public static void main(String[] args) throws JSONException {
////        callingGraph();
//        restTemplateExample();
//    }

    private String query =
    "{\n" +
            "  repository(name: \"sitecrawler\", owner: \"kevinstl\"){\n" +
            "    defaultBranchRef{\n" +
            "      target{\n" +
            "        ... on Commit{\n" +
            "          history{\n" +
            "            totalCount\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";


    public Integer retrieveMasternodeGithubTotalCommits(String repositoryOwner, String repositoryName) {

//        String response =
//                caller.execute("https://api.github.com/graphql", query, String.class);

        GithubInfoResponse response =
                caller.execute("https://api.github.com/graphql", query, GithubInfoResponse.class);

        System.out.println("retrieveMasternodeGithubTotalCommits: response: " + response);
        return null;
    }

//    public Integer getCommits(){
//
//        RestTemplate restTemplate = new RestTemplate();
//        String fooResourceUrl
//                = "https://api.github.com/graphql";
//        ResponseEntity<String> response
//                = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
//
//        return null;
//    }
//
//    public static void callingGraph() throws JSONException {
//        CloseableHttpClient client= null;
//        CloseableHttpResponse response= null;
//
//        client= HttpClients.createDefault();
//        HttpPost httpPost= new HttpPost("https://api.github.com/graphql");
//
////        httpPost.addHeader("Authorization","Bearer myToken");
//        httpPost.addHeader("Authorization",System.getenv("GITHUB_BEARER_TOKEN"));
//        httpPost.addHeader("Accept","application/json");
//
//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("query",
////                "{repository(owner: \"wso2-extensions\", name: \"identity-inbound-auth-oauth\") { object(expression: \"83253ce50f189db30c54f13afa5d99021e2d7ece\") { ... on Commit { blame(path: \"components/org.wso2.carbon.identity.oauth.endpoint/src/main/java/org/wso2/carbon/identity/oauth/endpoint/authz/OAuth2AuthzEndpoint.java\") { ranges { startingLine, endingLine, age, commit { message url history(first: 2) { edges { node {  message, url } } } author { name, email } } } } } } } }"
//                query1
//        );
//
//        try {
//            StringEntity entity= new StringEntity(jsonObj.toString());
//
//            httpPost.setEntity(entity);
//            response= client.execute(httpPost);
//
//
////            System.out.println(EntityUtils.getContentMimeType(entity));
////            System.out.println(EntityUtils.getContentCharSet(entity));
//
//            String responseString = new BasicResponseHandler().handleResponse(response);
//            System.out.println(responseString);
//
//        }
//
//        catch(UnsupportedEncodingException e){
//            e.printStackTrace();
//        }
//        catch(ClientProtocolException e){
//            e.printStackTrace();
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//
////        try{
////
////            BufferedReader reader= new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
////            String line= null;
////            StringBuilder builder= new StringBuilder();
////            while((line=reader.readLine())!= null){
////
////                builder.append(line);
////
////            }
////        }
////        catch(Exception e){
////            e.printStackTrace();
////        }
//    }
//
//
//    private static void restTemplateExample() throws JSONException {
//
////        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
////        Map map = new HashMap<String, String>();
////        map.put("Content-Type", "application/json");
////        map.put("Authorization",System.getenv("GITHUB_BEARER_TOKEN"));
////
////        headers.setAll(map);
////
//////        Map req_payload = new HashMap();
//////        req_payload.put("name", "piyush");
////
////        String payload = query1;
////
////        MultiValueMap<String, Object> queryMap = new LinkedMultiValueMap<String, Object>();
////        queryMap.put("query", query1);
////
////        HttpEntity<?> request = new HttpEntity<>(payload, headers);
////        String url = "https://api.github.com/graphql";
////
//////        ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, String.class);
////        String response = new RestTemplate().postForObject(url, request, String.class);
//////        ServiceResponse entityResponse = (ServiceResponse) response.getBody();
//////        System.out.println(entityResponse.getData());
////
//////        System.out.println("response: " + response.getBody());
////        System.out.println("response: " + response);
//
//
//        String walletBalanceUrl = "https://api.github.com/graphql";
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("Content-Type", "application/json");
//        httpHeaders.set("Authorization",System.getenv("GITHUB_BEARER_TOKEN"));
//
////        JSONObject json = new JSONObject();
////        json.put("walletId", "6337637DL");
//
//        JSONObject json = new JSONObject();
//        json.put("query", query1);
//
//        HttpEntity <String> httpEntity = new HttpEntity <String> (json.toString(), httpHeaders);
////        Map queryParameters = new HashMap<String, String>();
////        queryParameters.put("query", query1);
////        HttpEntity <String> httpEntity = new HttpEntity <String> (queryParameters, httpHeaders);
//
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.postForObject(walletBalanceUrl, httpEntity, String.class);
//
//        JSONObject jsonObj = new JSONObject(response);
//
//        System.out.println(jsonObj);
//    }

}
