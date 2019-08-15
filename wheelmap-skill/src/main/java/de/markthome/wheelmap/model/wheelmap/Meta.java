
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
    "page",
    "num_pages",
    "item_count",
    "item_count_total"
})
public class Meta {

    @JsonProperty("page")
    private Long page;
    @JsonProperty("num_pages")
    private Long numPages;
    @JsonProperty("item_count")
    private Long itemCount;
    @JsonProperty("item_count_total")
    private Long itemCountTotal;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Meta() {
    }

    /**
     * 
     * @param itemCountTotal
     * @param page
     * @param itemCount
     * @param numPages
     */
    public Meta(Long page, Long numPages, Long itemCount, Long itemCountTotal) {
        super();
        this.page = page;
        this.numPages = numPages;
        this.itemCount = itemCount;
        this.itemCountTotal = itemCountTotal;
    }

    @JsonProperty("page")
    public Long getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Long page) {
        this.page = page;
    }

    @JsonProperty("num_pages")
    public Long getNumPages() {
        return numPages;
    }

    @JsonProperty("num_pages")
    public void setNumPages(Long numPages) {
        this.numPages = numPages;
    }

    @JsonProperty("item_count")
    public Long getItemCount() {
        return itemCount;
    }

    @JsonProperty("item_count")
    public void setItemCount(Long itemCount) {
        this.itemCount = itemCount;
    }

    @JsonProperty("item_count_total")
    public Long getItemCountTotal() {
        return itemCountTotal;
    }

    @JsonProperty("item_count_total")
    public void setItemCountTotal(Long itemCountTotal) {
        this.itemCountTotal = itemCountTotal;
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
        return new ToStringBuilder(this).append("page", page).append("numPages", numPages).append("itemCount", itemCount).append("itemCountTotal", itemCountTotal).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(itemCountTotal).append(page).append(additionalProperties).append(itemCount).append(numPages).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Meta) == false) {
            return false;
        }
        Meta rhs = ((Meta) other);
        return new EqualsBuilder().append(itemCountTotal, rhs.itemCountTotal).append(page, rhs.page).append(additionalProperties, rhs.additionalProperties).append(itemCount, rhs.itemCount).append(numPages, rhs.numPages).isEquals();
    }

}
