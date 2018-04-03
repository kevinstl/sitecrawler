
package com.kevinwilde.sitecrawler.masternodesonline.domain;

import java.util.HashMap;
import java.util.Map;

public class Repository {

    private DefaultBranchRef defaultBranchRef;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public DefaultBranchRef getDefaultBranchRef() {
        return defaultBranchRef;
    }

    public void setDefaultBranchRef(DefaultBranchRef defaultBranchRef) {
        this.defaultBranchRef = defaultBranchRef;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
