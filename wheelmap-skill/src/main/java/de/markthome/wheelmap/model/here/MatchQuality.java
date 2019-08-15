
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
    "State",
    "City",
    "Street",
    "HouseNumber"
})
public class MatchQuality {

    @JsonProperty("State")
    private Integer state;
    @JsonProperty("City")
    private Integer city;
    @JsonProperty("Street")
    private List<Double> street = new ArrayList<Double>();
    @JsonProperty("HouseNumber")
    private Integer houseNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public MatchQuality() {
    }

    /**
     * 
     * @param street
     * @param state
     * @param houseNumber
     * @param city
     */
    public MatchQuality(Integer state, Integer city, List<Double> street, Integer houseNumber) {
        super();
        this.state = state;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    @JsonProperty("State")
    public Integer getState() {
        return state;
    }

    @JsonProperty("State")
    public void setState(Integer state) {
        this.state = state;
    }

    @JsonProperty("City")
    public Integer getCity() {
        return city;
    }

    @JsonProperty("City")
    public void setCity(Integer city) {
        this.city = city;
    }

    @JsonProperty("Street")
    public List<Double> getStreet() {
        return street;
    }

    @JsonProperty("Street")
    public void setStreet(List<Double> street) {
        this.street = street;
    }

    @JsonProperty("HouseNumber")
    public Integer getHouseNumber() {
        return houseNumber;
    }

    @JsonProperty("HouseNumber")
    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
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
        return new ToStringBuilder(this).append("state", state).append("city", city).append("street", street).append("houseNumber", houseNumber).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(street).append(state).append(houseNumber).append(city).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MatchQuality) == false) {
            return false;
        }
        MatchQuality rhs = ((MatchQuality) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(street, rhs.street).append(state, rhs.state).append(houseNumber, rhs.houseNumber).append(city, rhs.city).isEquals();
    }

}
