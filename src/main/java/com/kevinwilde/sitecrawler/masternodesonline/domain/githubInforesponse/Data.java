
package com.kevinwilde.sitecrawler.masternodesonline.domain.githubInforesponse;

import java.util.HashMap;
import java.util.Map;

public class Data {

    private Repository repository;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
