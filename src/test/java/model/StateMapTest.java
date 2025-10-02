/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/jsonToQuery
 */
package model;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class StateMapTest {

    @Test
    public void initMap_stateMapExpectedSize() {
        // Given
        // There are 52 state codes in the United States
        final int expectedSize = 52;
        final StateMap stateMap = new StateMap();

        // When
        final Map<String, String> map = stateMap.getStateMapForTesting();

        // Then
        Assert.assertNotNull(map);
        Assert.assertEquals(expectedSize, map.size());
    }

    @Test
    public void getState_getSomeCodesForValidStates() {
        // Give
        final StateMap stateMap = new StateMap();

        // When
        final String alNumber = stateMap.getStateId("AL");
        final String prNumber = stateMap.getStateId("PR");
        final String randomState = stateMap.getStateId("KS");

        // Then
        Assert.assertNotNull(alNumber);
        Assert.assertEquals("1", alNumber);
        Assert.assertNotNull(prNumber);
        Assert.assertEquals("52", prNumber);
        Assert.assertNotNull(randomState);
    }

    @Test
    public void getState_tryToGetCodeForInvalidState() {
        // Give
        final StateMap stateMap = new StateMap();

        // When
        final String invalidNumber = stateMap.getStateId("AGS");

        // Then
        Assert.assertNull(invalidNumber);
    }
}
