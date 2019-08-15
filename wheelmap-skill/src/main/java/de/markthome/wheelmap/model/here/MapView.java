
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
    "TopLeft",
    "BottomRight"
})
public class MapView {

    @JsonProperty("TopLeft")
    private TopLeft topLeft;
    @JsonProperty("BottomRight")
    private BottomRight bottomRight;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public MapView() {
    }

    /**
     * 
     * @param bottomRight
     * @param topLeft
     */
    public MapView(TopLeft topLeft, BottomRight bottomRight) {
        super();
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    @JsonProperty("TopLeft")
    public TopLeft getTopLeft() {
        return topLeft;
    }

    @JsonProperty("TopLeft")
    public void setTopLeft(TopLeft topLeft) {
        this.topLeft = topLeft;
    }

    @JsonProperty("BottomRight")
    public BottomRight getBottomRight() {
        return bottomRight;
    }

    @JsonProperty("BottomRight")
    public void setBottomRight(BottomRight bottomRight) {
        this.bottomRight = bottomRight;
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
        return new ToStringBuilder(this).append("topLeft", topLeft).append("bottomRight", bottomRight).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(bottomRight).append(topLeft).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MapView) == false) {
            return false;
        }
        MapView rhs = ((MapView) other);
        return new EqualsBuilder().append(bottomRight, rhs.bottomRight).append(topLeft, rhs.topLeft).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
