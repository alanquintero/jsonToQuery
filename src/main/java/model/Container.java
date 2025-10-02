/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/jsonToQuery
 */
package model;

import java.util.List;

/**
 * Represents the root container object used for JSON deserialization.
 * <p>
 * This class is designed to hold a collection of {@link Geometry} objects,
 * typically parsed from an input JSON file using the Jackson {@code ObjectMapper}.
 * </p>
 *
 * <pre>
 * Example usage:
 * ObjectMapper mapper = new ObjectMapper();
 * Container container = mapper.readValue(new File("input.json"), Container.class);
 * List<Geometry> geometries = container.getGeometries();
 * </pre>
 *
 * <h3>Expected JSON structure</h3>
 * The JSON file should contain a root object with a single array property
 * named {@code geometries}, where each element describes a geometry with its
 * type, properties, and arcs:
 *
 * <pre>
 * {
 *   "geometries": [
 *     {
 *       "type": "Polygon",
 *       "properties": {
 *         "name": "MT MEADOWS AREA",
 *         "zip": "00012",
 *         "state": "CA"
 *       },
 *       "arcs": "[[0, 1, 2]]"
 *     },
 *     {
 *       "type": "Polygon",
 *       "properties": {
 *         "name": "WEST PIMA COUNTY",
 *         "zip": "00014",
 *         "state": "AZ"
 *       },
 *       "arcs": "[[3, 4, 5, 6, 7]]"
 *     }
 *   ]
 * }
 * </pre>
 *
 * <ul>
 *   <li>{@code type} - the geometry type, e.g., "Polygon"</li>
 *   <li>{@code properties} - metadata object containing:
 *     <ul>
 *       <li>{@code name} - descriptive name of the region</li>
 *       <li>{@code zip} - zip code associated with the region</li>
 *       <li>{@code state} - two-letter state code (e.g., "CA", "AZ")</li>
 *     </ul>
 *   </li>
 *   <li>{@code arcs} - string representing the arc indices for the geometry</li>
 * </ul>
 *
 * @author Alan Quintero
 */
public class Container {

    private List<Geometry> geometries;

    public List<Geometry> getGeometries() {
        return geometries;
    }

    public void setGeometries(final List<Geometry> geometries) {
        this.geometries = geometries;
    }
}
