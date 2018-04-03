
package com.kevinwilde.sitecrawler.masternodesonline.domain;

import java.util.HashMap;
import java.util.Map;

public class History {

    private Integer totalCount;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
