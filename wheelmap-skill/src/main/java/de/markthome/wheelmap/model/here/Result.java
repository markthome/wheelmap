
package de.markthome.wheelmap.model.here;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Relevance",
    "MatchLevel",
    "MatchQuality",
    "MatchType",
    "Location"
})
public class Result {

    @JsonProperty("Relevance")
    private Integer relevance;
    @JsonProperty("MatchLevel")
    private String matchLevel;
    @JsonProperty("MatchQuality")
    private MatchQuality matchQuality;
    @JsonProperty("MatchType")
    private String matchType;
    @JsonProperty("Location")
    private Location location;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param matchQuality
     * @param location
     * @param matchLevel
     * @param matchType
     * @param relevance
     */
    public Result(Integer relevance, String matchLevel, MatchQuality matchQuality, String matchType, Location location) {
        super();
        this.relevance = relevance;
        this.matchLevel = matchLevel;
        this.matchQuality = matchQuality;
        this.matchType = matchType;
        this.location = location;
    }

    @JsonProperty("Relevance")
    public Integer getRelevance() {
        return relevance;
    }

    @JsonProperty("Relevance")
    public void setRelevance(Integer relevance) {
        this.relevance = relevance;
    }

    @JsonProperty("MatchLevel")
    public String getMatchLevel() {
        return matchLevel;
    }

    @JsonProperty("MatchLevel")
    public void setMatchLevel(String matchLevel) {
        this.matchLevel = matchLevel;
    }

    @JsonProperty("MatchQuality")
    public MatchQuality getMatchQuality() {
        return matchQuality;
    }

    @JsonProperty("MatchQuality")
    public void setMatchQuality(MatchQuality matchQuality) {
        this.matchQuality = matchQuality;
    }

    @JsonProperty("MatchType")
    public String getMatchType() {
        return matchType;
    }

    @JsonProperty("MatchType")
    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    @JsonProperty("Location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("Location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("relevance", relevance).append("matchLevel", matchLevel).append("matchQuality", matchQuality).append("matchType", matchType).append("location", location).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(matchQuality).append(location).append(matchLevel).append(additionalProperties).append(matchType).append(relevance).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Result) == false) {
            return false;
        }
        Result rhs = ((Result) other);
        return new EqualsBuilder().append(matchQuality, rhs.matchQuality).append(location, rhs.location).append(matchLevel, rhs.matchLevel).append(additionalProperties, rhs.additionalProperties).append(matchType, rhs.matchType).append(relevance, rhs.relevance).isEquals();
    }

}
