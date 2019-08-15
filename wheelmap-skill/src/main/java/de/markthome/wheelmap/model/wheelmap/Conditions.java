
package de.markthome.wheelmap.model.wheelmap;

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
    "format",
    "page",
    "per_page",
    "bbox"
})
public class Conditions {

    @JsonProperty("format")
    private String format;
    @JsonProperty("page")
    private Long page;
    @JsonProperty("per_page")
    private Long perPage;
    @JsonProperty("bbox")
    private String bbox;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Conditions() {
    }

    /**
     * 
     * @param page
     * @param bbox
     * @param perPage
     * @param format
     */
    public Conditions(String format, Long page, Long perPage, String bbox) {
        super();
        this.format = format;
        this.page = page;
        this.perPage = perPage;
        this.bbox = bbox;
    }

    @JsonProperty("format")
    public String getFormat() {
        return format;
    }

    @JsonProperty("format")
    public void setFormat(String format) {
        this.format = format;
    }

    @JsonProperty("page")
    public Long getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Long page) {
        this.page = page;
    }

    @JsonProperty("per_page")
    public Long getPerPage() {
        return perPage;
    }

    @JsonProperty("per_page")
    public void setPerPage(Long perPage) {
        this.perPage = perPage;
    }

    @JsonProperty("bbox")
    public String getBbox() {
        return bbox;
    }

    @JsonProperty("bbox")
    public void setBbox(String bbox) {
        this.bbox = bbox;
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
        return new ToStringBuilder(this).append("format", format).append("page", page).append("perPage", perPage).append("bbox", bbox).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(page).append(additionalProperties).append(bbox).append(perPage).append(format).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Conditions) == false) {
            return false;
        }
        Conditions rhs = ((Conditions) other);
        return new EqualsBuilder().append(page, rhs.page).append(additionalProperties, rhs.additionalProperties).append(bbox, rhs.bbox).append(perPage, rhs.perPage).append(format, rhs.format).isEquals();
    }

}
