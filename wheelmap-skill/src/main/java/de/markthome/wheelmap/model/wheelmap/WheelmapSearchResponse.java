
package de.markthome.wheelmap.model.wheelmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "conditions",
    "meta",
    "nodes"
})
public class WheelmapSearchResponse {

    @JsonProperty("conditions")
    private Conditions conditions;
    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("nodes")
    private List<Node> nodes = new ArrayList<Node>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public WheelmapSearchResponse() {
    }

    /**
     * 
     * @param conditions
     * @param nodes
     * @param meta
     */
    public WheelmapSearchResponse(Conditions conditions, Meta meta, List<Node> nodes) {
        super();
        this.conditions = conditions;
        this.meta = meta;
        this.nodes = nodes;
    }

    @JsonProperty("conditions")
    public Conditions getConditions() {
        return conditions;
    }

    @JsonProperty("conditions")
    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @JsonProperty("nodes")
    public List<Node> getNodes() {
        return nodes;
    }

    @JsonProperty("nodes")
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
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
        return new ToStringBuilder(this).append("conditions", conditions).append("meta", meta).append("nodes", nodes).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(conditions).append(additionalProperties).append(nodes).append(meta).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof WheelmapSearchResponse) == false) {
            return false;
        }
        WheelmapSearchResponse rhs = ((WheelmapSearchResponse) other);
        return new EqualsBuilder().append(conditions, rhs.conditions).append(additionalProperties, rhs.additionalProperties).append(nodes, rhs.nodes).append(meta, rhs.meta).isEquals();
    }

}
