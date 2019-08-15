
package de.markthome.wheelmap.model.here;

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
    "MetaInfo",
    "View"
})
public class Response {

    @JsonProperty("MetaInfo")
    private MetaInfo metaInfo;
    @JsonProperty("View")
    private List<View> view = new ArrayList<View>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Response() {
    }

    /**
     * 
     * @param metaInfo
     * @param view
     */
    public Response(MetaInfo metaInfo, List<View> view) {
        super();
        this.metaInfo = metaInfo;
        this.view = view;
    }

    @JsonProperty("MetaInfo")
    public MetaInfo getMetaInfo() {
        return metaInfo;
    }

    @JsonProperty("MetaInfo")
    public void setMetaInfo(MetaInfo metaInfo) {
        this.metaInfo = metaInfo;
    }

    @JsonProperty("View")
    public List<View> getView() {
        return view;
    }

    @JsonProperty("View")
    public void setView(List<View> view) {
        this.view = view;
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
        return new ToStringBuilder(this).append("metaInfo", metaInfo).append("view", view).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(metaInfo).append(view).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Response) == false) {
            return false;
        }
        Response rhs = ((Response) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(metaInfo, rhs.metaInfo).append(view, rhs.view).isEquals();
    }

}
