/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/jsonToQuery
 */

/**
 * The {@code Main} class serves as the entry point for the application.
 * <p>
 * It defines the path to the JSON resource file and invokes the
 * {@link Generator#generateQuery(String)} method to process it.
 * </p>
 *
 * @author Alan Quintero
 */
public class Main {

    /**
     * The path to the JSON file containing region data.
     */
    public static final String JSON_FILE = "src/main/java/resources/region.json";

    /**
     * The main entry point of the application.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Generator.generateQuery(JSON_FILE);
    }

}
