/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/jsonToQuery
 */

import model.Container;
import model.Geometry;
import model.Property;
import model.StateMap;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GeneratorTest {

    private static final String TEST_JSON = "test.json";
    private static final String TEST_DIR = System.getProperty("user.home") + File.separator + "jsonToQuery";

    @Before
    public void setup() throws Exception {
        // create a minimal JSON file for testing createContainer
        final String jsonContent = "{ \"geometries\": [ { \"type\": \"Polygon\", \"properties\": { \"name\": \"Test Area\", \"zip\": \"12345\", \"state\": \"CA\" }, \"arcs\": \"[[0,1,2]]\" } ] }";
        Files.write(Path.of(TEST_JSON), jsonContent.getBytes());
    }

    @After
    public void cleanup() throws Exception {
        Files.deleteIfExists(Path.of(TEST_JSON));
        Files.deleteIfExists(Path.of(TEST_DIR + File.separator + "insert-geometry.sql"));
        Files.deleteIfExists(Path.of(TEST_DIR));
    }

    @Test
    public void createContainer_validJson() {
        // Given & When
        final Container container = Generator.createContainer(TEST_JSON);

        // Then
        Assert.assertNotNull(container);
        Assert.assertEquals(1, container.getGeometries().size());
        Assert.assertEquals("Polygon", container.getGeometries().get(0).getType());
    }

    @Test
    public void createContainer_invalidJson() {
        // Given & When
        final Container container = Generator.createContainer("nonexistent.json");

        // Then
        Assert.assertNull(container);
    }

    @Test
    public void createDirectory() {
        // Given & When
        final String dirPath = Generator.createDirectory();

        // Then
        Assert.assertNotNull(dirPath);
        final File dir = new File(dirPath);
        Assert.assertTrue(dir.exists());
        Assert.assertTrue(dir.isDirectory());
    }

    @Test
    public void createFile() {
        // Given
        final String dirPath = Generator.createDirectory();
        final Path filePath = Generator.createFile(dirPath);
        Assert.assertNotNull(filePath);

        // When
        final File file = filePath.toFile();

        // Then
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.isFile());
    }

    @Test
    public void writeToFile_createsSqlFile() throws Exception {
        // Given
        final Container container = Generator.createContainer(TEST_JSON);
        final String dirPath = Generator.createDirectory();
        final Path filePath = Generator.createFile(dirPath);

        // When
        Generator.writeToFile(container, filePath);

        // Then
        final String content = Files.readString(filePath);
        Assert.assertTrue(content.contains("INSERT INTO map.geometry"));
        Assert.assertTrue(content.contains("Polygon"));
        Assert.assertTrue(content.contains("Test Area"));
    }


    @Test
    public void generateQuery_replacesTags() {
        // Given
        final String expectedQuery = "('Polygon','WEST PIMA COUNTY',00014,'AZ','[[3, 4, 5, 6, 7]]',3),";
        StringBuilder query = new StringBuilder(Generator.TAGS_FOR_QUERY);
        final Property properties = new Property();
        properties.setName("WEST PIMA COUNTY");
        properties.setZip("00014");
        properties.setState("AZ");
        final Geometry geometry = new Geometry();
        geometry.setType("Polygon");
        geometry.setProperties(properties);
        geometry.setArcs("[[3, 4, 5, 6, 7]]");
        final List<Geometry> geometries = new ArrayList<>();
        geometries.add(geometry);
        geometries.add(geometry);
        final Container container = new Container();
        container.setGeometries(geometries);
        final StateMap stateMap = new StateMap();
        final int counter = 1;

        // When
        Generator.generateQuery(query, container, stateMap, geometry, counter);

        // Then
        Assert.assertEquals(expectedQuery, query.toString());
    }

    @Test
    public void generateQuery__replacesTags_isTheLastRecord() {
        // Given
        final String expectedQuery = "('Polygon','WEST PIMA COUNTY',00014,'AZ','[[3, 4, 5, 6, 7]]',3);";
        StringBuilder query = new StringBuilder(Generator.TAGS_FOR_QUERY);
        final Property properties = new Property();
        properties.setName("WEST PIMA COUNTY");
        properties.setZip("00014");
        properties.setState("AZ");
        final Geometry geometry = new Geometry();
        geometry.setType("Polygon");
        geometry.setProperties(properties);
        geometry.setArcs("[[3, 4, 5, 6, 7]]");
        final List<Geometry> geometries = new ArrayList<>();
        geometries.add(geometry);
        final Container container = new Container();
        container.setGeometries(geometries);
        final StateMap stateMap = new StateMap();
        final int counter = geometries.size();

        // When
        Generator.generateQuery(query, container, stateMap, geometry, counter);

        // Then
        Assert.assertEquals(expectedQuery, query.toString());
    }
}
