/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/jsonToQuery
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Container;
import model.Geometry;
import model.StateMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The {@code Generator} class provides functionality to transform a JSON file describing
 * geographical geometries into a SQL insert script.
 * <p>
 * The process involves:
 * <ul>
 *   <li>Parsing the JSON file into a {@link Container} object.</li>
 *   <li>Creating a platform-independent output directory for generated files.</li>
 *   <li>Creating a SQL file named {@code insert-geometry.sql} in the output directory.</li>
 *   <li>Writing INSERT INTO statements into the SQL file, replacing template placeholders
 *       with actual geometry data from the JSON.</li>
 * </ul>
 * <p>
 * The output SQL is designed to populate the {@code map.geometry} table with records containing
 * geometry type, region name, zip code, state code, arcs, and a foreign key to the state.
 * </p>
 *
 * <p><b>Expected JSON structure:</b></p>
 * <pre>
 * {
 *   "geometries": [
 *     {
 *       "type": "Polygon",
 *       "properties": {
 *         "name": "Region Name",
 *         "zip": "12345",
 *         "state": "CA"
 *       },
 *       "arcs": "[[0, 1, 2]]"
 *     }
 *   ]
 * }
 * </pre>
 *
 * <p><b>Output:</b></p>
 * <pre>
 * INSERT INTO map.geometry (geometryType, regionName, zipCode, stateCode, arcs, idstatefk) VALUES
 * ('Polygon','Region Name',12345,'CA','[[0, 1, 2]]',5);
 * </pre>
 * <p>
 * This class is stateless and only provides static utility methods.
 *
 * @author Alan Quintero
 */
public class Generator {

    private static final String FILE_NAME = "data.sql";
    private static final String INSERT_INTO_START_STATEMENT = "INSERT INTO geometry (geometry_type, region_name, zip_code, state_code, arcs, state_id) VALUES \n";
    private final static String GEOMETRY_TYPE_TAG = "<geometryType>";
    private final static String REGION_NAME_TAG = "<regionName>";
    private final static String ZIP_CODE_TAG = "<zipCode>";
    private final static String STATE_CODE_TAG = "<stateCode>";
    private final static String ARCS_TAG = "<arcs>";
    private final static String STATE_ID_TAG = "<stateID>";
    static final String TAGS_FOR_QUERY = "('<geometryType>','<regionName>','<zipCode>','<stateCode>','<arcs>',<stateID>),";

    public static void generateQuery(final String jsonFile) {
        final Container container = createContainer(jsonFile);
        if (container == null) {
            System.err.println("Container is null");
            return;
        }

        final String baseDir = createDirectory();
        if (baseDir == null) {
            System.err.println("Could not create directory");
            return;
        }

        final Path path = createFile(baseDir);
        if (path == null) {
            System.err.println("Could not create file");
            return;
        }

        writeToFile(container, path);
        System.out.println("File generated successfully");
    }

    /**
     * Creates a {@link Container} object using the given json file.
     *
     * @param jsonFile a json file with a specific structure.
     * @return {@link Container}
     */
    static Container createContainer(final String jsonFile) {
        final ObjectMapper mapper = new ObjectMapper();
        Container container = null;
        try {
            container = mapper.readValue(new File(jsonFile), Container.class);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return container;
    }

    /**
     * Creates a directory (if not exists) at:
     * macOS/Linux: /Users/{user}/jsonToQuery
     * Windows: C:\Users\{user}\Documents\jsonToQuery
     *
     * @return the created directory path
     */
    static String createDirectory() {
        // Make directory path cross-platform
        final String baseDir = System.getProperty("user.home") + File.separator + "jsonToQuery";
        final File directory = new File(baseDir);
        if (!directory.exists()) {
            if (directory.mkdir()) {
                System.out.println("Directory is created at " + directory.getAbsolutePath());
            } else {
                System.err.println("Failed to create directory at " + directory.getAbsolutePath());
                return null;
            }
        } else {
            System.out.println("On directory: " + directory.getAbsolutePath());
        }
        return baseDir;
    }

    /**
     * Creates a file in the given directory.
     *
     * @param baseDir the directory
     */
    static Path createFile(final String baseDir) {
        final File file = new File(baseDir + File.separator + FILE_NAME);
        Path path = Paths.get(baseDir, FILE_NAME);

        try {
            Files.deleteIfExists(path);
            if (file.createNewFile()) {
                System.out.println("File was created at " + path.toAbsolutePath());
            } else {
                System.err.println("Could not create file");
            }
        } catch (IOException e) {
            path = null;
            System.err.println(e.getMessage());
        }

        return path;
    }

    /**
     * Write the information from the {@link Container} to the file in the given {@link Path}
     *
     * @param container the json information
     * @param path      the path to the file
     */
    static void writeToFile(final Container container, final Path path) {
        final StringBuilder queryTemplate = new StringBuilder(TAGS_FOR_QUERY);
        final String query = queryTemplate.toString();
        if (!(query.contains(GEOMETRY_TYPE_TAG) || query.contains(REGION_NAME_TAG) || query.contains(ZIP_CODE_TAG)
                || query.contains(STATE_CODE_TAG) || query.contains(ARCS_TAG) || query.contains(STATE_ID_TAG))) {
            System.err.println("Query template is incorrect!");
            return;
        }
        final StateMap stateMap = new StateMap();

        int counter = 1;

        System.out.println("Number of records to generate: " + container.getGeometries().size());
        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(path);
            writer.write(INSERT_INTO_START_STATEMENT);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        for (final Geometry geometry : container.getGeometries()) {
            generateQuery(queryTemplate, container, stateMap, geometry, counter);

            try {
                assert writer != null;
                writer.write(queryTemplate + "\n");
                if (counter == 1000 || counter == 2000) {
                    writer.flush();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            // Reset the template
            queryTemplate.replace(0, queryTemplate.length(), TAGS_FOR_QUERY);
            counter++;
        }
        try {
            assert writer != null;
            writer.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Records created: " + counter);
    }

    /**
     * Replace the tags in the input query with values.
     *
     * @param query     the template query
     * @param container the json information
     * @param stateMap  the state codes
     * @param geometry  the current geometry
     * @param counter   the current number of records generated
     */
    static void generateQuery(StringBuilder query, final Container container, final StateMap stateMap, final Geometry geometry, final int counter) {
        query.replace(0, query.length(), query.toString().replace(GEOMETRY_TYPE_TAG, geometry.getType()));
        query.replace(0, query.length(),
                query.toString().replace(REGION_NAME_TAG, geometry.getProperties().getName()));
        query.replace(0, query.length(), query.toString().replace(ZIP_CODE_TAG, geometry.getProperties().getZip()));
        query.replace(0, query.length(),
                query.toString().replace(STATE_CODE_TAG, geometry.getProperties().getState()));
        query.replace(0, query.length(), query.toString().replace(ARCS_TAG, geometry.getArcs()));
        query.replace(0, query.length(),
                query.toString().replace(STATE_ID_TAG, stateMap.getStateId(geometry.getProperties().getState())));

        if (counter == container.getGeometries().size()) {
            query.replace(query.length() - 1, query.length(), ";");
        }
    }
}

