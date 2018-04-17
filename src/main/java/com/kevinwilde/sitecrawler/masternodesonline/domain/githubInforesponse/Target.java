
package com.kevinwilde.sitecrawler.masternodesonline.domain.githubInforesponse;

import java.util.HashMap;
import java.util.Map;

public class Target {

    private History history;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
