/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/jsonToQuery
 */
package model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a single geometry element within the JSON structure.
 * <p>
 * Each geometry object defines a shape (e.g., a polygon) and contains
 * metadata in the {@link Property} object along with the arc indices
 * that describe the shape.
 * </p>
 *
 * <h3>Expected JSON structure</h3>
 * A geometry entry in the {@code geometries} array is expected to look like:
 *
 * <pre>
 * {
 *   "type": "Polygon",
 *   "properties": {
 *     "name": "MT MEADOWS AREA",
 *     "zip": "00012",
 *     "state": "CA"
 *   },
 *   "arcs": "[[0, 1, 2]]"
 * }
 * </pre>
 *
 * <ul>
 *   <li>{@code type} - the geometry type, such as "Polygon".</li>
 *   <li>{@code properties} - a {@link Property} object containing:
 *     <ul>
 *       <li>{@code name} - descriptive name of the region.</li>
 *       <li>{@code zip} - zip code associated with the region.</li>
 *       <li>{@code state} - two-letter state code (e.g., "CA").</li>
 *     </ul>
 *   </li>
 *   <li>{@code arcs} - a string representation of the arc indices
 *       that define the geometry's shape (e.g., "[[0, 1, 2]]").</li>
 * </ul>
 *
 * @author Alan Quintero
 */
public class Geometry {

    @JsonProperty("type")
    private String type;

    @JsonProperty("properties")
    private Property properties;

    @JsonProperty("arcs")
    private String arcs;

    public String getArcs() {
        return arcs;
    }

    public void setArcs(final String arcs) {
        this.arcs = arcs;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Property getProperties() {
        return properties;
    }

    public void setProperties(final Property properties) {
        this.properties = properties;
    }

}
