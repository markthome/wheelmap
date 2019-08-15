
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
    "id",
    "lat",
    "lon",
    "node_type",
    "category",
    "wheelchair",
    "wheelchair_description",
    "street",
    "housenumber",
    "city",
    "postcode",
    "website",
    "phone",
    "name"
})
public class Node {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lon")
    private Double lon;
    @JsonProperty("node_type")
    private NodeType nodeType;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("wheelchair")
    private String wheelchair;
    @JsonProperty("wheelchair_description")
    private String wheelchairDescription;
    @JsonProperty("street")
    private Object street;
    @JsonProperty("housenumber")
    private Object housenumber;
    @JsonProperty("city")
    private Object city;
    @JsonProperty("postcode")
    private Object postcode;
    @JsonProperty("website")
    private String website;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Node() {
    }

    /**
     * 
     * @param phone
     * @param lon
     * @param website
     * @param street
     * @param housenumber
     * @param postcode
     * @param city
     * @param id
     * @param category
     * @param wheelchair
     * @param name
     * @param wheelchairDescription
     * @param nodeType
     * @param lat
     */
    public Node(Long id, Double lat, Double lon, NodeType nodeType, Category category, String wheelchair, String wheelchairDescription, Object street, Object housenumber, Object city, Object postcode, String website, String phone, String name) {
        super();
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.nodeType = nodeType;
        this.category = category;
        this.wheelchair = wheelchair;
        this.wheelchairDescription = wheelchairDescription;
        this.street = street;
        this.housenumber = housenumber;
        this.city = city;
        this.postcode = postcode;
        this.website = website;
        this.phone = phone;
        this.name = name;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("lat")
    public Double getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(Double lat) {
        this.lat = lat;
    }

    @JsonProperty("lon")
    public Double getLon() {
        return lon;
    }

    @JsonProperty("lon")
    public void setLon(Double lon) {
        this.lon = lon;
    }

    @JsonProperty("node_type")
    public NodeType getNodeType() {
        return nodeType;
    }

    @JsonProperty("node_type")
    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    @JsonProperty("category")
    public Category getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonProperty("wheelchair")
    public String getWheelchair() {
        return wheelchair;
    }

    @JsonProperty("wheelchair")
    public void setWheelchair(String wheelchair) {
        this.wheelchair = wheelchair;
    }

    @JsonProperty("wheelchair_description")
    public String getWheelchairDescription() {
        return wheelchairDescription;
    }

    @JsonProperty("wheelchair_description")
    public void setWheelchairDescription(String wheelchairDescription) {
        this.wheelchairDescription = wheelchairDescription;
    }

    @JsonProperty("street")
    public Object getStreet() {
        return street;
    }

    @JsonProperty("street")
    public void setStreet(Object street) {
        this.street = street;
    }

    @JsonProperty("housenumber")
    public Object getHousenumber() {
        return housenumber;
    }

    @JsonProperty("housenumber")
    public void setHousenumber(Object housenumber) {
        this.housenumber = housenumber;
    }

    @JsonProperty("city")
    public Object getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(Object city) {
        this.city = city;
    }

    @JsonProperty("postcode")
    public Object getPostcode() {
        return postcode;
    }

    @JsonProperty("postcode")
    public void setPostcode(Object postcode) {
        this.postcode = postcode;
    }

    @JsonProperty("website")
    public String getWebsite() {
        return website;
    }

    @JsonProperty("website")
    public void setWebsite(String website) {
        this.website = website;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
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
        return new ToStringBuilder(this).append("id", id).append("lat", lat).append("lon", lon).append("nodeType", nodeType).append("category", category).append("wheelchair", wheelchair).append("wheelchairDescription", wheelchairDescription).append("street", street).append("housenumber", housenumber).append("city", city).append("postcode", postcode).append("website", website).append("phone", phone).append("name", name).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(phone).append(lon).append(website).append(street).append(postcode).append(housenumber).append(city).append(id).append(category).append(wheelchair).append(additionalProperties).append(name).append(wheelchairDescription).append(nodeType).append(lat).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Node) == false) {
            return false;
        }
        Node rhs = ((Node) other);
        return new EqualsBuilder().append(phone, rhs.phone).append(lon, rhs.lon).append(website, rhs.website).append(street, rhs.street).append(postcode, rhs.postcode).append(housenumber, rhs.housenumber).append(city, rhs.city).append(id, rhs.id).append(category, rhs.category).append(wheelchair, rhs.wheelchair).append(additionalProperties, rhs.additionalProperties).append(name, rhs.name).append(wheelchairDescription, rhs.wheelchairDescription).append(nodeType, rhs.nodeType).append(lat, rhs.lat).isEquals();
    }

}
