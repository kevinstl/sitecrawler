
package com.kevinwilde.sitecrawler.masternodesonline.domain;

import java.util.HashMap;
import java.util.Map;

public class DefaultBranchRef {

    private Target target;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
