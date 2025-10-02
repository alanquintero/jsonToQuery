/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/jsonToQuery
 */
package model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a property with basic details such as
 * name, zip code, and state.
 *
 * @author Alan Quintero
 */
public class Property {

    @JsonProperty("name")
    private String name;

    @JsonProperty("zip")
    private String zip;

    @JsonProperty("state")
    private String state;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(final String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

}
