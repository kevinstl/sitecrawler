
package com.kevinwilde.sitecrawler.masternodesonline.domain.githubInforesponse;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

public class Repository {

    private DefaultBranchRef defaultBranchRef;

    private OffsetDateTime createdAt;
    private OffsetDateTime pushedAt;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public DefaultBranchRef getDefaultBranchRef() {
        return defaultBranchRef;
    }

    public void setDefaultBranchRef(DefaultBranchRef defaultBranchRef) {
        this.defaultBranchRef = defaultBranchRef;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getPushedAt() {
        return pushedAt;
    }

    public void setPushedAt(OffsetDateTime pushedAt) {
        this.pushedAt = pushedAt;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
